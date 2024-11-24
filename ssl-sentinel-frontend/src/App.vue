<template>
  <div class="app">
    <!--显示菜单-->
    <div v-if="isShowMenu">
      <el-container>
        <el-header>HeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeaderHeader</el-header>
        <el-container>
          <el-aside width="200px">
            <el-menu router default-active="1">
              <el-menu-item v-for="route in routes" :key="route.path" :index="route.path">
                {{ route.meta.title }}
              </el-menu-item>
            </el-menu>
          </el-aside>
          <el-main>
            <router-view></router-view>
          </el-main>
        </el-container>
        <el-footer>FooterFooterFooterFooterFooterFooterFooterFooterFooterFooterFooterFooter</el-footer>
      </el-container>
    </div>
    <!--不显示菜单-->
    <div v-else>
      <router-view></router-view>
    </div>

  </div>
</template>

<script>
import { ElMenu, ElMenuItem } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
import { computed, ref, onMounted } from 'vue'
export default {
  name: 'app',
  components: {
    ElMenu,
    ElMenuItem
  },
  setup() {
    // 获取路由器实例
    const router = useRouter()
    // 获取当前路由的信息
    const route = useRoute()
    console.log(route)

    // 计算属性，从路由配置中提取菜单项
    const routes = computed(() => {
      return router.getRoutes().filter(route => route.meta && route.meta.title && route.meta.menu && route.meta.show)
    })

    // 判断当前页是否显示menu
    const isShowMenu = computed(() => {
      return route.meta && route.meta.title && route.meta.menu
    })

    return {
      routes,
      isShowMenu
    }
  }
}
</script>

<style>
</style>
