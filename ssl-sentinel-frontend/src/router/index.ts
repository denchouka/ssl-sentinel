import { createRouter, createWebHistory } from 'vue-router'

/**
 * meta {
 *  title: 页面title
 *  menu: 是否是菜单页面
 *  show: 在菜单页面是否显示
 * }
 */
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: () => import('../views/Login.vue'),
      meta: {title: '登录', menu: false}
    },
    {
      path: '/addEvent',
      name: 'AddEvent',
      component: () => import('../views/AddEvent.vue'),
      meta: {title: '添加事件', menu: true, show: true}
    },
    {
      path: '/eventList',
      name: 'EventList',
      component: () => import('../views/EventList.vue'),
      meta: {title: '事件列表', menu: true, show: true}
    },
    {
      path: '/eventHistory',
      name: 'EventHistory',
      component: () => import('../views/EventHistory.vue'),
      meta: {title: '执行历史', menu: true, show: true}
    },
    {
      path: '/:pathMatch(.*)*',
      name: '404',
      component: () => import('../views/error/404.vue'),
      meta: {title: '404', menu: true, show: false}
    }
  ]
})

export default router
