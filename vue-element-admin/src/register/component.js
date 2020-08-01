//组件注册器
export default {
  /**
   * 用户权限管理相关
   * @returns {Promise<*>|*}
   */
  //用户管理
  UserManager: () => import('@/views/user-manager/index'),
  CreateUser: () => import('@/views/user-manager/create-user'),
  EditUser: () => import('@/views/user-manager/edit-user'),
  //角色管理
  RoleManager: () => import('@/views/role-manager/index'),
  CreateRole: () => import('@/views/role-manager/create-role'),
  EditRole: () => import('@/views/role-manager/edit-role'),
  //菜单设置
  MenuManager: () => import('@/views/menu-manager/index'),
  //接口管理
  BackendApiManager: () => import('@/views/backendapi-manager/index'),
  CreateApi: () => import('@/views/backendapi-manager/create-api/index'),
  EditApi: () => import('@/views/backendapi-manager/edit-api/index')
}
