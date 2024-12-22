<template>
  <div class="header">
    <div class="left">
      <img class="logo" src="../../assets/ssl.png" alt="Logo">
      <h3 class="title">SSL Sentinel</h3>
    </div>
    <div class="right">
      <el-button round type="info" :loading="loading" @click="toLogout">退出登录</el-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { logout } from '@/api/index'
import { ElMessageBox } from 'element-plus'

// 定义loading状态
var loading = ref(false)

// 获取路由器实例
const router = useRouter()

/**
 * 退出登录
 */
const toLogout = () => {
  loading.value = true

  // 弹框提醒
  ElMessageBox.confirm(
    '确定要退出登录吗',
    '提示',
    {
      confirmButtonText: '确定退出',
      cancelButtonText: '取消',
      type: 'warning',
      draggable: true
    }
  )
  .then(() => {
    // 退出
    logout().then(() => {
      // 清空localStorage
      localStorage.clear()

      loading.value = false

      // 跳转登录页
      window.location.href = '/'
    })
  })
  .catch(() => {
    // 取消
    loading.value = false
  })
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