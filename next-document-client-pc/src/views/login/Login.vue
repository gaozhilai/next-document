<template>
  <div id="login">
    <el-card id="login-card" shadow="always">
      <div slot="header">
        <span>Next Document</span>
      </div>
      <div id="login-card-body">
        <div id="login-box">
          <div id="login-name">
            <el-input v-model="formData.loginName" placeholder="请输入用户名"></el-input>
          </div>
          <div id="password">
            <el-input v-model="formData.password" placeholder="请输入密码"></el-input>
          </div>
          <div id="button-box">
            <el-button type="primary" @click="login">登录</el-button>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
    import {successMsg} from "../../util/notify";
    import {setPermission, setRole, setToken} from "../../util/userInfo";

    export default {
      data() {
        return {
          formData: {
            loginName: '',
            password: '',
          }
        };
      },
      methods: {
        login: function () {
          this.$axios.get("/user/login", {
            params: {
              login_name: this.formData.loginName,
              password: this.formData.password
            }
          }).then(res => {
            if (!res) {
              return;
            }
            let data = res.data;
            let msg = data.msg;
            successMsg(msg, '登录成功');
            let token = data.data.token;
            setToken(token);
            let roleAndPermission = data.data.role_and_permission;
            let roles = roleAndPermission.roles;
            let permissions = roleAndPermission.permissions;
            setRole(roles);
            setPermission(permissions);
            this.$router.push('/layout/project_panel');
          });
        }
      }
    }
</script>

<style scoped>
  #login {
    background: linear-gradient(45deg, #fff, #333);
    height: 100%;
    display: flex;
    justify-items: center;
    justify-content: center;
    align-items: center;
  }
  #login-card {
    width: 50%;
    max-width: 420px;
    min-width: 380px;
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
