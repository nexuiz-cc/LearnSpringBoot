import { createRouter, createWebHistory } from 'vue-router'
import LoginRegisterPage from '@/views/LoginRegisterPage.vue'
import HomePage from '@/views/HomePage.vue'


const routes = [
  {
    path: '/',
    name: 'auth',
    component: LoginRegisterPage,
    meta: { requiresAuth: false }
  },
  {
    path: '/home',
    name: 'Home',
    component: HomePage,
    meta: { requiresAuth: true }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})
router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true'

  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/')
  } else if (!to.meta.requiresAuth && isAuthenticated) {
    next('/home')
  } else {
    next()
  }
})

export default router
