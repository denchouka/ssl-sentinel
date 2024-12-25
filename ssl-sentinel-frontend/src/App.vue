<template>
  <div class="app">
    <!--显示菜单-->
    <div v-if="isShowMenu">
      <el-container class="container">
        <!--header 顶栏容器-->
        <el-header class="header"><Header></Header></el-header>
        <el-divider></el-divider>
        <el-container class="middle">
          <!--aside 侧边栏容器-->
          <el-aside class="aside">
            <el-menu router default-active="1">
              <el-menu-item v-for="route in routes" :key="route.path" :index="route.path">
                <div class="menu-item">
                  <img :src="getIcon(route.meta.icon)" :alt="route.meta.title" class="menu-icon">
                  {{ route.meta.title }}
                </div>
              </el-menu-item>
            </el-menu>
          </el-aside>
          <!--main 主要区域容器-->
          <el-main class="main">
            <router-view></router-view>
          </el-main>
        </el-container>
        <el-divider></el-divider>
        <!--footer 底栏容器-->
        <el-footer class="fotter"><Footer></Footer></el-footer>
      </el-container>
    </div>
    <!--不显示菜单-->
    <div v-else>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'
import Header from '@/views/container/Header.vue'
import Footer from '@/views/container/Footer.vue'

export default {
  name: 'app',
  components: {
    Header,
    Footer
  },
  setup() {
    // 获取路由器实例
    const router = useRouter()
    // 获取当前路由的信息
    const route = useRoute()

    // 计算属性，从路由配置中提取菜单项
    const routes = computed(() => {
      return router.getRoutes().filter(route => route.meta && route.meta.title && route.meta.menu && route.meta.show)
    })

    // 判断当前页是否显示menu
    const isShowMenu = computed(() => {
      return route.meta && route.meta.title && route.meta.menu
    })

    const getIcon = (icon) => {
      return new URL(icon, import.meta.url).href
    }

    return {
      routes,
      isShowMenu,
      getIcon
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .container {
    position: fixed;
    height: 100%;
    width: 100%;
    background-color: white;

    .header {
      height: 10%;
    }

    .aside {
      width: 200px;
      border-right: 1px var(--el-border-color) var(--el-border-style);
    }

    .menu-item {
      font-size: 17px;
      display: flex;
      align-items: center;
    }

    .menu-icon {
        width: 20px;
        height: 20px;
        margin-right: 10px;
    }

    .el-menu {
      border: none;
      background-color: white;
    }

    .middle {
      height: 80%;
    }

    .fotter {
      height: 10%;
    }
  }

  :app {
    --el-menu-bg-color: rgb(249, 250, 252);
  }
</style>
