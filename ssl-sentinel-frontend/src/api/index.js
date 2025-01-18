import request from '@/utils/request'

// user
export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}
export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}

//task
export function addTask(data) {
  return request({
    url: '/task/add',
    method: 'post',
    data
  })
}
export function taskList(data) {
  return request({
    url: '/task/list',
    method: 'post',
    data
  })
}
//history
export function showHistory(id) {
  return request({
    url: '/history/show',
    method: 'get',
    params: {
      id
    }
  })
}

