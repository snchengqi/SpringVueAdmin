<template>
  <div class="role-manager-container">
    <el-card shadow="hover" class="role-manager-form">
      <form-query :size="size" @query="handleQuery"></form-query>
    </el-card>
    <el-card shadow="hover">
      <vea-table-simple
        :data="data"
        total-name="totalElements"
        rows-name="content"
        :page-change="pageChange"
        :current-page="pager.currentPage"
        :viewAble="false"
        row-key="id"
        :expand="true"
        :size="size"
        @add="handleAdd"
        @modify="handleModify"
        @delete="handleDelete">
        <template slot="expand">
          <el-table-column
            type="expand">
            <template slot-scope="scope">
              <el-form :size="size" inline label-position="left" label-width="90px" class="table-expand">
                <el-form-item label="角色名">
                  <span>{{scope.row.roleName}}</span>
                </el-form-item>
                <el-form-item label="名称">
                  <span>{{scope.row.name}}</span>
                </el-form-item>
                <el-form-item label="创建时间">
                  <span>{{scope.row.createdTime}}</span>
                </el-form-item>
                <el-form-item label="修改时间">
                  <span>{{scope.row.modifiedTime}}</span>
                </el-form-item>
                <el-form-item label="描述">
                  <span>{{scope.row.description}}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
        </template>

        <el-table-column
          prop="roleName"
          label="角色名">
        </el-table-column>
        <el-table-column
          prop="name"
          label="名称">
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
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button :size="size" type="text" icon="el-icon-edit" @click="handleEdit(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </vea-table-simple>
    </el-card>

    <el-dialog
      title="编辑权限"
      :close-on-click-modal="false"
      :visible.sync="visible">
      <div>
        <el-form label-width="80px" label-position="left">
          <el-form-item label="菜单">
            <el-tree
              ref="menuTree"
              node-key="id"
              :props="props"
              :data="menuTree"
              show-checkbox>
            </el-tree>
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer">
        <el-button :size="size" @click="handleDialogCancel">取消</el-button>
        <el-button :size="size" type="primary" @click="handleDialogSubmit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import FormQuery from './form-query/index'
  import VeaTableSimple from '@/components/VeaTableSimple'
  import {queryRoles, getMenuTree, updateRolePermission, deleteRoles} from '@/api/roleManager'
  import ElCollapseTransition from "element-ui/src/transitions/collapse-transition";
  export default {
    name: "RoleManager",
    components: {ElCollapseTransition, FormQuery, VeaTableSimple},
    data() {
      return {
        size: 'small',
        data: {},
        pager: {
          currentPage: 1,
          pageSize: 10
        },
        role: {},
        visible: false,
        props: {
          label: 'menuName',
          children: 'children'
        },
        menuTree: {},
        checkedRole: undefined
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
        const param = {...this.role, ...this.pager};
        /*window.setTimeout(() => {
          queryRoles(param).then(res => {
            if (showLoading) {
              loading.close();
            }
            this.data = res.data;
          }).catch(err => {
            if (showLoading) {
              loading.close();
            }
          });
        }, 3000);*/
        queryRoles(param).then(res => {
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
      handleQuery(role) {
        this.role = role;
        this.pager.currentPage = 1;
        this.search(true);
      },
      handleAdd() {
        this.$router.push({name: 'CreateRole'});
      },
      handleModify(role) {
        const selectRole = {...role};
        selectRole.menus = selectRole.menus.map(menu => menu.id);
        this.$router.push({name: 'EditRole', query: {role: selectRole}});
      },
      handleDelete(roles) {
        this.$confirm('此操作将永久删除角色, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const loading = this.$loading({
            target: this.$el,
            background: 'rgba(0,0,0,0)',
          });
          deleteRoles(roles).then(res => {
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
      handleEdit(role) {
        const checkedRole = {...role};
        checkedRole.menus = checkedRole.menus.map(menu => menu.id);

        this.checkedRole = checkedRole;
        this.visible = true;
        this.$nextTick(() => {
          this.$refs.menuTree.setCheckedKeys(checkedRole.menus);
        });
      },
      handleDialogSubmit() {
        this.checkedRole.menus = this.$refs.menuTree.getCheckedKeys(true);
        const params = {...this.checkedRole};
        const loading = this.$loading({
          target: this.$el,
          background: 'rgba(0,0,0,0)'
        });
        this.visible = false;
        updateRolePermission(params).then(res => {
          loading.close();
          this.$notify({
            type: 'success',
            title: '成功',
            message: '更新成功'
          });
          this.search(true);
        }).catch(err => {
          loading.close();
        });
      },
      handleDialogCancel() {
        this.visible = false;
      }
    },
    mounted() {
      this.search();
      getMenuTree().then(res => {
        this.menuTree = res.data;
      });
    }
  }
</script>

<style lang="scss" scoped>
  .role-manager-container {
    min-height: calc(100vh - 84px);
    background-color: rgb(240, 242, 245);
    padding: 20px;

    .role-manager-form {
      margin-bottom: 20px;
      padding-top: 20px;
    }

    .table-expand {
      font-size: 0;

      /deep/ .el-form-item__label {
        /*width: 90px;*/
        color: #99a9bf;
      }
    }

    .table-expand .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
      width: 50%;
    }
  }
</style>
