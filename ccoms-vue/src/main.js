import Vue from 'vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import App from './App.vue'
import  router  from './router'
import store from './store'

Vue.config.productionTip = false

new Vue({
  store,
  router: router,
  render: h => h(App)
}).$mount('#app')
