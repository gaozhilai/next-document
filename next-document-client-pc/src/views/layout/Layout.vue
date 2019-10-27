<template>
    <div>
      <el-container
        class="layout"
      >
        <el-aside
          id="aside-layout"
          width="120px">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            @select="onSelect"
          >
            <el-menu-item index="2">
              <i class="el-icon-menu"></i>
              <span slot="title"><router-link to="/layout/project_panel">我的项目</router-link></span>
            </el-menu-item>
            <el-menu-item index="3">
              <i class="el-icon-document"></i>
              <span slot="title"><router-link to="/layout/project_list">所有项目</router-link></span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-container
          class="main-layout"
        >
          <el-main>
            <router-view class="aaa"></router-view>
          </el-main>
        </el-container>
      </el-container>
    </div>
</template>

<script>
    export default {
      data() {
        return {

        };
      },
      methods: {
        onSelect(index, indexPath) {
          console.log("index", index);
          console.log("indexPath", indexPath);
        }
      }
    }
</script>

<style scoped>
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
  }

  .layout {
    /*height: 500px;*/
    position:fixed; width: 100%;height: 100%; top: 0; right:0;
  }
  .main-layout {
  }
  #aside-layout {
    /*height: 500px;*/
  }
  /deep/.el-main {
    padding:  10px;
  }
</style>
