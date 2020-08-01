import request from '@/utils/request'
import qs from 'qs'

export function addMenu(data) {
  return request({
    url: 'menuManager/addMenu',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function editMenu(data) {
  return request({
    url: 'menuManager/editMenu',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function deleteMenu({id}) {
  return request({
    url: 'menuManager/deleteMenu',
    method: 'post',
    data: qs.stringify({id})
  });
}

export function queryAllBackendApi() {
  return request({
    url: 'menuManager/queryAllBackendApi',
    method: 'get'
  });
}
