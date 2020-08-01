<template>
  <el-form :model="api" :inline="true" :size="size">
    <el-form-item label="名称">
      <el-input v-model="api.name" :clearable="clearAble" placeholder="请输入名称"></el-input>
    </el-form-item>
    <el-form-item label="路径">
      <el-input v-model="api.path" :clearable="clearAble" placeholder="请输入路径"></el-input>
    </el-form-item>
    <el-form-item label="方法">
      <el-select v-model="api.method" :clearable="clearAble">
        <el-option v-for="item in methods" :label="item.name" :value="item.value"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="query">查询</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "FormQuery",
    props: {
      size: {
        type: [String]
      },
      clearAble: {
        type: [Boolean]
      }
    },
    data() {
      return {
        methods: [
          {name: 'GET', value: 1},
          {name: 'POST', value: 2},
          {name: 'PUT', value: 3},
          {name: 'DELETE', value: 4}
        ],
        api: {}
      }
    },
    methods: {
      query() {
        this.$emit('query', this.api);
      }
    }
  }
</script>

<style lang="scss" scoped>
  /deep/ .el-input__inner {
    padding-right: 30px;
  }
</style>
