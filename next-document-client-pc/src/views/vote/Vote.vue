<template>
    <div>
      <el-menu
        :router="true"
        :default-active="activeIndex"
        mode="horizontal"
        @select="handleSelect"
      >
        <el-menu-item index="/layout/vote/vote_list?type=1">待完成的投票</el-menu-item>
        <el-menu-item index="/layout/vote/vote_list?type=2">我的所有投票</el-menu-item>
        <el-menu-item index="/layout/vote/vote_list?type=3">我发起的投票</el-menu-item>
        <el-menu-item index="/layout/vote/create_vote">新建投票</el-menu-item>
      </el-menu>
      <router-view></router-view>
    </div>
</template>

<script>
    export default {
      data() {
        return {
          activeIndex: "1",
        };
      },
      methods: {
        handleSelect(key, keyPath) {
          console.log(key, keyPath);
        }
      }
    }
</script>

<style scoped>

</style>
