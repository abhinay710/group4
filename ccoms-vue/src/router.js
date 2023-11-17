import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from './components/LoginPage'
import MenuItems from './components/MenuItems'
import OrderList from './components/OrderList'
import CartComponent from './components/CartComponent'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: LoginPage
  },
  {
    path: '/login',
    component: LoginPage,
    meta: { auth: false }
  },
  {
    path: '/menu-items',
    component: MenuItems,
    meta: { auth: true }
  },
  {
    path: '/orders',
    component: OrderList,
    meta: { auth: true }
  },
  {
    path: '/cart',
    component: CartComponent
  }
]

const router = new VueRouter({
    mode: 'history',
    routes
  });

// router.beforeEach((to, from, next) => {
//   let store = router.app.$options.store; 
//   if (to.meta.auth && !store.getters.isLoggedIn) {
//     next('/login')
//   } else {
//     next()
//   }
// })

export default router;