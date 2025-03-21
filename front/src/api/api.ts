import request from './request'

export const login = (userName: string, password: string) => {
  return request({
    url: 'user/login',
    method: 'POST',
    data: {
      userName,
      password,
    },
  })
}
