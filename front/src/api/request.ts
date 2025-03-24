import axios, { type AxiosResponse, type InternalAxiosRequestConfig, AxiosError } from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:8899',
  timeout: 5000,
})

request.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  // config 就是你的请求
  // 做一些其他的事情 。。。。
  // config.headers = ....

  // 请求放行
  return config
})

// 设置响应拦截
request.interceptors.response.use(
  (response: AxiosResponse) => {
    // 拦截到响应
    // 对响应进行相应的处理。。。

    // 处理完之后就放行
    return response
  },
  (error: AxiosError) => {
    // 多错误进行一个处理
    return Promise.reject(error)
  },
)

export default request
