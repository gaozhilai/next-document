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
          <!--存在快捷方式时布局-->
          <div
            v-if="item.id"
            :class="currentActiveIndex == index ? classPurpleLight : classPurple"
          >
            <div class="shortcut-delete">
              <el-button class="delete-icon" icon="el-icon-delete" @click="deleteShortcut(item.shortcut_index)"></el-button>
            </div>
            <div
              class="shortcut-project"
              @mouseenter="onMouseIn(index)"
              @mouseout="onMouseOut"
              @click="gotoDocumentPanel"
            >
              {{item.project_name}}
            </div>
          </div>
          <!--不存在快捷方式时布局-->
          <div
            v-if="!item.id"
            :class="currentActiveIndex == index ? classPurpleLight : classPurple"
          >
            <div class="non-shortcut"
                 @mouseenter="onMouseIn(index)"
                 @mouseout="onMouseOut"
                 @click="addProject(index)"
            >
              <i class="el-icon-plus more"
                 @mouseenter="onMouseIn(index)"
              ></i>
            </div>
          </div>
        </el-col>
        <el-col
          :span="6"
        >
          <div
            @mouseenter="onMouseIn(11)"
            @mouseout="onMouseOut"
            :class="currentActiveIndex == 11 ? classPurpleLight : classPurple"
            @click="gotoProjectList"
          >
            <div class="non-shortcut">
              <i class="el-icon-more more"></i>
            </div>
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
    import {successMsg} from "../../util/notify";

    export default {
        data() {
          return {
            classPurple: 'grid-content bg-purple-dark',
            classPurpleLight: 'grid-content bg-purple-light',
            currentActiveIndex: -1,
            projects: [
              {
                shortcut_index: 1
              },
              {
                shortcut_index: 2
              },
              {
                shortcut_index: 3
              },
              {
                shortcut_index: 4
              },{
                shortcut_index: 5
              },{
                shortcut_index: 6
              },{
                shortcut_index: 7
              },{
                shortcut_index: 8
              },{
                shortcut_index: 9
              },{
                shortcut_index: 10
              },{
                shortcut_index: 11
              }
              ],
            backgroundUrl: "background: url('../assets/unsplash.jpg');background-size: cover;",
            imgStyle: {
              backgroundImage: `url(${require('@/assets/unsplash.jpg')})`,
              backgroundSize: 'cover'
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
        },
        gotoDocumentPanel: function () {
          this.$router.push("/layout/document_panel");
        },
        addProject: function(index) {
          this.$router.push("/layout/project_list?index=" + index);
        },
        getProjectShortcut: function () {
          this.$axios.get("/project_shortcut").then(res => {
            let data = res.data.data;
            data.forEach(ele => {
              let shortcutIndex = ele.shortcut_index;
              this.projects.splice(shortcutIndex, 1, ele);
            });
          });
        },
        deleteShortcut: function (shortcutIndex) {
          this.$axios.delete("/project_shortcut/" + shortcutIndex).then(res => {
            this.projects.splice(shortcutIndex, 1, {shortcut_index: shortcutIndex});
            successMsg(res.data.msg);
          });
        }
      },
      created() {
        this.getProjectShortcut();
      }
    }
</script>

<style scoped>
  .el-col {
    border-radius: 4px;
    position: relative;
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
  .more {
    font-size: 50px;
  }
  .shortcut-delete {
    height: 1%;
    text-align: right;
  }
  .shortcut-project {
    height: 99%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .delete-icon {
    background-color: #909399;
  }
  .non-shortcut {
    height: 99%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
</style>
