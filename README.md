# SpringVueAdmin
很久以前使用Spring Security和Vue开发的练手项目，基于RABC实现权限控制，可页面配置的接口和菜单访问权限，对一些常用组件的封装。可以作为项目脚手架。

## 结构

vue-spring-admin --后端源码

vue-elment-admin --前端源码

## 环境

后端要求JDK1.8及以上

前端要求安装Node环境

## 技术选型

### 后端

SpringBoot

SpringSecurity

SringDataJpa

MySQL

### 前端

Vue

Element

vue-element-admin

## 快速构建

- clone本项目到你本地之后，执行（vue_element_admin.sql）DDL和初始化基础数据到你的数据库。

- 修改后端代码的配置文件数据库连接信息，如下图：

  ![image-20200801100856576](C:\Users\chengqi\AppData\Roaming\Typora\typora-user-images\image-20200801100856576.png)

- 然后启动后端服务

- 启动前端（数据库初始化了两个管理员账号：super/super  admin/admin）

  ![image-20200801101216467](typora-user-images\image-20200801101216467.png)

## 二次开发

项目使用RABC权限管理，并且提供了可在页面配置用户，角色，资源的功能，你只需要专注自己的业务开发。

- 开发好接口和前端页面之后，首先将Vue组件注册到components.js文件

![image-20200801101809448](typora-user-images\image-20200801101809448.png)

- 然后配置菜单国际化文本

  ![image-20200801102000922](typora-user-images\image-20200801102000922.png)

- 在接口管理界面注册你的接口

  ![image-20200801102352811](typora-user-images\image-20200801102352811.png)

- 注册好接口之后，在菜单设置界面注册你的一级菜单和二级菜单（绑定接口）

![image-20200801102137687](typora-user-images\image-20200801102137687.png)

![image-20200801102228897](typora-user-images\image-20200801102228897.png)

- 在角色管理界面为角色赋予对应的资源权限

  ![image-20200801102558504](typora-user-images\image-20200801102558504.png)

- 为用户绑定角色关系

  ![image-20200801102658074](typora-user-images\image-20200801102658074.png)

  至此，所有操作就完成了，登录的用户只展示拥有访问权限的菜单，并且不可绕过前端控制直接访问接口。

  如果你觉得对你有用的话，star一下吧~~