
import request from './request'
interface IRequestBody{
  username: string,
  password: string
}
export const login = (requestBody:IRequestBody) => {
  return request({
    url: 'users/login',
    method: 'POST',
    data:
      requestBody
    ,
  })
}
