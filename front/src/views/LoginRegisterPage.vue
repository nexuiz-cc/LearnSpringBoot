<template>
  <div class="parent">
    <nav class="div1">
      <a-anchor
        class="anchor"
        :items="[
          {
            key: 'login',
            href: 'login',
            title: () => h('span', { style: 'color: red' }, 'ログイン'),
          },
          {
            key: 'register',
            href: 'register',
            title: '新規登録',
          },
        ]"
        @click="handleClick"
      />
    </nav>
    <div class="div2">
      <Login :mode="activeTab" @submit="handleAuthSubmit" />
    </div>
  </div>
</template>

<script setup lang="ts">
import Login from '@/components/Login.vue'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import type { AnchorProps } from 'ant-design-vue'
import { h } from 'vue'

const router = useRouter()
const activeTab = ref('login')
const handleAuthSubmit = (success: boolean) => {
  if (success) {
    router.push('/list')
  }
}

const handleClick: AnchorProps['onClick'] = (e, link) => {
  e.preventDefault()
  activeTab.value = link.href
}
</script>

<style scoped>
.div1 button {
  margin-right: 1rem;
  padding: 0.5rem 1rem;
  border: none;
  background-color: #f1f1f1;
  cursor: pointer;
  border-radius: 4px;
  font-size: 16px;
}

.loginbox {
  width: 215px;
}
.registerbox {
  width: 215px;
}
.loginbox_active {
  border-right: rgb(111, 7, 230) thick solid;
}

.registerbox_active {
  border-right: rgb(111, 7, 230) thick solid;
}
.div1 button.active {
  display: flex;
  background-color: #007bff;
  color: white;
}
#btn {
  display: flex;
  margin-bottom: 15px;
  width: 200px;
  align-content: center;
  justify-content: center;
}

.parent {
  padding-top: 350px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: 1fr;
  grid-column-gap: 0px;
  grid-row-gap: 0px;
}
.div1 {
  grid-area: 1 / 1 / 2 / 2;
  display: flex; /* 使用 Flexbox 布局 */
  justify-content: flex-end; /* 将子元素向右对齐 */
  align-items: end; /* 垂直居中对齐 */
  flex-direction: column; /* 垂直排列子元素 */
  position: relative;
  left: -100px;
  top: -250px;
}
.div2 {
  grid-area: 1 / 2 / 2 / 3;
  align-content: center;
  justify-content: center;
  position: relative;
  top: -120px;
}

.anchor {
  position: relative;
  top: -70px;
}
</style>
