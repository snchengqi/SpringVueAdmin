
const roles = {
  total: 5,
  rows: [
    {
      id: '1',
      roleName: 'admin',
      name: '管理员',
      createTime: '2019-03-03 17:47:00',
      updateTime: '2019-03-03 15:47:00',
      description: '这是超级管理员',
      menus: [11,12,13,14,15,16,22]
    },
    {
      id: '2',
      roleName: 'user',
      name: '普通用户',
      createTime: '2019-03-03 16:47:00',
      updateTime: '2019-03-03 15:47:00',
      description: '这只是个普通用户',
      menus: [11,22,31]
    },
    {
      id: '3',
      roleName: 'VIP',
      name: '会员',
      createTime: '2019-03-03 17:21:00',
      updateTime: '2019-03-03 15:47:00',
      description: '这我们的大客户',
      menus: [22]
    },
    {
      id: '4',
      roleName: 'buyer',
      name: '采购员',
      createTime: '2019-03-03 15:32:00',
      updateTime: '2019-03-03 15:47:00',
      description: '这是采购员',
      menus: [16,31]
    },
    {
      id: '5',
      roleName: 'customer',
      name: '客服',
      createTime: '2019-03-03 18:47:00',
      updateTime: '2019-03-03 15:47:00',
      description: '这是客服',
      menus: [32,42,44]
    }
  ]
};

const menuTree = [
  {
    id: 1,
    name: 'PermissionManager',
    menuName: '权限管理',
    icon: '',
    alwaysShow: 0,
    level: 1,
    children: [
      {
        id: 11,
        name: 'UserManager',
        menuName: '用户管理',
        path: 'user',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 12,
        name: 'CreateUser',
        menuName: '创建用户',
        path: 'createUser',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 1
      },
      {
        id: 13,
        name: 'EditUser',
        menuName: '编辑用户',
        path: 'editUser',
        icon: '',
        level: 2,
        noCache: 1,
        hidden: 1
      },
      {
        id: 14,
        name: 'RoleManager',
        menuName: '角色管理',
        path: 'role',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 15,
        name: 'CreateRole',
        menuName: '创建角色',
        path: 'createRole',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 1
      },
      {
        id: 16,
        name: 'EditRole',
        menuName: '编辑角色',
        path: 'editRole',
        icon: '',
        level: 2,
        noCache: 1,
        hidden: 1
      }
    ]
  },
  {
    id: 2,
    name: 'PermissionManager',
    menuName: '权限管理',
    icon: '',
    alwaysShow: 0,
    level: 1,
    children: [
      {
        id: 21,
        name: 'UserManager',
        menuName: '用户管理',
        path: 'user',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 22,
        name: 'CreateUser',
        menuName: '创建用户',
        path: 'createUser',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 23,
        name: 'EditUser',
        menuName: '编辑用户',
        path: 'editUser',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 24,
        name: 'RoleManager',
        menuName: '角色管理',
        path: 'role',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 25,
        name: 'CreateRole',
        menuName: '创建角色',
        path: 'createRole',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 26,
        name: 'EditRole',
        menuName: '编辑角色',
        path: 'editRole',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      }
    ]
  },
  {
    id: 3,
    name: 'PermissionManager',
    menuName: '权限管理',
    icon: '',
    alwaysShow: 0,
    level: 1,
    children: [
      {
        id: 31,
        name: 'UserManager',
        menuName: '用户管理',
        path: 'user',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 32,
        name: 'CreateUser',
        menuName: '创建用户',
        path: 'createUser',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 1
      },
      {
        id: 33,
        name: 'EditUser',
        menuName: '编辑用户',
        path: 'editUser',
        icon: '',
        level: 2,
        noCache: 1,
        hidden: 1
      },
      {
        id: 34,
        name: 'RoleManager',
        menuName: '角色管理',
        path: 'role',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 35,
        name: 'CreateRole',
        menuName: '创建角色',
        path: 'createRole',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 1
      },
      {
        id: 36,
        name: 'EditRole',
        menuName: '编辑角色',
        path: 'editRole',
        icon: '',
        level: 2,
        noCache: 1,
        hidden: 1
      }
    ]
  },
  {
    id: 4,
    name: 'PermissionManager',
    menuName: '权限管理',
    icon: '',
    alwaysShow: 0,
    level: 1,
    children: [
      {
        id: 41,
        name: 'UserManager',
        menuName: '用户管理',
        path: 'user',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 42,
        name: 'CreateUser',
        menuName: '创建用户',
        path: 'createUser',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 1
      },
      {
        id: 43,
        name: 'EditUser',
        menuName: '编辑用户',
        path: 'editUser',
        icon: '',
        level: 2,
        noCache: 1,
        hidden: 1
      },
      {
        id: 44,
        name: 'RoleManager',
        menuName: '角色管理',
        path: 'role',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 0
      },
      {
        id: 45,
        name: 'CreateRole',
        menuName: '创建角色',
        path: 'createRole',
        icon: '',
        level: 2,
        noCache: 0,
        hidden: 1
      },
      {
        id: 46,
        name: 'EditRole',
        menuName: '编辑角色',
        path: 'editRole',
        icon: '',
        level: 2,
        noCache: 1,
        hidden: 1
      }
    ]
  }
];

export default {
  queryRoles() {
    return roles;
  },
  addRole() {
    return true;
  },
  editRole() {
    return true;
  },
  getMenuTree() {
    return menuTree;
  },
  updateRolePermission() {
    return true;
  },
  deleteRoles() {
    return true;
  }
}
