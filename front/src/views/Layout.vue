<template>
  <div v-if="username == ''">
    <LoginRegisterPage />
  </div>
  <div v-if="username != ''">
    <HomePage />
  </div>
</template>

<script setup lang="ts">
import LoginRegisterPage from '@/components/LoginRegisterPage.vue'
import HomePage from './HomePage.vue'
import { computed } from 'vue'
import { useUserStore } from '@/stores/userStore'

const username = computed(() => userStore.username)
const userStore = useUserStore()
</script>

<style scoped lang="scss"></style>
