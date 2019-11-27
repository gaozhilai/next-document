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
          <div id="message-list">
            <div
              id="message-more"
            >
              加载更多
            </div>
            <el-card
              class="message-card"
              shadow="hover"
              v-for="message of messages"
              :key="message.id"
            >
              <div slot="header">
                <span>{{message.name}}</span> <span class="message-time">{{message.date}}</span>
              </div>
              <div>
                {{message.content}}
              </div>
            </el-card>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
    export default {
      data() {
        return {
          count: 0,
          messages: [
            {
              id: '1',
              name: '张三',
              date: '2019/11/11 12:00',
              content: 'hello, 大家好'
            },
            {
              id: '2',
              name: '张三',
              date: '2019/11/11 12:00',
              content: 'hello, 大家好'
            },
            {
              id: '3',
              name: '张三',
              date: '2019/11/11 12:00',
              content: 'hello, 大家好'
            },
            {
              id: '4',
              name: '张三',
              date: '2019/11/11 12:00',
              content: 'hello, 大家好'
            },
            {
              id: '5',
              name: '张三',
              date: '2019/11/11 12:00',
              content: 'hello, 大家好'
            }
          ]
        }
      },
      methods: {
        load () {
          this.count += 2
        }
      },
      updated() {
        let messageList = document.getElementById('message-list');
        messageList.scrollTop = messageList.scrollHeight;
        console.log(messageList.scrollTop, messageList.scrollHeight)
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

  .infinite-list {
    padding: 0;
  }

  .infinite-list-item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    background: #e8f3fe;
    margin: 10px;
    color: #7dbcfc;
    list-style: none;
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

  .message-card {
    text-align: left;
    margin: 20px;
  }

  #message-more {
    margin: 20px;
  }

  .message-time {
    color: #606266;
  }

  #message-list {
    width: 100%;
    height: 100%;
    overflow-y: auto;
  }
</style>
