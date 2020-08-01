export const adminMenus =  [
  {
    name: 'PermissionManager',
    alwaysShow: 0,
    icon: 'lock',
    level: 1,
    children: [
      {
        name: 'UserManager',
        path: 'user',
        noCache: 0,
        hidden: 0
      },
      {
        name: 'CreateUser',
        path: 'createUser',
        noCache: 0,
        hidden: 1
      },
      {
        name: 'EditUser',
        path: 'editUser',
        noCache: 1,
        hidden: 1
      },
      {
        name: 'RoleManager',
        path: 'role',
        noCache: 0,
        hidden: 0
      },
      {
        name: 'CreateRole',
        path: 'createRole',
        noCache: 0,
        hidden: 1
      },
      {
        name: 'EditRole',
        path: 'editRole',
        noCache: 1,
        hidden: 1
      },
      {
        name: 'MenuManager',
        path: 'menuManager',
        noCache: 0,
        hidden: 0
      },
      {
        name: 'RolePermission',
        path: 'rolePermission',
        noCache: 1,
        hidden: 0
      }
    ]
  },
  {
    name: 'icon',
    alwaysShow: 0,
    level: 1,
    children: [
      {
        name: 'Icons',
        path: 'index',
        icon: 'icon',
        noCache: 1,
        hidden: 0
      }
    ]
  },
  {
    name: 'Example',
    alwaysShow: 1,
    icon: 'example',
    level: 1,
    children: [
      {
        name: 'CreateForm',
        path: 'create',
        icon: 'edit',
        noCache: 0,
        hidden: 0
      },
      {
        name: 'EditForm',
        path: 'edit/:id(\\d+)',
        noCache: 1,
        hidden: 1
      },
      {
        name: 'ArticleList',
        path: 'list',
        icon: 'list',
        noCache: 0,
        hidden: 0
      }
    ]
  },
  {
    name: 'table',
    alwaysShow: 0,
    level: 1,
    children: [
      {
        name: 'Cqtable',
        path: 'table',
        icon: 'icon',
        noCache: 0,
        hidden: 0
      }
    ]
  }
];

export const editorMenus = [
  {
    name: 'icon',
    alwaysShow: 0,
    level: 1,
    children: [
      {
        name: 'Icons',
        path: 'index',
        icon: 'icon',
        noCache: 1,
        hidden: 0
      }
    ]
  }
];



