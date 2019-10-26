<template>
  <div class="project-panel" :style="imgStyle">
    <div class="project-panel-main">
      <el-col
        v-for="(item, index) in projects"
        :key="index"
        :span="6"
      >
        <router-link
          to="/document_panel"
        >
          <div
            @mouseenter="onMouseIn(index)"
            @mouseout="onMouseOut"
            :class="currentActiveIndex == index ? classPurpleLight : classPurple"
          >
            {{item.projectName}}
          </div>
        </router-link>
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
    <router-link
      to="/drawing_board"
    >
      <div
        class="arrow-panel"
      >
        <img
          @mouseenter="changeArrowStyleOnMouseIn"
          @mouseout="changeArrowStyleOnMouseOut"
          :src="rightArrowUrl"
        />
      </div>
    </router-link>
  </div>
</template>

<script>
    export default {
        data() {
          return {
            classPurple: 'grid-content bg-purple-dark',
            classPurpleLight: 'grid-content bg-purple-light',
            currentActiveIndex: -1,
            projects: [
              {
                id: '1',
                icon: '',
                projectName: '我的日记'
              },
              {
                id: '2',
                icon: '',
                projectName: '项目一'
              },
              {
                id: '3',
                icon: '',
                projectName: '学习笔记'
              },
              {
                id: '4',
                icon: '',
                projectName: '学习笔记'
              },{
                id: '5',
                icon: '',
                projectName: '学习笔记'
              },{
                id: '6',
                icon: '',
                projectName: '学习笔记'
              },{
                id: '7',
                icon: '',
                projectName: '学习笔记'
              },{
                id: '8',
                icon: '',
                projectName: '学习笔记'
              },{
                id: '9',
                icon: '',
                projectName: '学习笔记'
              },{
                id: '10',
                icon: '',
                projectName: '学习笔记'
              },{
                id: '11',
                icon: '',
                projectName: '学习笔记'
              }
              ],
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
            },
            rightArrowUrl: `${require('@/assets/right-arrow-gray.png')}`
          }
        },
      methods: {
          onMouseIn: function (index) {
            this.currentActiveIndex = index;
          },
          onMouseOut: function () {
            this.currentActiveIndex = -1;
          },
        changeArrowStyleOnMouseIn: function () {
          this.rightArrowUrl = `${require('@/assets/right-arrow-blue.png')}`;
        },
        changeArrowStyleOnMouseOut: function () {
          this.rightArrowUrl = `${require('@/assets/right-arrow-gray.png')}`;
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
    /*background-color: brown;*/
    line-height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
