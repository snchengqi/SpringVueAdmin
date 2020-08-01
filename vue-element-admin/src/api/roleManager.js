import request from '@/utils/request.js'
import qs from 'qs'

export function queryRoles(data) {
  return request({
    url: '/roleManager/queryRoles',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function addRole(data) {
  return request({
    url: '/roleManager/addRole',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function editRole(data) {
  return request({
    url: '/roleManager/editRole',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function getMenuTree() {
  return request({
    url: '/menuManager/getMenuTree',
    method: 'get'
  });
}

export function updateRolePermission(data) {
  return request({
    url: '/roleManager/editRole',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function deleteRoles(data) {
  return request({
    url: '/roleManager/deleteRoles',
    method: 'post',
    data
  });
}
