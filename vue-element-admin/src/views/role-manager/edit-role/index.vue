<template>
  <form-role ref="roleForm" v-model="role" :hide-required-asterisk="true">
    <el-form-item slot="control">
      <el-button type="primary" @click="handleSubmit">提交</el-button>
    </el-form-item>
  </form-role>
</template>

<script>
  import FormRole from '../form-role/index'
  import {editRole} from '@/api/roleManager'
  export default {
    name: "EditRole",
    components: {FormRole},
    data() {
      return {
        role: {}
      }
    },
    methods: {
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
          editRole(this.role).then(res => {
            loading.close();
            this.$notify({
              type: 'success',
              title: '成功',
              message: '修改成功',
            });
            this.$store.dispatch('delView', route).then(() => {
              this.$router.replace({name: 'RoleManager'});
            });
          }).catch(err => {
            loading.close();
          });
        });
      }
    },
    mounted() {
      this.role = this.$route.query.role;
    }
  }
</script>

<style scoped>

</style>
