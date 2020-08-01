<template>
  <div class="menu-manager-container">
    <el-row class="menu-manager-content" :gutter="10">
      <el-col :xs="24" :sm="8" :md="6">
        <el-card class="content-height" shadow="hover">
          <div slot="header">
            <span style="font-weight: bold">菜单设置</span>
          </div>
          <el-scrollbar style="height: 93%; width: 100%">
            <el-tree
              :props="props"
              :data="menuTree"
              :highlight-current="true"
              :expand-on-click-node="false"
              @node-click="nodeClick">
              <span class="tree-font" slot-scope="{node, data}">
                <svg-icon :icon-class="data.level === 1? node.expanded?'directory-open':'directory':data.level === 2?'file':''"></svg-icon>
                <span>{{node.label}}</span>
              </span>
            </el-tree>
          </el-scrollbar>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="16" :md="18">
        <el-card class="content-right" shadow="hover">
          <div slot="header">
            <el-button :disabled="disabled.add" @click="handleAdd" :type="type" :size="size" icon="el-icon-plus">添加</el-button>
            <el-button :disabled="disabled.edit" @click="handleEdit" :type="type" :size="size" icon="el-icon-edit">修改</el-button>
            <el-button :disabled="disabled.delete" @click="handleDelete" :type="type" :size="size" icon="el-icon-delete">删除</el-button>
            <el-button :disabled="disabled.save" @click="handleSave" :type="type" :size="size" icon="el-icon-tickets">保存</el-button>
            <el-button :disabled="disabled.back" @click="handleBack" :type="type" :size="size" icon="el-icon-close">返回</el-button>
          </div>

          <el-row>
            <el-col :xs="24" :sm="20" :md="18">
              <el-form ref="form" :model="formData" :rules="rules" :disabled="disabled.form" :size="size" :hide-required-asterisk="true" label-position="left" label-width="120px">
                <el-form-item label="菜单类型">
                  <el-select v-model="formData.level" disabled style="width: 100%">
                    <el-option label="一级菜单" :value="1"></el-option>
                    <el-option label="二级菜单" :value="2"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="组件名称" prop="name">
                  <el-input v-model="formData.name" placeholder="请输入组件名称"></el-input>
                </el-form-item>
                <el-form-item label="菜单名称" prop="menuName">
                  <el-input v-model="formData.menuName" placeholder="请输入菜单名称"></el-input>
                </el-form-item>
                <el-form-item label="菜单图标">
                  <el-input v-model="formData.icon" placeholder="请输入菜单图标"></el-input>
                </el-form-item>
                <el-form-item label="菜单顺序" prop="orderNo">
                  <el-input-number v-model="formData.orderNo" :min="0" controls-position="right" style="width: 100%"></el-input-number>
                </el-form-item>
                <el-form-item v-if="formType === 2" label="菜单路径" prop="path">
                  <el-input v-model="formData.path" placeholder="请输入菜单路径"></el-input>
                </el-form-item>
                <el-form-item v-if="formType === 2" label="接口权限">
                  <el-select v-model="formData.backendApis" multiple style="width: 100%">
                    <el-option v-for="backendApi in backendApis" :label="backendApi.name" :value="backendApi.id">
                      <span style="float: left">{{backendApi.name}}</span>
                      <span style="float: right; color: #8492a6; font-size: 13px">{{backendApi.path}}|{{resolveMethod(backendApi.method)}}</span>
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item v-if="formType === 1" label="显示根节点" prop="alwaysShow">
                  <el-radio-group v-model="formData.alwaysShow">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item v-if="formType === 2" label="是否缓存" prop="noCache">
                  <el-radio-group v-model="formData.noCache">
                    <el-radio :label="0">是</el-radio>
                    <el-radio :label="1">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item v-if="formType === 2" label="是否隐藏" prop="hidden">
                  <el-radio-group v-model="formData.hidden">
                    <el-radio :label="1">是</el-radio>
                    <el-radio :label="0">否</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-form>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import {getMenuTree} from '@/api/roleManager'
  import {addMenu, editMenu, deleteMenu, queryAllBackendApi} from '@/api/menuManager'
  export default {
    name: "MenuManager",
    data() {
      return {
        type: 'text',
        size: 'small',
        menuTree: [],
        backendApis: [],
        props: {
          label: 'menuName',
          children: 'children'
        },
        disabled: {
          form: true,
          add: false,
          edit: true,
          delete: true,
          save: true,
          back: true
        },
        selectMenu: undefined,
        formData: {},
        formType: 1,
        control: undefined,

        rules: {
          name: [
            {required: true, message: '请输入组件名称', trigger: 'blur'}
          ],
          menuName: [
            {required: true, message: '请输入菜单名称', trigger: 'blur'}
          ],
          path: [
            {required: true, message: '请输入菜单路径', trigger: 'blur'}
          ],
          orderNo: [
            {type: 'number', required: true, message: '请输入菜单顺序', trigger: 'blur'}
          ],
          alwaysShow: [
            {required: true, message: '请选择是否显示跟节点', trigger: 'blur'}
          ],
          noCache: [
            {required: true, message: '请选择是否缓存', trigger: 'blur'}
          ],
          hidden: [
            {required: true, message: '请选择是否隐藏', trigger: 'blur'}
          ],
        }
      }
    },
    methods: {
      queryMenuTree(showLoading) {
        let loading;
        if (showLoading) {
          loading = this.$loading({
            target: this.el,
            background: 'rgba(0,0,0,0)'
          });
        }
        getMenuTree().then(res => {
          this.menuTree = res.data;
          if (showLoading) {
            loading.close();
          }
        }).catch(err => {
          if (showLoading) {
            loading.close();
          }
        });
      },
      queryAllBackendApi() {
        queryAllBackendApi().then(res => this.backendApis = res.data);
      },
      resolveMethod(code) {
        let message = '未知';
        switch (code) {
          case 1:
            message = "GET";
            break;
          case 2:
            message = "POST";
            break;
          case 3:
            message = "PUT";
            break;
          case 4:
            message = "DELETE";
        }
        return message;
      },
      nodeClick(data, node, vNode) {
        this.$refs.form.clearValidate();

        this.selectMenu = data;
        this.formType = data.level;
        this.formData = {...data};
        this.formData.backendApis = this.formData.backendApis.map(item => item.id);
        this.formData.children = undefined;

        this.disabled.form = true;
        this.disabled.edit = false;
        this.disabled.delete = false;
        this.disabled.save = true;
        this.disabled.back = false;
        this.disabled.add = data.level !== 1;
      },
      handleAdd() {
        this.control = 'add';

        this.disabled.form = false;
        this.disabled.add = true;
        this.disabled.edit = true;
        this.disabled.delete = true;
        this.disabled.save = false;
        this.disabled.back = false;

        if (this.selectMenu && this.selectMenu.level === 1) {
          this.formType = 2;
        } else {
          this.formType = 1;
        }

        this.formData = {
          level: this.formType,
          parentId: this.selectMenu? this.selectMenu.id: undefined,
          orderNo: 0
        };
      },
      handleEdit() {
        this.control = 'edit';

        this.disabled.form = false;
        this.disabled.add = true;
        this.disabled.edit = true;
        this.disabled.delete = false;
        this.disabled.save = false;
        this.disabled.back = false;
      },
      handleDelete() {
        this.$confirm('此操作将永久删除该菜单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const loading = this.$loading({
            target: this.$el,
            background: 'rgba(0,0,0,0)'
          });
          deleteMenu({id: this.selectMenu.id}).then(res => {
            loading.close();
            this.$notify({
              type: 'success',
              title: '成功',
              message: '删除成功'
            });
            this.formType = 1;
            this.selectMenu = undefined;
            this.formData = {};

            this.disabled.form = true;
            this.disabled.add = false;
            this.disabled.edit = true;
            this.disabled.delete = true;
            this.disabled.save = true;
            this.disabled.back = true;

            this.queryMenuTree(true);
          }).catch(err => {
            loading.close();
          });
        });
      },
      handleSave() {
        this.$refs.form.validate(valid => {
          if (!valid) {
            return false;
          }
          const loading = this.$loading({
            target: this.el,
            background: 'rgba(0,0,0,0)'
          });
          if (this.control === 'edit') {
            editMenu(this.formData).then(res => {
              loading.close();
              this.$notify({
                type: 'success',
                title: '成功',
                message: '修改成功'
              });
              this.formType = 1;
              this.control = undefined;
              this.selectMenu = undefined;
              this.formData = {};

              this.disabled.form = true;
              this.disabled.add = false;
              this.disabled.edit = true;
              this.disabled.delete = true;
              this.disabled.save = true;
              this.disabled.back = true;

              this.queryMenuTree(true);
            }).catch(err => {
              loading.close();
            });
          } else if (this.control === 'add') {
            addMenu(this.formData).then(res => {
              loading.close();
              this.$notify({
                type: 'success',
                title: '成功',
                message: '新增成功'
              });
              this.formType = 1;
              this.control = undefined;
              this.selectMenu = undefined;
              this.formData = {};

              this.disabled.form = true;
              this.disabled.add = false;
              this.disabled.edit = true;
              this.disabled.delete = true;
              this.disabled.save = true;
              this.disabled.back = true;

              this.queryMenuTree(true);
            }).catch(err => {
              loading.close();
            });
          } else {
            loading.close();
          }
        });
      },
      handleBack() {
        this.$refs.form.clearValidate();

        this.control = undefined;

        this.selectMenu = undefined;
        this.formType = 1;
        this.formData = {};

        this.disabled.form = true;
        this.disabled.add = false;
        this.disabled.edit = true;
        this.disabled.delete = true;
        this.disabled.save = true;
        this.disabled.back = true;
      }
    },
    mounted() {
      this.queryMenuTree();
      this.queryAllBackendApi();
    }
  }
</script>

<style lang="scss" scoped>
  .menu-manager-container {
    min-height: calc(100vh - 84px);
    background-color: rgb(240, 242, 245);
    padding: 20px;

    .menu-manager-content {
      .content-height {
        height: calc(100vh - 124px);

        /deep/ .el-scrollbar__wrap {
          overflow-x: hidden;
        }

        /deep/ .el-card__header {
          padding: 10px;
        }

        /deep/ .el-card__body {
          height: 100%;
        }
      }

      .content-right {
        min-height: calc(100vh - 124px);

        /deep/ .el-card__header {
          padding: 10px;
        }

        /deep/ .el-card__body {
          height: 100%;

          /deep/ .el-input-number .el-input__inner {
            text-align: left;
          }
        }
      }

      .tree-font {
        font-size: 14px;
      }
    }
  }
</style>
