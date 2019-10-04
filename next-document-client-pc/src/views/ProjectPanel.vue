<template>
  <div class="project-panel" :style="imgStyle">
    <div class="project-panel-main">
      <el-col
        v-for="(item, index) in projects"
        :key="index"
        :span="6"
      >
        <div
          @mouseenter="onMouseIn(index)"
          @mouseout="onMouseOut"
          :class="currentActiveIndex == index ? classPurpleLight : classPurple"
        >
        </div>
      </el-col>
      <el-col
        :span="6"
      >
        <div
          @mouseenter="onMouseIn(11)"
          @mouseout="onMouseOut"
          :class="currentActiveIndex == 11 ? classPurpleLight : classPurple"
          :style="iconStyle"
        >
<!--          <img-->
<!--            @mouseenter="onMouseIn(11)"-->
<!--            @mouseout="onMouseOut"-->
<!--            src="../assets/more-icon.png"/>-->
        </div>
      </el-col>
    </div>
    <div class="arrow-panel">
      <i class="el-icon-d-arrow-right"></i>
    </div>
  </div>
</template>

<script>
    export default {
        data() {
          return {
            classPurple: 'grid-content bg-purple-dark',
            classPurpleLight: 'grid-content bg-purple-light',
            currentActiveIndex: -1,
            projects: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
            backgroundUrl: "background: url('../assets/unsplash.jpg');background-size: cover;",
            imgStyle: {
              backgroundImage: `url(${require('@/assets/unsplash.jpg')})`,
              backgroundSize: 'cover'
            },
            iconStyle: {
              backgroundImage: `url(${require('@/assets/more-icon.png')})`,
              backgroundSize: 'contain',
              backgroundRepeat: 'no-repeat',
              backgroundPosition: 'center'
            }
          }
        },
      methods: {
          onMouseIn: function (index) {
            this.currentActiveIndex = index;
          },
          onMouseOut: function () {
            this.currentActiveIndex = -1;
          }
      }
    }
</script>

<style scoped>
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    /*min-height: 36px;*/
    height: 10rem;
    line-height: 10rem;
    width: 15rem;
    margin: 0.5rem;
  }
  .grid-content img {
    vertical-align: middle;
    width: 100%;
    height: 100%;
  }
  .row-bg {
    /*padding: 10px 0;*/
    background-color: #f9fafc;
  }
  .project-panel {
    display: block;
    position: absolute;
    left: 0px;
    top: 0px;
    bottom: 0px;
    width: 100%;
    height: 100%;
    overflow: hidden;
    /*background-color: aqua;*/
    /*background: url("../assets/unsplash.jpg");*/
  }
  .project-panel-main {
    width: 90%;
    height: 90%;
    text-align: center;
    margin:5%;
    position: absolute;
    left: 0;
    right: 0;
    top:0;
    bottom: 0;
  }
  .arrow-panel {
    height: 100%;
    width: 5%;
    float: right;
    background-color: brown;
  }
</style>
