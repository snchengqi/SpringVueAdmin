<template>
  <vea-table-simple
    :toolBar="toolBar"
    :viewAble="viewAble"
    :addAble="addAble"
    :modifyAble="modifyAble"
    :deleteAble="deleteAble"
    :type="type"
    :size="size"
    :total-name="totalName"
    :rows-name="rowsName"
    :pageAble="pageAble"
    :pageSizes="pageSizes"
    :pageChange="pageChange"
    :current-page="currentPage"
    :data="data"
    :height="height"
    :max-height="maxHeight"
    :stripe="stripe"
    :border="border"
    :fit="fit"
    :row-key="rowKey"
    :show-header="showHeader"
    :highlight-current-row="highlightCurrentRow"
    :empty-text="emptyText"
    :fixed="fixed"
    :show-message="showMessage"
    @view="view"
    @add="add"
    @delete="deleteRows"
    @modify="modify"
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
    @expand-change="expandChange">
    <el-table-column
      v-for="column in columns"
      :label="column.label"
      :prop="column.prop"
      :width="column.width"
      :min-width="column.minWidth"
      :fixed="column.fixed"
      :sortable="column.sortable"
      :sort-method="column.sortMethod"
      :sort-by="column.sortBy"
      :sort-orders="column.sortOrders"
      :resizable="column.resizable"
      :formatter="column.formatter"
      :show-overflow-tooltip="column.showOverflowTooltip"
      :align="column.align"
      :header-align="column.headerAlign"
      :class-name="column.className"
      :label-class-name="column.labelClassName"
      :render-header="column.renderHeader">
    </el-table-column>
  </vea-table-simple>
</template>

<script>
  import VeaTableSimple from '@/components/VeaTableSimple'
  export default {
    name: 'VeaTable',
    components: {VeaTableSimple},
    props: {
      columns: {
        type: [Array],
        required: true
      },
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
      },
      rowsName: {
        type: [String]
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
        type: [Boolean]
      },
      showHeader: {
        type: [Boolean]
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
    methods: {
      handleSelectionChange(selection) {
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
      view(row) {
        this.$emit('view', row);
      },
      add() {
        this.$emit('add');
      },
      modify(row) {
        this.$emit('modify', row);
      },
      deleteRows(rows) {
        this.$emit('delete', rows);
      }
    }
  }
</script>

<style scoped>

</style>
