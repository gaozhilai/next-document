<template>
    <div>
      <el-container
        class="layout"
      >
        <el-aside
          id="aside-layout"
          width="auto"
        >
          <el-menu
            :collapse="isCollapse"
            default-active="2"
            class="el-menu-vertical-demo"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            @select="onSelect"
            router="true"
          >
            <el-menu-item
              @click="isCollapse ? isCollapse = false : isCollapse = true"
            >
              <i :class="isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'"></i>
              <span slot="title">折叠/展开</span>
            </el-menu-item>
            <el-menu-item index="/layout/project_panel">
              <i class="el-icon-s-management"></i>
              <span slot="title">我的项目</span>
            </el-menu-item>
            <el-menu-item index="/layout/project_list">
              <i class="el-icon-wallet"></i>
              <span slot="title">所有项目</span>
            </el-menu-item>
            <el-menu-item index="/layout/drawing_board">
              <i class="el-icon-s-open"></i>
              <span slot="title">来一幅画</span>
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
          isCollapse: true
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
