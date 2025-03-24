import { createRouter, createWebHistory } from 'vue-router'
import LoginRegisterPage from '@/components/LoginRegisterPage.vue'
import HomePage from '@/views/HomePage.vue'

const routes = [
  {
    path: '/',
    components: {
      user: 'LoginRegisterPage',
      default: 'HomePage',
    },
    redirect: '/login'
  },
  {
    path: '/login',
    component: () => import('@/components/Login.vue'),
  },
  {
    path: '/register',

    component: () => import('@/components/Register.vue'),
  },
  {
    path: '/home',
    component: () => import('@/views/HomePage.vue'),
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
