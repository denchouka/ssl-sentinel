<template>
  <div class="home">
    <el-menu :router="true" default-active="2" class="el-menu-vertical-demo">
      <el-menu-item v-for="route in routes" :key="route.path" :index="route.path">
        {{ route.meta.title }}
      </el-menu-item>
    </el-menu>
    <router-view></router-view>
  </div>
</template>

<script>
import { ElMenu, ElMenuItem } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'
export default {
  name: 'home',
  components: {
    ElMenu,
    ElMenuItem
  },
  setup() {
    const router = useRouter()
    const route = useRoute()

    // 计算属性，从路由配置中提取菜单项
    const routes = computed(() => {
      return router.getRoutes().filter(route => route.meta && route.meta.title && route.meta.show)
    })

    return {
      routes
    }
  }
}
</script>

<style>
</style>
