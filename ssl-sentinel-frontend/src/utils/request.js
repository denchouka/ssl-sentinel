import axios from 'axios'
import { ElMessage } from 'element-plus'
import { ACCESS_TOKEN, USER_NAME } from './constant'

// create an axios instance
const service = axios.create({
  baseURL: 'http://localhost:8882', 
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
    console.log("response = ", response)
    const res = response.data

    if (res.code === 401) {
      console.error('token失效')
      // 重新登录
      that.$router.push('/login')
    }

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
      localStorage.setItem(ACCESS_TOKEN, response.headers[ACCESS_TOKEN])
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
