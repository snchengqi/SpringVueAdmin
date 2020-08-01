const getters = {
  sidebar: state => state.app.sidebar,
  language: state => state.app.language,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  introduction: state => state.user.introduction,
  status: state => state.user.status,
  menus: state => state.user.menus,
  setting: state => state.user.setting,
  permission_routes: state => state.permission.routes,
  user_menus: state => state.permission.menus,
  addRoutes: state => state.permission.addRoutes,
  errorLogs: state => state.errorLog.logs
}
export default getters
