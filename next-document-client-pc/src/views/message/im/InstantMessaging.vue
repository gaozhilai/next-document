<template>
  <div id="instant-messaging" style="height: 100%">
    <el-container>
      <el-aside
        id="im-aside"
      >
        <ul class="infinite-list" v-infinite-scroll="load">
          <li v-for="i in count" class="infinite-list-item">{{ i }}群组名称</li>
        </ul>
      </el-aside>
      <el-container direction="vertical">
        <el-header id="im-header">
          <el-row :gutter="20">
            <el-col :span="20">
              <div id="im-header-left">
                当前群组标题
              </div>
            </el-col>
            <el-col :span="4">
              <div id="im-header-right">
                <el-button id="online-user-number" icon="el-icon-user" round>23</el-button>
              </div>
            </el-col>
          </el-row>
        </el-header>
        <el-main id="im-main">

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
    export default {
      data() {
        return {
          count: 0
        }
      },
      methods: {
        load () {
          this.count += 2
        }
      }
    }
</script>

<style scoped>
  #im-aside {
    background-color: maroon;
  }

  #im-header {
    background-color: #67C23A;
    line-height: 60px;
  }

  #im-main, .el-container {
    background-color: #409EFF;
    padding: 0px;
    margin: 0px;
    height: 100%;
  }

  .infinite-list .infinite-list-item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    background: #e8f3fe;
    margin: 10px;
    color: #7dbcfc;
  }

  #online-user-number {

  }

  #im-header-left {
    min-height: 1px;
    text-align: left;
  }

  #im-header-right {
    min-height: 1px;
    text-align: right;
  }
</style>
