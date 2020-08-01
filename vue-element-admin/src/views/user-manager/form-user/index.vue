<template>
  <el-row class="user-form-container">
    <el-col :xs="24" :sm="20" :md="20">
      <el-form ref="form" class="user-form" :hide-required-asterisk="hideRequiredAsterisk" label-position="top" :model="user" :rules="rules">
        <el-form-item label="账号" prop="userName">
          <el-input v-model="user.userName" :disabled="type === 'edit'" placeholder="请填入用户账号"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="user.name" placeholder="请填入用户姓名"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <el-select style="width: 100%" multiple v-model="user.roles" placeholder="请选择角色">
            <el-option v-for="role in roles" :label="role.name" :value="role.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="user.sex">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="状态" prop="isLock">
          <el-radio-group v-model="user.isLock">
            <el-radio :label="0">未锁定</el-radio>
            <el-radio :label="1">已锁定</el-radio>
          </el-radio-group>
        </el-form-item>
        <slot name="control"></slot>
      </el-form>
    </el-col>
    <el-col :xs="0" :sm="4" :md="4"></el-col>
  </el-row>
</template>

<script>
  import {queryAllRoles} from '@/api/userManager'
  export default {
    name: "FormUser",
    props: {
      value: {
        type: [Object],
        required: true
      },
      hideRequiredAsterisk: {
        type: [Boolean]
      },
      type: {
        type: [String],
        default: 'create'
      }
    },
    data() {
      return {
        user: this.value,
        roles: [],
        rules: {
          userName: [
            {required: true, message: '请输入用户账号', trigger: 'blur'},
            {min: 5, max: 18, message: '长度在5到18个字符', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请输入用户姓名', trigger: 'blur'},
            {max: 6, message: '必须小于6个字符', trigger: 'blur'}
          ],
          sex: [
            {required: true, message: '请选择性别', trigger: 'change'}
          ],
          isLock: [
            {required: true, message: '请选择状态', trigger: 'change'}
          ]
        }
      }
    },
    watch: {
      value(val) {
        this.user = val;
      },
      user(val) {
        this.$emit('input', val);
      }
    },
    created() {
      queryAllRoles().then(res => {
        this.roles = res.data;
      }).catch(err => {
        this.$notify({
          type: 'error',
          title: '错误',
          message: err
        });
      });
    }
  }
</script>

<style lang="scss" scoped>
  .user-form-container {
    padding: 20px;

    .user-form {
      margin: 18px;
    }
  }
</style>
