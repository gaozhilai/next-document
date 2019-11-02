<template>
  <div class="project-panel" :style="imgStyle">
    <div class="project-panel-main">
      <el-col
        :span="23"
      >
        <el-col
          v-for="(item, index) in projects"
          :key="index"
          :span="6"
        >
          <router-link
            to="/layout/document_panel"
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
            @click="gotoProjectList"
          >
            <!--          <img-->
            <!--            @mouseenter="onMouseIn(11)"-->
            <!--            @mouseout="onMouseOut"-->
            <!--            src="../assets/more-icon.png"/>-->
          </div>
        </el-col>
      </el-col>
      <el-col
        :span="1"
        class="arrow"
      >
        <router-link
          to="/layout/drawing_board"
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
      </el-col>
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
        },
        gotoProjectList: function () {
          this.$router.push("/layout/project_list");
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
  .project-panel, .project-panel-main, .el-col-23 {
    height: 100%;
  }
  .el-col-6 {
    height: 33.3%;
  }
  .grid-content {
    border-radius: 4px;
    height: 90%;
    margin: 5%;
    display:flex;
    align-items:center;
    justify-content:center;
  }
  .grid-content img {
    vertical-align: middle;
    width: 100%;
    height: 100%;
  }
  .arrow {
    height: 100%;
    display:flex;
    align-items:center;
    justify-content:center;
  }
</style>
