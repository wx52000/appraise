// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import router from './router'
import Vue from 'vue';
import App from './App.vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Axios from 'axios';
import 'font-awesome/css/font-awesome.min.css';
import 'babel-polyfill';

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.prototype.$axios = Axios;
Vue.prototype.$baseUrl = 'http://localhost:9999/';
// Vue.prototype.$baseUrl = 'http://10.136.238.22:9999/';
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  render: h => h(App),
})
