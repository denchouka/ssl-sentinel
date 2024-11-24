<template>
  <div class="footer">
    Copyright © {{ 2024 }} - {{ new Date().getFullYear() }}  
    SSL Sentinel. All Rights Reserved. Powered by
    <a href="https://tch.cool" target="_blank">
      我剑也未尝不利
    </a>.
  </div>
</template>

<script>

export default {
  name: 'Footer'
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .footer {
    font-size: 18px;
    color: #685d5d;
    margin: 40px auto 100px 0px;
    text-align: center;
    font-weight: bold;
  }
</style>
