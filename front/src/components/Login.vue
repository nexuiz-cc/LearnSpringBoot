<template>
  <form @submit.prevent="handleSubmit" class="auth-form">
    <h2>{{ mode === 'login' ? '登录' : '注册' }}</h2>

    <div class="form-group">
      <label>用户名</label>
      <input v-model="form.username" type="text" required>
    </div>

    <div class="form-group">
      <label>密码</label>
      <input v-model="form.password" type="password" required>
    </div>

    <div v-if="mode === 'register'" class="form-group">
      <label>确认密码</label>
      <input v-model="form.confirmPassword" type="password" required>
    </div>

    <button type="submit">{{ mode === 'login' ? '登录' : '注册' }}</button>

    <p v-if="error" class="error">{{ error }}</p>
  </form>
</template>


<script setup>
import { ref, defineProps, defineEmits } from 'vue'

const props = defineProps({
  mode: {
    type: String,
    default: 'login',
    validator: value => ['login', 'register'].includes(value)
  }
})

const emit = defineEmits(['submit'])

const form = ref({
  username: '',
  password: '',
  confirmPassword: ''
})

const error = ref('')

const handleSubmit = () => {
  error.value = ''

  if (props.mode === 'register' && form.value.password !== form.value.confirmPassword) {
    error.value = '两次密码输入不一致'
    return
  }

  // 模拟认证成功
  localStorage.setItem('isAuthenticated', 'true')
  localStorage.setItem('username', form.value.username)

  emit('submit', true)
}
</script>
<style scoped>
.auth-form {
  max-width: 400px;
  width: 100%;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>
