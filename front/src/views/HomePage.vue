<template>
  <div>HOME PAGE</div>
  <div><a-button @click="logout">LogOut</a-button></div>

</template>


<script setup lang="ts">
import { useUserStore } from '@/stores/UserStore'
const userStore = useUserStore();
const logout =()=>{
  localStorage.setItem('username','xx')
  localStorage.setItem('token','xx')
  userStore.username='xx'
  userStore.token='xx'
  location.reload();
}
</script>

<style scoped lang="scss"></style>
