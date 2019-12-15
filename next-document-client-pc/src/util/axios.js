import axios from 'axios'
import router from '@/router/index';
import {getToken} from "./userInfo";
import {errorMsg} from "./notify";

const instance = axios.create({
  baseURL: 'http://127.0.0.1:9100/next_document'
});

// instance.interceptors.request.use(
//   config => {
//     let token = getToken();
//     if (!token) {
//       // 跳转登录页
//       router.push("/login");
//       return;
//     }
//     config.headers.Token = token;
//     return config;
//   }
// );

instance.interceptors.response.use(response => {
  return response;
}, e => {
  if (!e || !e.response) {
    return Promise.reject(e);
  }
  let status = e.response.status;
  let msg = e.response.data.msg;
  if (status === 401) {
    // token错误, 登录过期, 跳转登录页
    router.push("/login");
    return;
  }
  errorMsg(msg, '');
});

export default instance;
