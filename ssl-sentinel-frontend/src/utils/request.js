import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'
import { ACCESS_TOKEN, USER_NAME } from './constant'
import router from '../router'

// create an axios instance
const service = axios.create({
  baseURL: import.meta.env.VITE_BASE_API, // 使用环境变量中的 base URL
  timeout: 10000
})

// request interceptor
service.interceptors.request.use(
  config => {
    // Retrieve token and username from localStorage
    
    const token = localStorage.getItem(ACCESS_TOKEN)
    const username = localStorage.getItem(USER_NAME)
    if (token) {
      config.headers[ACCESS_TOKEN] = token
    }
    if (username) {
      config.headers[USER_NAME] = username
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
    const res = response.data

    if (res.code === 200) {
      // 保存token
      localStorage.setItem(ACCESS_TOKEN, response.headers[ACCESS_TOKEN])
      return res
    } else if (res.code === 401) {
      ElMessageBox.confirm('登录失效，请重新登录', '提示', {
        confirmButtonText: '重新登录',
        type: 'warning'
      }).then(() => {
        // 清空localStorage
        localStorage.clear()
        // 重新登录
        router.push('/')
      })

      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      ElMessage({
        message: res.message || 'Error',
        type: 'error',
        duration: 3 * 1000,
        plain: true
      })

      return Promise.reject(new Error(res.message || 'Error'))
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
