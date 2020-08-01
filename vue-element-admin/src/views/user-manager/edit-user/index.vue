<template>
  <form-user
    ref="userForm"
    v-model="user"
    type="edit"
    :hide-required-asterisk="true">
    <el-form-item slot="control">
      <el-button type="primary" @click="handleSubmit">提交</el-button>
    </el-form-item>
  </form-user>
</template>

<script>
  import FormUser from '../form-user/index'
  import {editUser} from '@/api/userManager'
  export default {
    name: "EditUser",
    components: {FormUser},
    data() {
      return {
        user: {}
      }
    },
    methods: {
      handleSubmit() {
        const form = this.$refs.userForm.$refs.form;
        form.validate(valid => {
          if (!valid) {
            return false;
          }
          let route = this.$route;
          const loading = this.$loading({
            target: this.$el,
            // spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0)'
          });
          editUser(this.user).then(res => {
            loading.close();
            this.$notify({
              type: 'success',
              title: '成功',
              message: '修改成功'
            });
            this.$store.dispatch('delView', route).then(() => {
              this.$router.replace({name: 'UserManager'});
            });
          }).catch(err => {
            loading.close();
          });
        });
      }
    },
    mounted() {
      const user = this.$route.query.user;
      const roles = [];
      user.roles.forEach(role => roles.push(role.id));
      user.roles = roles;
      user.menus = undefined;
      this.user = user;
    }
  }
</script>

<style scoped>

</style>
