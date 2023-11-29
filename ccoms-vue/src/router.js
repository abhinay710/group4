import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from './components/LoginPage'
import MenuItems from './components/MenuItems'
import OrderList from './components/OrderList'
import CartComponent from './components/CartComponent'
import StudentList from './components/StudentList'
import StudentProfile from './components/StudentProfile'
import EmployeeList from './components/EmployeeList'
import DiningHallList from './components/DiningHallList'

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
    path: '/my-profile',
    name: 'StudentProfile',
    component: StudentProfile,
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
    component: CartComponent,
    meta: { auth: true }
  },
  {
    path: '/student-list',
    component: StudentList,
    meta: { auth: true }
  },
  {
    path: '/employee-list',
    component: EmployeeList,
    meta: { auth: true }
  },
  {
    path: '/dining-hall-list',
    component: DiningHallList,
    meta: { auth: true }
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
});

// Add your navigation guard here if needed
// router.beforeEach((to, from, next) => {
//   let store = router.app.$options.store; 
//   if (to.meta.auth && !store.getters.isLoggedIn) {
//     next('/login')
//   } else {
//     next()
//   }
// })

export default router;
