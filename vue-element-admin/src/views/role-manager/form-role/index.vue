<template>
  <div class="role-form-container">
    <el-row>
      <el-col :xs="24" :sm="20" :md="20">
        <el-form ref="form" label-position="top" class="role-form" :model="role" :rules="rules" :hide-required-asterisk="hideRequiredAsterisk">
          <el-form-item label="角色名" prop="roleName">
            <el-input v-model="role.roleName" placeholder="请输入角色名"></el-input>
          </el-form-item>
          <el-form-item label="名称" prop="name">
            <el-input v-model="role.name" placeholder="请输入名称"></el-input>
          </el-form-item>
          <el-form-item label="描述">
            <el-input v-model="role.description" :rows="6" type="textarea" placeholder="描述..."></el-input>
          </el-form-item>
          <slot name="control"></slot>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "FormRole",
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
        role: this.value,
        rules: {
          roleName: [
            {required: true, message: '请填入角色名', trigger: 'blur'},
            {max: 18, message: '长度不能超过18个字符', trigger: 'blur'}
          ],
          name: [
            {required: true, message: '请填入名称', trigger: 'blur'},
            {min: 2, max: 10, message: '长度在2到10个字符', trigger: 'blur'}
          ]
        }
      }
    },
    watch: {
      value(val) {
        this.role = val;
      },
      role(val) {
        this.$emit('input', val);
      }
    }
  }
</script>

<style lang="scss" scoped>
  .role-form-container {
    padding: 20px;

    .role-form {
      margin: 18px;
    }
  }
</style>
