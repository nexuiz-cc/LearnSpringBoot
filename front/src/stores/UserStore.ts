import { login } from '@/api/api'

import { defineStore } from 'pinia'

interface UserState {
  username: string
  token: string
}
interface IRequestBody{
  username: string,
  password: string
}

export const useUserStore = defineStore('counter', {
  // 初期状態を定義
  state: (): UserState => ({
    username: '',
    token: '',
  }),
  // アクション（状態変更を定義）
  actions: {
    userLogin(requestBody:IRequestBody) {
      login(requestBody).then((res) => {
        this.setUser(res.data.username, res.data.token)
      })
    },
    setUser(username: string, token: string) {
      this.username = username
      this.token = token
      // 将用户信息和 token 存储到 localStorage
      localStorage.setItem('username', username)
      localStorage.setItem('token', token)
    },
    clearUser() {
      this.username = null
      this.token = null
      // 清除 localStorage 中的用户信息和 token
      localStorage.removeItem('username')
      localStorage.removeItem('token')
    },
    loadUser() {
      const username = localStorage.getItem('username')
      const token = localStorage.getItem('token')
      if (username && token) {
        this.setUser(username, token)
      }
    },
  },
})
