<template>
  <form-api ref="apiForm" v-model="api" :hide-required-asterisk="true">
    <el-form-item slot="control">
      <el-button type="primary" @click="handleSubmit">提交</el-button>
    </el-form-item>
  </form-api>
</template>

<script>
  import FormApi from '../form-api/index'
  import {editBackendApi} from '@/api/backendApiManager'
  export default {
    name: "EditApi",
    components: {FormApi},
    data() {
      return {
        api: {}
      }
    },
    methods: {
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
          editBackendApi(this.api).then(res => {
            loading.close();
            this.$notify({
              type: 'success',
              title: '成功',
              message: '修改成功'
            });
            this.$store.dispatch('delView', route).then(() => {
              this.$router.replace({name: 'BackendApiManager'});
            });
          }).catch(err => {
            loading.close();
          });
        });
      }
    },
    created() {
      this.api = this.$route.query.api;
    }
  }
</script>

<style scoped>

</style>
