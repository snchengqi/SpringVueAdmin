<template>
  <el-form :model="role" :inline="true" :size="size">
    <el-form-item label="角色">
      <el-input v-model="role.roleName" clearable placeholder="请输入角色名"></el-input>
    </el-form-item>
    <el-form-item label="名称">
      <el-input v-model="role.name" clearable placeholder="请输入名称"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click.stop="query">查询</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "FormQuery",
    props: {
      size: {
        type: [String]
      }
    },
    data() {
      return {
        role: {}
      }
    },
    methods: {
      query() {
        this.$emit('query', this.role);
      }
    }
  }
</script>

<style scoped>

</style>
