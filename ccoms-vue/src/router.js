import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from './components/LoginPage'
import MenuItems from './components/MenuItems'
import MenuList from './components/MenuList'
import OrderList from './components/OrderList'
import CartComponent from './components/CartComponent'
import StudentList from './components/StudentList'
import StudentProfile from './components/StudentProfile'
import EmployeeList from './components/EmployeeList'
import DiningHallList from './components/DiningHallList'
import OrderListReport from './components/OrderListReport'

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
    path: '/menu-list',
    component: MenuList,
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
  },
  {
    path: '/order-list',
    component: OrderListReport,
    meta: { auth: true }
  }
  
]

const router = new VueRouter({
  mode: 'history',
  routes
});



export default router;
