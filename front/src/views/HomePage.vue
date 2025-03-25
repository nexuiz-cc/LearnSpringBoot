<template>
  <div>HOME PAGE</div>
  <div><a-button @click="logout">LogOut</a-button></div>
</template>

<script setup lang="ts">
import router from '@/router'
import { useUserStore } from '@/stores/UserStore'
const userStore = useUserStore()
const logout = () => {
  localStorage.setItem('username', 'xx')
  localStorage.setItem('token', 'xx')
  localStorage.setItem('isAuthenticated', 'false')
  userStore.username = 'xx'
  userStore.token = 'xx'
  router.go(0)
}
</script>

<style scoped lang="scss"></style>
