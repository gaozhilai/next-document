<template>
  <div>
    <el-row>
      个人中心
    </el-row>
    <el-row>
      <el-radio-group v-model="themeValue">
        <el-radio :label="1">天空蓝</el-radio>
        <el-radio :label="2">典雅黑</el-radio>
        <el-radio :label="3">少女粉</el-radio>
        <el-radio :label="4">橘猫橙</el-radio>
      </el-radio-group>
    </el-row>
    <el-row>
      <el-button type="primary" @click="chooseTheme">选择</el-button>
    </el-row>
  </div>
</template>

<script>
    export default {
      data() {
        return {
          themeValue: 1
        }
      },
      methods: {
        initThemeData: function () {
          let exist = this.$cookies.isKey('theme');
          if (!exist) {
            return;
          }
          let theme = this.$cookies.get('theme');
          if (theme === 'default') {
            this.themeValue = 1;
          }
          if (theme === 'black') {
            this.themeValue = 2;
          }
          if (theme === 'pink') {
            this.themeValue = 3;
          }
          if (theme === 'orange') {
            this.themeValue = 4;
          }
        },
        chooseTheme: function () {
          let themeValue = this.themeValue;
          let theme;
          if (themeValue === 1) {
            theme = 'default';
          }
          if (themeValue === 2) {
            theme = 'black';
          }
          if (themeValue === 3) {
            theme = 'pink';
          }
          if (themeValue === 4) {
            theme = 'orange';
          }
          this.$cookies.set('theme', theme, -1);
          document.body.className = 'theme-' + theme;
        }
      },
      created() {
        this.initThemeData();
      }
    }
</script>

<style scoped>

</style>
