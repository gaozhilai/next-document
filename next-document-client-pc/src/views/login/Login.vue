<template>
  <div id="login">
    <el-card id="login-card" shadow="always">
      <div slot="header">
        <span>Next Document</span>
      </div>
      <div id="login-card-body">
        <div id="login-box">
          <div id="login-name">
            <el-input v-model="input" placeholder="请输入用户名"></el-input>
          </div>
          <div id="password">
            <el-input v-model="input" placeholder="请输入密码"></el-input>
          </div>
          <div id="button-box">
            <el-button type="primary">登录</el-button>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "Login"
    }
</script>

<style scoped>
  #login {
    background-color: #67C23A;
    height: 100%;
    display: flex;
    justify-items: center;
    justify-content: center;
    align-items: center;
  }
  #login-card {
    width: 50%;
    max-width: 400px;
  }
  #button-box {
    text-align: right;
    margin-top: 5px;
  }
  #login-box {
    background-color: rgb(250, 250, 250);
    width: 75%;
    margin-left:auto;
    margin-right:auto;
    min-width: 300px;
  }
  #login-card-body {
    position: relative;
  }
  #login-name {
    margin-bottom: 5px;
  }
  #password {
    margin-top: 5px;
  }
</style>
