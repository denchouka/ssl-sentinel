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
export function taskList(params) {
  return request({
    url: '/task/list',
    method: 'get',
    params: params
  })
}

export function changeMfaCode(data) {
  return request({
    url: '/system/admin/changeMfaCode',
    method: 'post',
    data
  })
}
export function fetchUser(params) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: params
  })
}
