<template>
  <el-form :model="user" :inline="true" :size="size">
    <el-form-item label="账号">
      <el-input :clearable="clearAble" v-model="user.userName" placeholder="请填入账号"></el-input>
    </el-form-item>
    <el-form-item label="姓名">
      <el-input :clearable="clearAble" v-model="user.name" placeholder="请填入姓名"></el-input>
    </el-form-item>
    <el-form-item label="性别">
      <el-select :clearable="clearAble" v-model="user.sex" placeholder="请选择">
        <el-option label="男" :value="0"></el-option>
        <el-option label="女" :value="1"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="状态">
      <el-select :clearable="clearAble" v-model="user.isLock" placeholder="请选择">
        <el-option label="未锁定" :value="0"></el-option>
        <el-option label="已锁定" :value="1"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="query">查询</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "form-query",
    props: {
      size: {
        type: [String]
      },
      clearAble: {
        type: [Boolean],
        default: true
      }
    },
    data() {
      return {
        user: {}
      }
    },
    methods: {
      query() {
        if (this.user.sex === '') {
          this.user.sex = undefined;
        }
        if (this.user.isLock === '') {
          this.user.isLock = undefined;
        }
        this.$emit('query', this.user);
      }
    }
  }
</script>

<style scoped>

</style>
