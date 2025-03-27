<template>
  <a-form
    :model="formState"
    name="basic"
    layout="horizontal"
    autocomplete="off"
    :label-col="{ span: 8 }"
    :wrapper-col="{ span: 16 }"
    @finish="onFinish"
    class="auth-form"
    @finishFailed="onFinishFailed"
  >
    <h2 class="h2">{{ mode === 'login' ? 'ログイン' : '新規登録' }}</h2>

    <a-form-item
      v-if="mode === 'register'"
      label="id"
      class="registerid"
      name="id"
      :rules="[{ required: true, message: 'Please input your username!' }]"
    >
      <a-input v-model:value="formState.id" disabled />
    </a-form-item>
    <a-form-item
      label="ユーザーネーム"
      name="username"
      class="username"
      :rules="[{ required: true, message: 'Please input your username!' }]"
    >
      <a-input v-model:value="formState.username" />
    </a-form-item>

    <a-form-item
      label="パスワード"
      name="password"
      class="password"
      :rules="[{ required: true, message: 'Please input your password!' }]"
    >
      <a-input-password v-model:value="formState.password" />
    </a-form-item>

    <a-form-item
      v-if="mode === 'register'"
      label="パスワード確認"
      name="confirmPassword"
      class="confirmPassword"
      :rules="[{ required: true, message: 'Please confirm your password!' }]"
    >
      <a-input-password v-model:value="formState.confirmPassword" />
    </a-form-item>

    <a-form-item
      v-if="mode === 'register'"
      label="メールアドレス"
      name="email"
      class="email"
      :rules="[{ required: true, message: 'Please confirm your email!' }]"
    >
      <a-input-password v-model:value="formState.confirmPassword" />
    </a-form-item>

    <a-form-item
      v-if="mode === 'register'"
      label="ロール"
      name="role"
      class="role"
      :rules="[{ required: true, message: 'Please confirm your role!' }]"
    >
      <a-select
        ref="select"
        v-model:value="formState.role"
        style="width: 120px"
        @focus="focus"
        @change="handleChange"
      >
        <a-select-option value="admin">Admin</a-select-option>
        <a-select-option value="support">Support</a-select-option>
        <a-select-option value="sales">Sales</a-select-option>
      </a-select>
    </a-form-item>

    <a-form-item name="remember" class="remember">
      <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
    </a-form-item>

    <a-form-item>
      <a-button type="primary" html-type="submit" class="submit">{{
        mode === 'login' ? 'ログイン' : '新規登録'
      }}</a-button>
    </a-form-item>
  </a-form>
</template>

<script setup lang="ts">
import { getLastId, login } from '@/api/api'
import router from '@/router'
import { ref, defineProps, defineEmits, reactive, onMounted } from 'vue'

interface RequestBody {
  username: string
  password: string
}

interface FormState {
  id: number
  username: string
  password: string
  confirmPassword: string
  remember: boolean
  role: string
}

const formState = reactive<FormState>({
  id: 0,
  username: 'Louise Stewart',
  password: '123456',
  confirmPassword: '',
  role: '',
  remember: true,
})
const props = defineProps({
  mode: {
    type: String,
    default: 'login',
    validator: (value: string) => ['login', 'register'].includes(value),
  },
})

const emit = defineEmits(['submit'])

const form = ref({
  id: 0,
  username: 'Louise Stewart',
  password: '123456',
  confirmPassword: '123456',
})

const focus = () => {
  console.log('focus')
}

const handleChange = (value: string) => {
  formState.role = value
}

const error = ref('')
const requestBody: RequestBody = {
  username: form.value.username,
  password: form.value.password,
}
const onFinish = () => {
  if (props.mode === 'register' && form.value.password !== form.value.confirmPassword) {
    error.value = '两次密码输入不一致'
    return
  }

  if (props.mode === 'login') {
    login(requestBody).then((response) => {
      localStorage.setItem('username', response.data.username)
      localStorage.setItem('userId', response.data.userId)
      localStorage.setItem('token', response.data.token)
      router.push('/products')
    })
  }

  error.value = ''

  // 模拟认证成功
  localStorage.setItem('isAuthenticated', 'true')
  localStorage.setItem('username', form.value.username)

  emit('submit', true)
}

onMounted(() => {
  getLastId().then((response) => {
    formState.id = response.data.id
  })
})
const onFinishFailed = (errorInfo: unknown) => {
  console.log('Failed:', errorInfo)
}
</script>
<style scoped>
.auth-form {
  max-width: 600px;
  height: 560px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 15px;
}

.remember {
  position: relative;
  top: -20px;
}

.role,
.email,
.registerid,
.password,
.username,
.confirmPassword {
  width: 400px;
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

.submit {
  width: 60%;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  position: relative;
  top: -20px;
  left: 155px;
}

.error {
  color: red;
  margin-top: 10px;
}
.h2 {
  position: relative;
  top: -20px;
}
</style>
