<template>
    <div class="vea-table-container">
      <el-row v-if="toolBar" class="vea-table-header">
        <el-button-group class="vea-toolbar-left">
          <el-button v-if="addAble" @click.stop="addClick" :type="type" :size="size" icon="el-icon-plus"></el-button>
          <el-button v-if="viewAble" @click.stop="viewClick" :type="type" :size="size" icon="el-icon-view"></el-button>
          <el-button v-if="modifyAble" @click.stop="modifyClick" :type="type" :size="size" icon="el-icon-edit-outline"></el-button>
          <el-button v-if="deleteAble" @click.stop="deleteClick" :type="type" :size="size" icon="el-icon-delete"></el-button>
        </el-button-group>
        <div class="vea-toolbar-right">
          <vea-input-search :size="size" @search="search"></vea-input-search>
        </div>
      </el-row>
      <el-row class="vea-table-content">
        <slot>
          <p class="vea-table-content-p">无数据!</p>
        </slot>
      </el-row>
      <el-row v-if="pageAble" class="vea-table-footer">
        <div class="vea-table-page">
          <el-pagination
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            :page-sizes="pageSizes"
            :page-size="pageSize"
            :current-page="currentPage"
            :pager-count="5"
            :background="pagerBackground"
            :small="pagerSize"
            @current-change="currentChange"
            @size-change="sizeChange"
            ></el-pagination>
        </div>
      </el-row>
    </div>
</template>

<script>
  import VeaInputSearch from '@/components/VeaInputSearch'
  export default {
    name: "VeaTableContainer",
    components: {VeaInputSearch},
    props: {
      toolBar: {
        type: [Boolean],
        default: true
      },
      viewAble: {
        type: [Boolean],
        default: true
      },
      addAble: {
        type: [Boolean],
        default: true
      },
      modifyAble: {
        type: [Boolean],
        default: true
      },
      deleteAble: {
        type: [Boolean],
        default: true
      },
      size: {
        type: [String],
        default: undefined
      },
      type: {
        type: [String],
        default: ''
      },
      pageAble: {
        type: [Boolean],
        default: true
      },
      total: {
        type: [Number],
        default: 0
      },
      pageSizes: {
        type: [Array],
        default: () => [10,20,50,100]
      },
      currentPage: {
        type: [Number],
      }
    },
    data() {
      return {
        currentPageInner: 0,
        pageSize: 10
      }
    },
    computed: {
      pagerSize() {
        return this.size === 'small' || this.size === 'mini'
      },
      pagerBackground() {
        return this.type !== '';
      }
    },
    methods: {
      viewClick() {
        this.$emit('view');
      },
      addClick() {
        this.$emit('add');
      },
      modifyClick() {
        this.$emit('modify');
      },
      deleteClick() {
        this.$emit('delete');
      },
      search(key, instance) {
        this.$emit('search', key, instance);
      },
      currentChange(currentPage) {
        this.currentPageInner = currentPage;
        this.$emit('pageChange', {currentPage, 'pageSize': this.pageSize});
      },
      sizeChange(pageSize) {
        this.pageSize = pageSize;
        if (this.total/pageSize + 1 < this.currentPageInner) {
          return;
        }
        this.$emit('pageChange', {'currentPage': this.currentPageInner, pageSize});
      }
    },
    mounted() {
      this.pageSize = this.pageSizes[0];
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .vea-table-container {
    margin: 0;
    padding: 0;
    width: 100%;
    .vea-table-header {
      padding: 3px;
      border-bottom: #f0f2f5 1px solid;

      .vea-toolbar-left {
        margin-left: 10px;
      }
      .vea-toolbar-right {
        float: right;
        height: 100%;
        margin-right: 10px;
      }
    }
    .vea-table-content {
      /*min-height: 118px;*/
      padding: 0 12px;
      border-bottom: #f0f2f5 1px solid;

      .vea-table-content-p {
        /*margin: 50px;*/
        text-align: center;
        color: #C0C4CC;
      }
    }
    .vea-table-footer {
      padding: 3px;
      overflow-x: auto;
      /*border-bottom: #f0f2f5 1px solid;*/

      .vea-table-page {
        text-align: center;
      }
    }
  }
</style>
