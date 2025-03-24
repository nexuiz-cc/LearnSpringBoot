<template>
  <div class="login-form">
    <a-form
    :model="formState"
    name="basic"
    :label-col="{ span: 8 }"
    :wrapper-col="{ span: 16 }"
    autocomplete="off"
    @finish="onFinish"
    @finishFailed="onFinishFailed"
  >
    <a-form-item
      label="Username"
      name="userName"
      :rules="[{ required: true, message: 'Please input your username!' }]"
    >
      <a-input v-model:value="formState.userName" />
    </a-form-item>

    <a-form-item
      label="Password"
      name="password"
      :rules="[{ required: true, message: 'Please input your password!' }]"
    >
      <a-input-password v-model:value="formState.password" />
    </a-form-item>

    <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
      <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
    </a-form-item>

    <a-form-item :wrapper-col="{ offset: 12, span: 16 }">
      <a-button type="primary" html-type="submit" class="submit">登录</a-button>
    </a-form-item>
  </a-form>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue';
import { useUserStore } from '@/stores/UserStore'
import { login } from '@/api/api';
import router from '@/router';
interface FormState {
  username: string;
  password: string;
  remember: boolean;
}


interface IRequestBody{
  username: string,
  password: string
}

const userStore = useUserStore();
const formState = reactive<FormState>({
  username: '',
  password: '',
  remember: true,
});

const requestBody = reactive<IRequestBody>({
  username: '',
  password: ''
});

const onFinish = (values: any) => {
  console.log('values:',values);
  requestBody.username = values.userName;
  requestBody.password=values.password
  login(requestBody).then((res)=>{
    userStore.userLogin(requestBody)
    router.push('home')
  })
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
</script>

<style scoped>
.login-form {
  align-content: center;
  justify-content: center;
  width: 500px;
  margin-top: 100px;
  margin-left: -100px;
}
.userName{
  position: relative;
  height: 40px;
}
.password{
  position: relative;
  height: 40px;
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

.submit{
  font-size: 1rem;
  width: 150px;
  cursor: pointer;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
}
</style>
