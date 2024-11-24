<template>
  <div class="header">
    <div class="left">
      <img class="logo" src="../../assets/ssl.png" alt="Logo">
      <h3 class="title">SSL Sentinel</h3>
    </div>
    <div class="right">
      <el-button round class="logout1" type="info">退出登录</el-button>
    </div>
  </div>
</template>

<script>
import { ElButton } from 'element-plus';

export default {
  name: 'Header',
  components: {
    ElButton
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .header {
    display: flex;
    justify-content: space-between; /* 使左右两侧内容对齐 */
    margin-top: 60px;
    margin-bottom: 100px;
  }

  .left {
    display: flex;
    align-items: center; /* 垂直居中对齐 */
  }

  .logo {
    width: 50px;
    height: 50px;
    margin: 0 10px 0 10px;
  }

  .title {
    font-size: 40px;
    color: #2a1a1a;
    font-weight: bold;
  }

  .right {
    display: flex;
    margin-right: 20px;
    justify-content: flex-end; /* 右对齐 */
  }
</style>