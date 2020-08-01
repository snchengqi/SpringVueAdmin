<template>
  <div class="backend-api-manager-container">
    <el-card shadow="hover" class="backend-api-manager-form">
      <form-query :size="size" :clear-able="clearAble" @query="handleQuery"></form-query>
    </el-card>
    <el-card shadow="hover">
      <vea-table-simple
        :size="size"
        :data="data"
        total-name="totalElements"
        rows-name="content"
        :current-pager="pager.currentPage"
        :page-change="pageChange"
        :view-able="false"
        row-key="id"
        @add="handleAdd"
        @modify="handleEdit"
        @delete="handleDelete">
        <el-table-column
          label="名称"
          prop="name">
        </el-table-column>
        <el-table-column
          label="路径"
          prop="path">
        </el-table-column>
        <el-table-column
          label="方法">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.method === 1">GET</el-tag>
            <el-tag v-else-if="scope.row.method === 2" type="success">POST</el-tag>
            <el-tag v-else-if="scope.row.method === 3" type="warning">PUT</el-tag>
            <el-tag v-else-if="scope.row.method === 4" type="danger">DELETE</el-tag>
            <el-tag v-else type="info">未知</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="创建人"
          prop="createdBy">
        </el-table-column>
        <el-table-column
          label="修改人"
          prop="modifiedBy">
        </el-table-column>
        <el-table-column
          label="创建时间"
          prop="createdTime"
          :show-overflow-tooltip="true"
          min-width="100"
          sortable>
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span>{{scope.row.createdTime}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="修改时间"
          prop="modifiedTime"
          :show-overflow-tooltip="true"
          min-width="100"
          sortable>
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span>{{scope.row.modifiedTime}}</span>
          </template>
        </el-table-column>
      </vea-table-simple>
    </el-card>
  </div>
</template>

<script>
  import {queryBackendApis, deleteBackendApis} from '@/api/backendApiManager'
  import FormQuery from './form-query/index'
  import VeaTableSimple  from '@/components/VeaTableSimple'
  export default {
    name: "BackendApiManager",
    components: {FormQuery, VeaTableSimple},
    data() {
      return {
        size: 'small',
        clearAble: true,
        data: {},
        api: {},
        pager: {
          currentPage: 1,
          pageSize: 10
        }
      }
    },
    methods: {
      rowKey(row) {
        return row.id + new Date();
      },
      search(showLoading) {
        const params = {...this.api, ...this.pager};
        let loading;
        if (showLoading) {
          loading = this.$loading({
            target: this.$el,
            background: 'rgba(0,0,0,0)'
          });
        }
        queryBackendApis(params).then(res => {
          if (showLoading) {
            loading.close();
          }
          this.data = res.data;
        }).catch(err => {
          if (showLoading) {
            loading.close();
          }
        });
      },
      pageChange({currentPage, pageSize}) {
        this.pager = {currentPage, pageSize};
        this.search(true);
        return this.data;
      },
      handleQuery(api) {
        if (api.method === '') {
          api.method = undefined;
        }
        this.api = api;
        this.pager.currentPage = 1;
        this.search(true);
      },
      handleAdd() {
        this.$router.push({name: 'CreateApi'});
      },
      handleEdit(api) {
        this.$router.push({name: 'EditApi', query: {api}});
      },
      handleDelete(apis) {
        this.$confirm('此操作将永久删除接口, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const loading = this.$loading({
            target: this.$el,
            // spinner: 'el-icon-loading',
            background: 'rgba(0,0,0,0)',
          });
          debugger;
          deleteBackendApis(apis).then(res => {
            loading.close();
            this.$notify({
              type: 'success',
              title: '成功',
              message: '删除成功'
            });
            this.search(true);
          }).catch(err => {
            loading.close()
          });
        });
      }
    },
    mounted() {
      this.search();
    }
  }
</script>

<style lang="scss" scoped>
  .backend-api-manager-container {
    min-height: calc(100vh - 84px);
    background-color: rgb(240, 242, 245);
    padding: 20px;

    .backend-api-manager-form {
      padding-top: 20px;
      margin-bottom: 20px;
    }
  }
</style>
