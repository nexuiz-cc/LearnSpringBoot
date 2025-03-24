<template>
  <div v-if="username == 'xx'">
    <router-view></router-view>
  </div>
  <div v-if="username != 'xx'">
    <router-view name="user"></router-view>
  </div>
</template>

<script setup lang="ts">
import LoginRegisterPage from '@/components/LoginRegisterPage.vue'
import HomePage from './HomePage.vue'
import { computed, onMounted } from 'vue'
const username = computed(() => localStorage.getItem('username'))
onMounted(()=>{
  console.log('username:  ',username);
})

</script>

<style scoped lang="scss"></style>
