import request from './request'
interface LoginBody {
  username: string
  password: string
}
enum Role {
  Admin = 'admin',
  Support = 'support',
}
interface RegisterBody {
  id: number
  username: string
  password: string
  role: Role
  email: string
  phone: string
}
export const login = (loginBody: LoginBody) => {
  return request({
    url: 'users/login',
    method: 'POST',
    data: loginBody,
  })
}

export const register = (registerBody: RegisterBody) => {
  return request({
    url: 'users/register',
    method: 'POST',
    data: registerBody,
  })
}

export const getLastId = () => {
  return request({
    url: 'users/lastId',
    method: 'GET',
  })
}

export const getAllProducts = () => {
  return request({
    url: 'products',
    method: 'GET',
  })
}
