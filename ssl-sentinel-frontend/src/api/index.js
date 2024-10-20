import request from '@/utils/request'

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
