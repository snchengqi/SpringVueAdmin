<template>
  <form-user
    class="user-container"
    ref="userForm"
    v-model="user"
    type="create"
    :hide-required-asterisk="false">
    <el-form-item slot="control">
      <el-button type="primary" @click="handleSubmit">提交</el-button>
      <el-button @click="handleReset">重置</el-button>
    </el-form-item>
  </form-user>
</template>

<script>
  import FormUser from '../form-user/index'
  import {addUser} from '@/api/userManager'
  export default {
    name: "CreateUser",
    components: {FormUser},
    data() {
      return {
        user: {
          userName: '',
          name: '',
          roles: [],
          sex: undefined,
          isLock: undefined
        },
      }
    },
    methods: {
      handleReset() {
        this.user = {
          roles: []
        };
        this.$refs.userForm.$refs.form.resetFields();
      },
      handleSubmit() {
        const form = this.$refs.userForm.$refs.form;
        form.validate(valid => {
          if (!valid) {
            return false;
          }
          let route = this.$route;
          let loading = this.$loading({
            target: this.$el,
            // spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0)'
          });
          addUser(this.user).then(res => {
            loading.close();
            this.$notify({
              type: 'success',
              title: '成功',
              message: '新增成功'
            });
            this.$store.dispatch('delView', route).then(() => {
              this.$router.replace({name: 'UserManager'});
            });
          }).catch(err => {
            loading.close();
          });
        });
      }
    }
  }
</script>

<style lang="scss" scoped>
  .user-container {
    min-height: calc(100vh - 84px);
  }
</style>
