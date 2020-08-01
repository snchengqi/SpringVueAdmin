import request from '@/utils/request'
import qs from 'qs'

export function queryBackendApis(data) {
  return request({
    url: '/backAPiManager/queryBackApis',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function addBackendApi(data) {
  return request({
    url: '/backAPiManager/addBackendApi',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function editBackendApi(data) {
  return request({
    url: '/backAPiManager/editBackendApi',
    method: 'post',
    data: qs.stringify(data)
  });
}

export function deleteBackendApis(data) {
  return request({
    url: '/backAPiManager/deleteBackendApis',
    method: 'post',
    data
  });
}
