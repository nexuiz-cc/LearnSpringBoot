<template>
  <div class="parent">
    <nav class="div1">
      <button @click="toLogin()" id="btn" :class="{ active: currentView === 'login' }">登录</button>
      <button @click="toRegister()" id="btn" :class="{ active: currentView === 'register' }">
        注册
      </button>
    </nav>
    <div class="div2">
      <router-view v-slot="{ Component }">
        <component :is="Component"></component>
      </router-view>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()

const currentView = ref('login')

const toLogin = () => {
  currentView.value = 'login'
  router.push('/login')
}

const toRegister = () => {
  currentView.value = 'register'
  router.push('/register')
}

onMounted(() => {
  if (route.path === '/register') {
    currentView.value = 'register'
  }
})
</script>

<style scoped>
.div1 button {
  margin-right: 1rem;
  padding: 0.5rem 1rem;
  border: none;
  background-color: #f1f1f1;
  cursor: pointer;
  border-radius: 4px;
  font-size: 16px;
}

.div1 button.active {
  display: flex;
  background-color: #007bff;
  color: white;
}
#btn {
  display: flex;
  margin-bottom: 15px;
  width: 200px;
  align-content: center;
  justify-content: center;
}

.parent {
  padding-top: 350px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: 1fr;
  grid-column-gap: 0px;
  grid-row-gap: 0px;
}
.div1 {
  grid-area: 1 / 1 / 2 / 2;
  display: flex; /* 使用 Flexbox 布局 */
  justify-content: flex-end; /* 将子元素向右对齐 */
  align-items: end; /* 垂直居中对齐 */
  flex-direction: column; /* 垂直排列子元素 */
  position: relative;
  left: -100px;
  top: -50px;
}
.div2 {
  grid-area: 1 / 2 / 2 / 3;
  align-content: center;
  justify-content: center;
}
</style>
