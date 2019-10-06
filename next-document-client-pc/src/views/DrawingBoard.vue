<template>
    <div class="drawing-board">
      <canvas
        id="canvas"
        @mouseup="onMouseUp"
        @mousedown="onMouseDown"
        @mousemove="onMouseMove"
        :style="backgroundColor"
      >
        您的浏览器不支持canvas, 请更换浏览器访问
      </canvas>
      <div id="color-button-group">
        <!--黑-->
        <el-button
          v-if="this.activeColorButton == 'black'"
          class="color-button" icon="el-icon-check" circle @click="changeColor('black')" style="background: #000000">
        </el-button>
        <el-button
          v-else
          class="color-button" circle @click="changeColor('black')" style="background: #000000">
        </el-button>
        <!--蓝-->
        <el-button
          v-if="this.activeColorButton == 'blue'"
          class="color-button" icon="el-icon-check" circle @click="changeColor('blue')" style="background: #409EFF">
        </el-button>
        <el-button
          v-else
          class="color-button" circle @click="changeColor('blue')" style="background: #409EFF">
        </el-button>
        <!--绿-->
        <el-button
          v-if="this.activeColorButton == 'green'"
          class="color-button" icon="el-icon-check" circle @click="changeColor('green')" style="background: #67C23A;">
        </el-button>
        <el-button
          v-else
          class="color-button" circle @click="changeColor('green')" style="background: #67C23A;">
        </el-button>
        <!--灰-->
        <el-button
          v-if="this.activeColorButton == 'gray'"
          class="color-button" icon="el-icon-check" circle @click="changeColor('gray')" style="background: #909399;">
        </el-button>
        <el-button
          v-else
          class="color-button" circle @click="changeColor('gray')" style="background: #909399;">
        </el-button>
        <!--橙-->
        <el-button
          v-if="this.activeColorButton == 'orange'"
          class="color-button" icon="el-icon-check" circle @click="changeColor('orange')" style="background: #E6A23C;">
        </el-button>
        <el-button
          v-else
          class="color-button" circle @click="changeColor('orange')" style="background: #E6A23C;">
        </el-button>
        <!--粉-->
        <el-button
          v-if="this.activeColorButton == 'pink'"
          class="color-button" icon="el-icon-check" circle @click="changeColor('pink')" style="background: #F56C6C;">
        </el-button>
        <el-button
          v-else
          class="color-button" circle @click="changeColor('pink')" style="background: #F56C6C;">
        </el-button>
        <el-color-picker class="color-button" v-model="brushColor" size="medium"></el-color-picker>
      </div>
      <div id="slider">
        <el-slider
          v-model="slider.value"
          :min="slider.minValue"
          :max="slider.maxValue"
          height="30vh"
          vertical
        >
        </el-slider>
      </div>
      <div id="tools">
        <el-radio-group v-model="radioValue">
          <el-button
            v-if="activeTool == 1"
            type="primary" round icon="el-icon-edit" @click="changeTools(1)">
            画笔
          </el-button>
          <el-button
            v-else
            type="primary" round plain icon="el-icon-edit" @click="changeTools(1)">
            画笔
          </el-button>
          <el-button
            v-if="activeTool == 2"
            type="primary" round icon="el-icon-notebook-2" @click="changeTools(2)">
            橡皮擦
          </el-button>
          <el-button
            v-else
            type="primary" round plain icon="el-icon-notebook-2" @click="changeTools(2)">
            橡皮擦
          </el-button>
          <el-button
            type="primary" round plain icon="el-icon-back" @click="changeTools(3)">
            撤销
          </el-button>
          <el-button
            type="primary" round plain icon="el-icon-delete" @click="changeTools(4)">
            清空
          </el-button>
          <el-button
            type="primary" round plain icon="el-icon-receiving" @click="changeTools(5)">
            下载
          </el-button>
        </el-radio-group>
      </div>
    </div>
</template>

<script>
    export default {
      data () {
        return {
          activeTool: 1,
          canvas: null,
          canvasCtx: null,
          canvasWidth: null,
          canvasHeight: null,
          brushColor: '#000000',
          activeColorButton: 'black',
          backgroundColor: {
            backgroundColor: 'blueviolet'
          },
          erase: false,
          lineWidth: 3,
          painting: false,
          lastPoint: {
            x: null,
            y: null
          },
          webSocket: null,
          slider: {
            value: 5,
            minValue: 1,
            maxValue: 50
          },
          radioValue: '画笔',
          history: []
        }
      },
      methods: {
        initCanvas: function () {
          this.canvas = document.getElementById("canvas");
          this.canvasWidth = `${document.documentElement.clientWidth}`;
          this.canvasHeight = `${document.documentElement.clientHeight}`;
          this.canvas.width = this.canvasWidth;
          this.canvas.height = this.canvasHeight;
          this.canvasCtx = this.canvas.getContext("2d");
          this.canvasCtx.lineWidth = this.lineWidth;
        },
        drawLine: function (x1, y1, x2, y2) {
          if (this.erase) {
            this.canvasCtx.save();
            this.canvasCtx.globalCompositeOperation = "destination-out";
            this.canvasCtx.moveTo(x1, y1);
            this.canvasCtx.lineTo(x2, y2);
            this.canvasCtx.stroke();
            this.canvasCtx.closePath();
            this.canvasCtx.clip();
            this.canvasCtx.clearRect(0,0,this.canvas.width, this.canvas.height);
            this.canvasCtx.restore();
          } else {
            this.canvasCtx.lineCap = "round";
            this.canvasCtx.lineJoin = "round";
            this.canvasCtx.moveTo(x1, y1);
            this.canvasCtx.lineTo(x2, y2);
            this.canvasCtx.stroke();
            this.canvasCtx.closePath();
          }
        },
        drawCircle: function (x, y, radius) {
          this.canvasCtx.save();
          this.canvasCtx.beginPath();
          this.canvasCtx.arc(x, y, radius, 0, Math.PI * 2);
          this.canvasCtx.fill();
        },
        onMouseUp: function () {
          this.painting = false;
          this.uploadImgData();
        },
        onMouseDown: function (e) {
          this.saveHistory();
          let x = e.clientX;
          let y = e.clientY;
          this.lastPoint = {"x": x, "y": y};
          this.painting = true;
          this.drawCircle(x, y, 0);
        },
        onMouseMove: function (e) {
          if (this.painting) {
            let x = e.clientX;
            let y = e.clientY;
            let newPoint = {"x": x, "y": y};
            this.drawLine(this.lastPoint.x, this.lastPoint.y, newPoint.x, newPoint.y);
            this.lastPoint = newPoint;
          }
        },
        loadCanvasData: function (imgBase64Data) {
          let ctx = this.canvasCtx;
          let img = null;
          new Promise((resolve) => {
            img = new Image();
            img.src = imgBase64Data;
            img.onload = function () {
              resolve();
            }
          }).then(() => {
            ctx.drawImage(img, 0, 0);
          });
          console.log("绘制图片完成");
        },
        initWebSocket: function () {
          this.webSocket = new WebSocket("ws://localhost:9100/next_document/websocket/20");
          this.webSocket.onopen = this.onWebSocketOpen;
          this.webSocket.onclose = this.onWebSocketClose;
          this.webSocket.onerror = this.onWebSocketError;
          this.webSocket.onmessage = this.onWebSocketMessage;
        },
        onWebSocketOpen: function () {
          console.log("WebSocket开启");
        },
        onWebSocketClose: function () {
          console.log("WebSocket关闭");
        },
        onWebSocketError: function () {
          console.log("WebSocket错误");
        },
        onWebSocketMessage: function (msg) {
          console.log("WebSocket接收到消息");
          this.loadCanvasData(msg.data);
        },
        uploadImgData: function () {
          let imgData = this.canvas.toDataURL();
          let postData = {
            base64_data: imgData
          }
          this.$axios.post("http://localhost:9100/next_document/ws/drawing", postData)
            .then(function (res) {
              console.log("结果", res.data);
            })
            .catch(function (error) {
              console.log(error);
            });
        },
        changeColor: function (type) {
          this.activeColorButton = type;
          if (type == 'blue') {
            this.brushColor = '#409EFF';
          }
          if (type == 'green') {
            this.brushColor = '#67C23A';
          }
          if (type == 'gray') {
            this.brushColor = '#909399';
          }
          if (type == 'orange') {
            this.brushColor = '#E6A23C';
          }
          if (type == 'pink') {
            this.brushColor = '#F56C6C';
          }
          if (type == 'black') {
            this.brushColor = '#000000';
          }
        },
        clearBoard: function () {
          console.log("清空");
          this.canvasCtx.clearRect(0, 0, this.canvas.width, this.canvas.height);
          this.canvasCtx.fillStyle = "blueviolet";
          this.canvasCtx.fillRect(0, 0, this.canvas.width, this.canvas.height)
          this.canvasCtx.fillStyle = "black";
        },
        changeTools: function (type) {
          if (type != 3 & type != 4 & type != 5 ) {
            this.activeTool = type;
          }
          console.log("工具类型", this.activeTool);
          if (type == '1') {
            this.erase = false;
          }
          if (type == '2') {
            this.erase = true;
          }
          if (type == '3') {
            this.undo();
          }
          if (type == '4') {
            this.clearBoard();
          }
          if (type == '5') {
            // 下载
          }
        },
        saveHistory: function () {
          console.log("保存历史", this.history.length);
          // 保留20条历史数据
          if (this.history.length >= 20) {
            this.history.shift();
          }
          let imgData = this.canvasCtx.getImageData(0, 0, this.canvasWidth, this.canvasHeight);
          this.history.push(imgData);
        },
        undo: function () {
          if (this.history.length == 0) {
            return;
          }
          console.log("上一步");
          let imgData = this.history.pop();
          this.canvasCtx.putImageData(imgData, 0, 0);
        }
      },
      created() {
      },
      mounted() {
        this.initCanvas();
        // this.loadCanvasData();
        this.initWebSocket();
      },
      watch: {
        slider: {
          deep: true,
          handler: function () {
            this.canvasCtx.lineWidth = this.slider.value;
          }
        },
        brushColor: {
          handler: function () {
            this.canvasCtx.fillStyle = this.brushColor;
            this.canvasCtx.strokeStyle = this.brushColor;
          }
        }
      }
    }
</script>

<style scoped>
  #canvas {
    /*background: blueviolet;*/
    position: fixed;
    left: 0;
    top: 0;
  }
  .drawing-board {
    display: block;
    position: absolute;
    left: 0px;
    top: 0px;
    bottom: 0px;
    width: 100%;
    height: 100%;
    overflow: hidden;
  }
  #color-button-group {
    position: relative;
    float: left;
    top: 50%;
    margin-top: -27vh;
  }
  .color-button {
    display: block;
    margin: 3vh;
  }
  #slider {
    position: relative;
    float: right;
    top: 50%;
    margin-top: -15vh;
    vertical-align: center;
  }
  #tools {
    position: fixed;
    bottom: 5vh;
    width:100%;
    display: flex;
    justify-content: center;
    text-align: center
  }
</style>
