import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    name: 'home',
    component: ()=>import('../views/userLogin/LoginPage')
  },
  {
    path: '/regist',
    name: 'regist',
    component: ()=>import('../views/userLogin/RegistPage')
  },
  {
    path: '/',
    name: 'index',
    redirect:'/mainPage',
    component: ()=>import('../views/functionPage/IndexPage'),
    children:[
      {
        path: '/mainPage',
        name: 'main',
        component: ()=>import('../views/functionPage/Pages/MainPage')
      },
      {
        path: '/userPage',
        name: 'user',
        component: ()=>import('../views/functionPage/Pages/UserPage')
      },
      {
        path: '/userManagerPage',
        name: 'userManager',
        component: ()=>import('../views/functionPage/Pages/UserManagerPage')
      },
    ]
  },
  {
    path: '/photoInfo',
    name: 'photoInfo',
    component: ()=>import('../views/functionPage/Pages/PhotosInfoPage')
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
