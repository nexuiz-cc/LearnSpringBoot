import { createRouter, createWebHistory } from 'vue-router'
import LoginRegisterPage from '@/components/LoginRegisterPage.vue'

const routes = [
  {
    path: '/',
    component: LoginRegisterPage,
  },
  {
    path: '/login',
    component: () => import('@/components/Login.vue'),
  },
  {
    path: '/register',
    component: () => import('@/components/Register.vue'),
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
