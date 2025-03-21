import { login } from '@/api/api'
import type { AxiosResponse } from 'axios'
import { defineStore } from 'pinia'

interface UserState {
  username: string
  token: string
}

export const useUserStore = defineStore('counter', {
  // 初期状態を定義
  state: (): UserState => ({
    username: '',
    token: '',
  }),
  // アクション（状態変更を定義）
  actions: {
    async login(userName: string, password: string) {
      login(userName, password).then((response: AxiosResponse) => {
        response.data.userName = this.username
        response.data.token = this.token
      })
    },

    reset() {
      this.username = ''
      this.token = ''
    },
  },
})
