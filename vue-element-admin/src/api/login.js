import request from '@/utils/request'
import qs from 'qs'

export function loginByUsername(userName, password) {
  const data = {
    userName,
    password
  }
  return request({
    url: '/login',
    method: 'post',
    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    data: qs.stringify(data)
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/user/getUserInfo',
    method: 'get',
    params: { token }
  })
}

