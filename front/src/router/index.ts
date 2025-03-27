import { createRouter, createWebHistory } from 'vue-router'
import LoginRegisterPage from '@/views/LoginRegisterPage.vue'
import HomePage from '@/views/HomePage.vue'
import Products from '@/components/Products.vue'
import UserInfo from '@/components/UserInfo.vue'
import Nav3 from '@/components/nav3.vue'
import Nav4 from '@/components/nav4.vue'

const routes = [
  {
    path: '/',
    name: 'auth',
    component: LoginRegisterPage,
    meta: { requiresAuth: false },
  },
  {
    path: '/home',
    name: 'Home',
    component: HomePage,
    children: [
      {
        path: '/products',
        name: 'products',
        component: Products,
      },
      {
        path: '/userInfo',
        name: 'userInfo',
        component: UserInfo,
      },
      {
        path: '/nav3',
        component: Nav3,
      },
      {
        path: '/nav4',
        component: Nav4,
      },
    ],
    meta: { requiresAuth: true },
  },
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
