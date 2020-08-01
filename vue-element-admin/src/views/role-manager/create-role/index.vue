<template>
  <form-role ref="roleForm" v-model="role">
    <el-form-item slot="control">
      <el-button type="primary" @click="handleSubmit">提交</el-button>
      <el-button @click="handleReset">重置</el-button>
    </el-form-item>
  </form-role>
</template>

<script>
  import FormRole from '../form-role/index'
  import {addRole} from '@/api/roleManager'
  export default {
    name: "CreateRole",
    components: {FormRole},
    data() {
      return {
        role: {}
      }
    },
    methods: {
      handleReset() {
        this.$refs.roleForm.$refs.form.resetFields();
      },
      handleSubmit() {
        const form = this.$refs.roleForm.$refs.form;
        form.validate(valid => {
          if (!valid) {
            return false;
          }
          const route = this.$route;
          const loading = this.$loading({
            target: this.$el,
            background: 'rgba(0,0,0,0)'
          });
          addRole(this.role).then(res => {
            loading.close();
            this.$notify({
              type: 'success',
              title: '成功',
              message: '新增成功'
            });
            this.$store.dispatch('delView', route).then(() => {
              this.$router.replace({name: 'RoleManager'});
            });
          }).catch(err => {
            loading.close();
          });
        });
      }
    }
  }
</script>

<style scoped>

</style>
