<template>
  <div class="user-manager-container">
    <el-card shadow="hover" class="user-manager-form">
      <form-query :size="size" @query="query"></form-query>
    </el-card>
    <el-card shadow="hover">
      <vea-table-simple
        :data="data"
        total-name="totalElements"
        rows-name="content"
        :page-change="pageChange"
        :current-page="pager.currentPage"
        :size="size"
        :viewAble="false"
        :show-message="false"
        row-key="id"
        @add="handleAdd"
        @modify="handleEdit"
        @delete="handleDelete">
        <el-table-column
          prop="userName"
          label="用户名">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名">
        </el-table-column>
        <el-table-column
          label="性别">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.sex === 0">男</el-tag>
            <el-tag v-else-if="scope.row.sex === 1" type="danger">女</el-tag>
            <el-tag v-else type="warning">未知</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="状态">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.isLock === 0" type="success">未锁定</el-tag>
            <el-tag v-else type="info">已锁定</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="角色">
          <template slot-scope="scope">
            <template v-if="scope.row.roles && scope.row.roles.length > 0">
              <el-popover trigger="hover" placement="top">
                <el-table :data="scope.row.roles" :show-header="false" :size="size" @cell-click="roleCellClick">
                  <el-table-column class="pointer-class" prop="name" label="名称"></el-table-column>
                </el-table>
                <el-tag slot="reference">{{scope.row.roles[0].name}}</el-tag>
              </el-popover>
            </template>
          </template>
        </el-table-column>
        <el-table-column
          label="创建人"
          prop="createdBy">
        </el-table-column>
        <el-table-column
          label="修改人"
          prop="modifiedBy">
        </el-table-column>
        <el-table-column
          sortable
          :show-overflow-tooltip="true"
          min-width="100"
          prop="createdTime"
          label="创建时间">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span>{{scope.row.createdTime}}</span>
          </template>
        </el-table-column>
        <el-table-column
          sortable
          :show-overflow-tooltip="true"
          min-width="100"
          prop="modifiedTime"
          label="修改时间">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span>{{scope.row.modifiedTime}}</span>
          </template>
        </el-table-column>
      </vea-table-simple>
    </el-card>
  </div>
</template>

<script>
  import FormQuery from './form-query/index'
  import VeaTableSimple from '@/components/VeaTableSimple'
  import {queryUsers, deleteUsers} from "@/api/userManager";

  export default {
    name: "UserManager",
    components: {FormQuery, VeaTableSimple},
    data() {
      return {
        data: {},
        user: {},
        pager: {
          currentPage: 1,
          pageSize: 10
        },
        size:'small'
      }
    },
    methods: {
      pageChange({currentPage, pageSize}) {
        this.pager = {currentPage, pageSize};
        this.search(true);
        return this.data;
      },
      search(showLoading) {
        let loading;
        if (showLoading) {
          loading = this.$loading({
            target: this.$el,
            background: 'rgba(0,0,0,0)'
          });
        }
        const params = {...this.user, ...this.pager};
        queryUsers(params).then(res => {
          if (showLoading) {
            loading.close();
          }
          this.data = res.data;
        }).catch(err => {
          if (showLoading) {
            loading.close();
          }
        });
      },
      query(user) {
        this.user = user;
        this.pager.currentPage = 1;
        this.search(true);
      },
      handleAdd() {
        this.$router.push({name: 'CreateUser'});
      },
      handleEdit(user) {
        this.$router.push({name: 'EditUser', query: {user}});
      },
      handleDelete(users) {
        this.$confirm('此操作将永久删除用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const loading = this.$loading({
            target: this.$el,
            // spinner: 'el-icon-loading',
            background: 'rgba(0,0,0,0)',
          });
          deleteUsers(users).then(res => {
            loading.close();
            this.$notify({
              type: 'success',
              title: '成功',
              message: '删除成功'
            });
            this.search(true);
          }).catch(err => {
            loading.close()
          });
        });
      },
      roleCellClick(row, column, cell, event) {
        this.$router.push({name: 'RoleManager', query: {id: row.id}});
      }
    },
    mounted() {
      this.search();
    }
  }
</script>

<style lang="scss" scoped>
  .user-manager-container {
    min-height: calc(100vh - 84px);
    background-color: rgb(240, 242, 245);
    padding: 20px;

    .user-manager-form {
      margin-bottom: 20px;
      padding-top: 20px;
    }
  }

  .pointer-class {
    cursor: pointer;
  }
</style>
