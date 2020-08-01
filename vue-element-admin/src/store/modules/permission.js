import { asyncRoutes, constantRoutes } from '@/router'
/* Layout */
import Layout from '@/views/layout/Layout'
import components from '@/register/component'
import {notFoundRoute} from "@/router/modules/notFound";

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(menus) {
  const res = [];

  function generateRoute(menu) {
    const route = {
      name: menu.name,
      alwaysShow: menu.alwaysShow === 1,
      hidden: menu.hidden === 1,
      meta: {
        title: menu.name,
        icon: menu.icon,
        noCache: menu.noCache === 1,
        isSubMenu: menu.level === 1
      }
    };
    return route;
  }

  function generateSubRoutes(subMenus = [], children) {
    subMenus.forEach(menu => {
      const route = generateRoute(menu);
      route.path = menu.path;
      route.component = components[menu.name];
      children.push(route);
    });
  }

  menus.forEach(menu => {
    const children = [];
    generateSubRoutes(menu.children, children);
    const route = generateRoute(menu);
    route.path = '/' + menu.name;
    route.redirect = 'dashboard';
    route.component = Layout;
    route.children = children;

    res.push(route);
  });

  //添加404路由
  res.push(notFoundRoute);

  return res
}

const permission = {
  state: {
    routes: [],
    addRoutes: [],
  },
  mutations: {
    SET_ROUTES: (state, routes) => {
      state.addRoutes = routes
      state.routes = constantRoutes.concat(routes)
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        const { menus } = data
        let accessedRoutes
        accessedRoutes = filterAsyncRoutes(menus)
        commit('SET_ROUTES', accessedRoutes)
        resolve(accessedRoutes)
      })
    }
  }
}

export default permission
