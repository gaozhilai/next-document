// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import axios from './util/axios';
import ElementUI from 'element-ui';
// 样式重置css
import './css/reset.css'
// 边框css
import './css/border.css'
// 引入主题样式
import './index.less';
// elementUI默认样式
// import 'element-ui/lib/theme-chalk/index.css';
// 引入样式
import 'vue-easytable/libs/themes-base/index.css';
// 导入 table 和 分页组件
import {VTable, VPagination} from 'vue-easytable';
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
import VueCookies from 'vue-cookies';

Vue.use(VueCookies)
import {getThemeName} from "./util/theme";
// use
Vue.use(mavonEditor)
// 注册到全局
Vue.component(VTable.name, VTable)
Vue.component(VPagination.name, VPagination)

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;

Vue.use(ElementUI);

Vue.directive('has', {
  inserted: function (el, binding) {
    if (!permissionJudge(binding.value)) {
      el.parentNode.removeChild(el);
    }

    function permissionJudge(value) {
      // 此处store.getters.getMenuBtnList代表vuex中储存的按钮菜单数据
      let list = JSON.parse(sessionStorage.getItem("RoleAndPermissionList"));
      for (let item of list) {
        if (item === value) {
          return true;
        }
      }
      return false;
    }
  }
});

// 设置当前用户自定义的主题
document.body.className = 'theme-' + getThemeName();

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
});
