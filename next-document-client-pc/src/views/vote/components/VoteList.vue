<template>
    <div>
      投票列表
    </div>
</template>

<script>
    export default {
      data() {
        return {
          type: 1,
        };
      },
      methods: {

      },
      created() {

      },
      mounted() {

      },
      watch: {
        '$route' (to, from) {
          // 对路由变化作出响应...
          let type = this.$route.query.type;
          console.log("to ", to);
          console.log("from ", from);
          console.log("type页面参数: ", type);
        }
      }
    }
</script>

<style scoped>

</style>
