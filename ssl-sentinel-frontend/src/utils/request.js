import axios from 'axios'
import { ElMessage } from 'element-plus'

// create an axios instance
const service = axios.create({
  baseURL: 'http://localhost:8882', 
  timeout: 10000
})

// request interceptor
service.interceptors.request.use(
  config => {
    const token = localStorage.getItem('authToken')
    if (token) {
      config.headers.Authorization = token
    }
    return config
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    console.log("response = ", response)
    const res = response.data

    if (res.code !== 200) {
      ElMessage({
        message: res.message || 'Error',
        type: 'error',
        duration: 3 * 1000,
        plain: true
      })

      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      // 保存token
      localStorage.setItem('authToken', null)
      return res
    }
  },
  error => {
    console.log('err' + error)
    ElMessage({
      message: error.message,
      type: 'error',
      duration: 3 * 1000,
      plain: true
    })
    return Promise.reject(error)
  }
)

export default service
