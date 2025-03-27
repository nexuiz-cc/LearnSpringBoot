<template>
  <a-layout>
    <a-layout-sider
      breakpoint="lg"
      collapsed-width="0"
      @collapse="onCollapse"
      @breakpoint="onBreakpoint"
    >
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <router-link to="/dashboard">レポート</router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <router-link to="/products">商品一覧</router-link>
        </a-menu-item>
        <a-menu-item key="3">
          <router-link to="/userInfo">客先一覧</router-link>
        </a-menu-item>
        <a-menu-item key="4">
          <router-link to="/userInfo">スタッフ一覧</router-link>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header :style="{ background: 'white', padding: 0, height: '50px' }">
        <h2 class="h2">welcome: {{ username }}</h2>
        <a-button type="primary" class="logout" @click="logout">LogOut</a-button>
      </a-layout-header>

      <a-layout-content>
        <div :style="{ padding: '24px', background: '#fff', minHeight: '966px', color: 'black' }">
          <router-view></router-view>
        </div>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script setup lang="ts">
import router from '@/router'
import { useUserStore } from '@/stores/UserStore'
import { computed, onMounted, ref } from 'vue'
const userStore = useUserStore()
const username = computed(() => userStore.username)
const logout = () => {
  localStorage.setItem('username', 'xx')
  localStorage.setItem('token', 'xx')
  localStorage.setItem('isAuthenticated', 'false')
  userStore.username = 'xx'
  userStore.token = 'xx'
  router.go(0)
}

onMounted(() => {
  userStore.loadUser()
})
const onCollapse = (collapsed: boolean, type: string) => {
  console.log(collapsed, type)
}

const onBreakpoint = (broken: boolean) => {
  console.log(broken)
}

const selectedKeys = ref<string[]>(['2'])
</script>
<style scoped>
.logout {
  position: relative;
  top: -80px;
  left: 1750px;
}

.h2 {
  display: flex;
  margin-left: 20px;
  margin-top: -10px;
}
#components-layout-demo-responsive .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}

.site-layout-sub-header-background {
  background: #fff;
}

.site-layout-background {
  background: #fff;
}

[data-theme='dark'] .site-layout-sub-header-background {
  background: #141414;
}
</style>
