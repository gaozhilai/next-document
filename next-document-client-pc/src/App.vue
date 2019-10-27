<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style scoped>
  html,body, #app{height:100%; width:100%; overflow:hidden; margin:0;
    padding:0;}
</style>
