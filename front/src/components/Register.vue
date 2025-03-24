<template>
  <div class="register-form">
    <h2>注册</h2>
    <form @submit.prevent="handleRegister">
      <div class="form-group">
        <label for="username">用户名:</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div class="form-group">
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <button type="submit">注册</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
const username = ref('')
const password = ref('')

const handleRegister = () => {
  // 处理注册逻辑
  console.log('Username:', username.value)
  console.log('Password:', password.value)
}
</script>

<style scoped>
.register-form {
  align-content: center;
  justify-content: center;
  width: 500px;
  margin-top: 200px;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
}

.form-group input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  padding: 0.75rem 1rem;
  font-size: 1rem;
  cursor: pointer;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
}
</style>
