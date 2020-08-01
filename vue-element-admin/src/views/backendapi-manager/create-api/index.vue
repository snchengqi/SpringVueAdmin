<template>
  <form-api ref="apiForm" v-model="api">
    <el-form-item slot="control">
      <el-button type="primary" @click="handleSubmit">提交</el-button>
      <el-button @click="handleReset">重置</el-button>
    </el-form-item>
  </form-api>
</template>

<script>
  import FormApi from '../form-api/index'
  import {addBackendApi} from '@/api/backendApiManager'
  export default {
    name: "CreateApi",
    components: {FormApi},
    data() {
      return {
        api: {}
      }
    },
    methods: {
      handleReset() {
        this.$refs.apiForm.$refs.form.resetFields();
      },
      handleSubmit() {
        const form = this.$refs.apiForm.$refs.form;
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
          addBackendApi(this.api).then(res => {
            loading.close();
            this.$notify({
              type: 'success',
              title: '成功',
              message: '新增成功'
            });
            this.$store.dispatch('delView', route).then(() => {
              this.$router.replace({name: 'BackendApiManager'});
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
