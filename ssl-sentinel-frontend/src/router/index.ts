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
      path: '/taskList',
      name: 'TaskList',
      component: () => import('../views/TaskList.vue'),
      meta: {title: '任务列表', menu: true, show: true, icon: './assets/list.png'}
    },
    {
      path: '/addTask',
      name: 'AddTask',
      component: () => import('../views/AddTask.vue'),
      meta: {title: '添加任务', menu: true, show: true, icon: './assets/add.png'}
    },
    {
      path: '/taskHistory',
      name: 'TaskHistory',
      component: () => import('../views/TaskHistory.vue'),
      meta: {title: '执行历史', menu: true, show: true, icon: './assets/history.png'}
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
