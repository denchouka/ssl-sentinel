import { createRouter, createWebHistory } from 'vue-router'
import listIcon from '../assets/list.png'
import addIcon from '../assets/add.png'

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
      component: () => import('../views/task/TaskList.vue'),
      meta: {title: '任务列表', menu: true, show: true, icon: listIcon}
    },
    {
      path: '/addTask',
      name: 'AddTask',
      component: () => import('../views/task/AddTask.vue'),
      meta: {title: '添加任务', menu: true, show: true, icon: addIcon}
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
