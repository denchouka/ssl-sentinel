<template>
  <div class="not-found">
    <img src="../../assets/404.png"></img>
    <p>您访问的页面不存在。</p>
  </div>
</template>

<script>
export default {
  name: 'NotFound'
};
</script>

<style scoped>
  .not-found {
    text-align: center;
    margin-top: 50px;
  }

  p {
    margin-top: 30px;
    color: #685d5d;
  }
</style>