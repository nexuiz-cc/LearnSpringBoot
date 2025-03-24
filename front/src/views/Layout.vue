<template>
  <div v-if="username == 'xx'">
    <LoginRegisterPage />
  </div>
  <div v-if="username != 'xx'">
    <HomePage />
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
