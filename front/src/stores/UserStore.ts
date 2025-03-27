import { login } from '@/api/api'

import { defineStore } from 'pinia'

interface UserState {
  username: string
  token: string
  userId: number
}
interface IRequestBody {
  username: string
  password: string
}

export const useUserStore = defineStore('UserStore', {
  // 初期状態を定義
  state: (): UserState => ({
    username: '',
    token: '',
    userId: 0,
  }),
  // アクション（状態変更を定義）
  actions: {
    userLogin(requestBody: IRequestBody) {
      login(requestBody).then((res) => {
        this.setUser(res.data.username, res.data.token, res.data.id)
      })
    },
    setUser(username: string, token: string, userId: number) {
      this.username = username
      this.token = token
      // 将用户信息和 token 存储到 localStorage
      localStorage.setItem('username', username)
      localStorage.setItem('token', token)
      localStorage.setItem('userId', userId.toString())
    },
    clearUser() {
      this.username = 'xx'
      this.token = 'xx'
      this.userId = 0
      // 清除 localStorage 中的用户信息和 token
      localStorage.removeItem('username')
      localStorage.removeItem('token')
      localStorage.removeItem('userId')
    },
    loadUser() {
      const username = localStorage.getItem('username')
      const token = localStorage.getItem('token')
      const userId = localStorage.getItem('userId')
      if (userId != null) {
        const userId2 = parseInt(userId, 10)
        if (username && token) {
          this.setUser(username, token, userId2)
        }
      }
    },
  },
})
