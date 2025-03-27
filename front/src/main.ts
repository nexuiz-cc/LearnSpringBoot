import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Antd from 'ant-design-vue'
import App from './App.vue'
import router from './router'
import '../style.scss'
import { useUserStore } from './stores/UserStore'
const app = createApp(App)
const pinia = createPinia()
app.use(pinia)
const userStore = useUserStore() // 确保这里的 store 名称是正确的
userStore.loadUser()
app.use(createPinia())
app.use(router)
app.use(Antd)
app.mount('#app')
