import request from '@/utils/request.js'
import qs from 'qs'

export function queryUsers(data) {
  return request({
    url: '/userManager/queryUsers',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function queryAllRoles() {
  return request({
    url: '/userManager/queryAllRoles',
    method: 'get'
  });
}

export function addUser(data) {
  return request({
    url: '/userManager/addUser',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function editUser(data) {
  return request({
    url: '/userManager/editUser',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function deleteUsers(data) {
  return request({
    url: '/userManager/deleteUsers',
    method: 'post',
    data
  });
}
