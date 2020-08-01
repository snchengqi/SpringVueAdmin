<template>
  <vea-table-container
    @search="search"
    @pageChange="change"
    @view="view"
    @add="add"
    @delete="deleteRows"
    @modify="modify"
    :toolBar="toolBar"
    :viewAble="viewAble"
    :addAble="addAble"
    :modifyAble="modifyAble"
    :deleteAble="deleteAble"
    :type="type"
    :size="size"
    :pageAble="pageAble"
    :total="total"
    :pageSizes="pageSizes"
    :current-page="currentPage">
    <el-table
      ref="table"
      :size="size"
      :data="filterTableData"
      @selection-change="handleSelectionChange"
      @select="select"
      @select-all="selectAll"
      @cell-mouse-enter="cellMouseEnter"
      @cell-mouse-leave="cellMouseLeave"
      @cell-click="cellClick"
      @cell-dblclick="cellDblclick"
      @row-click="rowClick"
      @row-contextmenu="rowContextmenu"
      @row-dblclick="rowDblclick"
      @header-click="headerClick"
      @header-contextmenu="headerContextmenu"
      @sort-change="sortChange"
      @current-change="currentChange"
      @header-dragend="headerDragend"
      @expand-change="expandChange"
      style="width: 100%"
      :height="height"
      :max-height="maxHeight"
      :stripe="stripe"
      :border="border"
      :fit="fit"
      :row-key="rowKey"
      :show-header="showHeader"
      :highlight-current-row="highlightCurrentRow"
      :empty-text="emptyText">
      <slot name="expand"></slot>
      <el-table-column
        v-if="selectAble"
        type="selection"
        :fixed="fixed"
        :reserve-selection="true"
        width="38">
      </el-table-column>
      <slot></slot>
    </el-table>
  </vea-table-container>
</template>

<script>
  import VeaTableContainer from '@/components/VeaTableContainer'
  export default {
    name: "VeaTableSimple",
    components: {VeaTableContainer},
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
      data: {
        type: [Object],
        default: undefined
      },
      totalName: {
        type: [String],
        default: 'total'
      },
      rowsName: {
        type: [String],
        default: 'rows'
      },
      pageAble: {
        type: [Boolean],
        default: true
      },
      pageSizes: {
        type: [Array],
        default: () => [10,20,50,100]
      },
      pageChange: {
        type: [Function],
        default: () => this.originalData
      },
      currentPage: {
        type: [Number]
      },
      /*table样式*/
      height: {
        type: [Number, String],
        default: undefined
      },
      maxHeight: {
        type: [Number, String],
        default: undefined
      },
      stripe: {
        type: [Boolean]
      },
      border: {
        type: [Boolean]
      },
      fit: {
        type: [Boolean],
        default: true
      },
      showHeader: {
        type: [Boolean],
        default: true
      },
      highlightCurrentRow: {
        type: [Boolean]
      },
      emptyText: {
        type: [String]
      },
      fixed: {
        type: [Boolean, String]
      },
      rowKey: {
        type: [String, Function]
      },
      //message相关
      showMessage: {
        type: [Boolean],
        default: false
      }
    },
    data() {
      return {
        filterTableData: [],
        originalData: {},
        selection: [],
        searchInstance: undefined
      }
    },
    computed: {
      selectAble() {
        return this.toolBar && (this.viewAble || this.addAble || this.modifyAble || this.deleteAble);
      },
      total() {
        return this.originalData[this.totalName];
      },
      rows() {
        return this.originalData[this.rowsName];
      }
    },
    watch: {
      originalData() {
        this.$refs.table.clearSelection();
        this.filterTableData = this.originalData[this.rowsName];
        if (this.searchInstance) {
          this.searchInstance.clearKey();
        }
      },
      data() {
        this.originalData = {...this.data}
      }
    },
    methods: {
      search(key, instance) {
        this.searchInstance = instance;
        if (key === '') {
          this.filterTableData = this.rows;
        }
        this.filterTableData = this.rows.filter((row) => {
          for (let p in row) {
            if (String(row[p]).toLowerCase().includes(key.toLowerCase())) {
              return true;
            }
          }
          return false;
        });
      },
      change({currentPage, pageSize}) {
        /*this.$refs.table.clearSelection();*/
        this.originalData = this.pageChange({currentPage, pageSize});
      },
      handleSelectionChange(selection) {
        this.selection = selection;
        this.$emit('selection-change', selection);
      },
      select(selection, row) {
        this.$emit('select', selection, row);
      },
      selectAll(selection) {
        this.$emit('select-all', selection);
      },
      cellMouseEnter(row, column, cell, event) {
        this.$emit('cell-mouse-enter', row, column, cell, event);
      },
      cellMouseLeave(row, column, cell, event) {
        this.$emit('cell-mouse-leave', row, column, cell, event);
      },
      cellClick(row, column, cell, event) {
        this.$emit('cell-click', row, column, cell, event);
      },
      cellDblclick(row, column, cell, event) {
        this.$emit('cell-dblclick', row, column, cell, event);
      },
      rowClick(row, column, event) {
        this.$emit('row-click', row, column, event);
      },
      rowContextmenu(row, column, event) {
        this.$emit('row-contextmenu', row, column, event);
      },
      rowDblclick(row, column, event) {
        this.$emit('row-dblclick', row, column, event);
      },
      headerClick(column, event) {
        this.$emit('header-click', column, event);
      },
      headerContextmenu(column, event) {
        this.$emit('header-contextmenu', column, event);
      },
      sortChange({ column, prop, order }) {
        this.$emit('sort-change', { column, prop, order });
      },
      currentChange(currentRow, oldCurrentRow) {
        this.$emit('current-change', currentRow, oldCurrentRow);
      },
      headerDragend(newWidth, oldWidth, column, event) {
        this.$emit('header-dragend', newWidth, oldWidth, column, event);
      },
      expandChange(row, expandedRows) {
        this.$emit('expand-change', row, expandedRows);
      },
      view() {
        if (!this.selection || this.selection.length === 0) {
          if (this.showMessage) {
            this.$message({
              type:'warning',
              message: '请先选中数据'
            });
          }
          return false;
        }
        if (this.selection.length > 1) {
          if (this.showMessage) {
            this.$message({
              type:'warning',
              message: '请选中单行数据'
            });
          }
          return false;
        }
        this.$emit('view', this.selection[0]);
      },
      add() {
        this.$emit('add');
      },
      modify() {
        if (!this.selection || this.selection.length === 0) {
          if (this.showMessage) {
            this.$message({
              type:'warning',
              message: '请先选中数据'
            });
          }
          return false;
        }
        if (this.selection.length > 1) {
          if (this.showMessage) {
            this.$message({
              type:'warning',
              message: '请选中单行数据'
            });
          }
          return false;
        }
        this.$emit('modify', this.selection[0]);
      },
      deleteRows() {
        if (!this.selection || this.selection.length === 0) {
          if (this.showMessage) {
            this.$message({
              type:'warning',
              message: '请先选中数据'
            });
          }
          return false;
        }
        this.$emit('delete', this.selection);
      }
    },
    mounted() {
      this.originalData = {...this.data};
      this.filterTableData = this.rows;
    }
  }
</script>

<style scoped>

</style>
