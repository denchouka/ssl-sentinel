import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: () => import('../views/Login.vue')
    },
    {
      path: '/home',
      name: 'Home',
      component: () => import('../views/Home.vue'),
      meta: {title: 'Home', show: false}
    },
    {
      path: '/addEvent',
      name: 'AddEvent',
      component: () => import('../views/AddEvent.vue'),
      meta: {title: '添加事件', show: true}
    },
    {
      path: '/eventList',
      name: 'EventList',
      component: () => import('../views/EventList.vue'),
      meta: {title: '事件列表', show: true}
    },
    {
      path: '/eventHistory',
      name: 'EventHistory',
      component: () => import('../views/EventHistory.vue'),
      meta: {title: '执行历史', show: true}
    }
  ]
})

export default router
