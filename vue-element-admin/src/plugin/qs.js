import qs from 'qs'

const qsPlugin = {
  install: function (Vue, options) {
    Vue.prototype.$qs = qs;
  }
};

export default qsPlugin;
