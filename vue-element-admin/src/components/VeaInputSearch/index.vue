<template>
  <div :class="{show: show && (!size || size === ''), 'show-medium': show && (size === 'medium'),
    'show-small': show && (size === 'small'), 'show-mini': show && (size === 'mini')}" class="vea-input-search-container">
    <i class="el-icon-search vea-input-search-icon" @click.stop="click"></i>
    <el-input ref="searchInput" v-model="queryKey" :size="size" class="vea-input-search-input" placeholder="搜索"></el-input>
  </div>
</template>

<script>
  export default {
    name: "VeaInputSearch",
    props: {
      size: {
        type: [String],
        default: ''
      }
    },
    computed: {
      width() {
        let width = '180px';
        switch (this.size) {
          case 'medium':
            width = '160px';
            break;
          case 'small':
            width = '140px';
            break;
          case 'mini':
            width = '120px';
        }
        return width;
      }
    },
    data () {
      return {
        show: false,
        queryKey: ''
      }
    },
    methods: {
      click() {
        this.show = !this.show;
        if (this.show) {
          this.$refs.searchInput && this.$refs.searchInput.focus();
        }
        /*this.queryKey = '';*/
      },
      close(e) {
        if (this.$refs.searchInput.$el === e.target.parentElement) {
          return;
        }
        this.$refs.searchInput && this.$refs.searchInput.blur();
        this.show = false;
        /*this.queryKey = '';*/
      },
      clearKey() {
        this.queryKey = '';
      }
    },
    watch: {
      show (show) {
        if (show) {
          document.body.addEventListener('click', this.close);
        } else {
          document.body.removeEventListener('click', this.close);
        }
      },
      queryKey() {
        this.$emit('search', this.queryKey, this);
      }
    }
  }
</script>

<style lang="scss" scoped>
  .vea-input-search-container {
    display: inline-block;

    .vea-input-search-icon {
      cursor: pointer;
      font-size: 18px;
      vertical-align: middle;
    }

    .vea-input-search-input {
      width: 0;
      transition: width 0.2s;
      vertical-align: middle;

      /deep/ .el-input__inner {
        border-radius: 0;
        border: 0;
        padding-left: 0;
        padding-right: 0;
        box-shadow: none !important;
        border-bottom: 1px solid #d9d9d9;
        vertical-align: middle;
      }
    }

    &.show {
      .vea-input-search-input {
        width: 180px;
        margin-left: 10px;
      }
    }

    &.show-medium {
      .vea-input-search-input {
        width: 160px;
        margin-left: 10px;
      }
    }

    &.show-small {
      .vea-input-search-input {
        width: 140px;
        margin-left: 10px;
      }
    }

    &.show-mini {
      .vea-input-search-input {
        width: 120px;
        margin-left: 10px;
      }
    }
  }

</style>
