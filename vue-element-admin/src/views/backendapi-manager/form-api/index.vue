<template>
  <el-row class="form-api-contain">
    <el-col :xs="24" :sm="20">
      <el-form
        ref="form"
        :model="api"
        :rules="rules"
        :hide-required-asterisk="hideRequiredAsterisk"
        class="api-form"
        label-position="top">
        <el-form-item label="名称" prop="name">
          <el-input v-model="api.name" placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item label="路径" prop="path">
          <el-input v-model="api.path" placeholder="请输入路径"></el-input>
        </el-form-item>
        <el-form-item label="方法" prop="method">
          <el-select v-model="api.method" style="width: 100%">
            <el-option v-for="item in methods" :label="item.name" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <slot name="control"></slot>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: "FormApi",
    props: {
      value: {
        type: [Object],
        required: true
      },
      hideRequiredAsterisk: {
        type: [Boolean]
      }
    },
    data() {
      return {
        api: this.value,
        methods: [
          {name: 'GET', value: 1},
          {name: 'POST', value: 2},
          {name: 'PUT', value: 3},
          {name: 'DELETE', value: 4}
        ],
        rules: {
          name: [
            {required: true, message: '请输入名称', trigger: 'blur'}
          ],
          path: [
            {required: true, pattern: /^(\/[a-zA-Z0-9\-_%]+)+$/, message: '请输入正确路径', trigger: ['change','blur']}
          ],
          method: [
            {required: true, message: '请选择方法', trigger: 'change'}
          ]
        }
      }
    },
    watch: {
      value(val) {
        this.api = val;
      },
      api(val) {
        this.$emit('input', val);
      }
    }
  }
</script>

<style lang="scss" scoped>
  .form-api-contain {
    padding: 20px;

    .api-form {
      margin: 18px;
    }
  }
</style>
