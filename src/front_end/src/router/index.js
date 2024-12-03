import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/views/HomePage.vue'; // 确保路径和组件名正确
import IHome from '@/views/IHome.vue'; // 新增 IHome 页面
import IDetail from '@/views/IDetail.vue';
import DoctorPage from '@/views/DoctorPage.vue';
import Doctor_Detail from '@/views/Doctor_Detail.vue';
import Resources from '@/views/Resources.vue';
import VideoResources from '@/views/VideoResources.vue';
import ArticleResources from '@/views/ArticleResources.vue';
import AIChat from '../views/AIChat.vue'
import Profile_Page from '@/views/Profile_Page.vue';
import TestPage from "@/views/test.vue";
import MyCollection from '@/views/MyCollection.vue';
import axios from "axios";

const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: HomePage
  },
  {
    path: '/symptoms',
    name: 'IHome',
    component: IHome
  },
  {
    path: '/article/:id',
    name: 'ArticleDetail',
    component: IDetail,
    props: true
  },
  {
    path: '/doctors',
    name: 'DoctorPage',
    component: DoctorPage
  },
  {
    path: '/doctor_detail',
    name: 'Doctor_Detail',
    component: Doctor_Detail
  },
  {
    path: '/Resources',
    name: 'ResourcePage',
    component: Resources
  },
  {
    path: '/resources/video',
    name: 'VideoResourcePage',
    component: VideoResources
  },
  {
    path: '/resources/article',
    name: 'ArticleResourcePage',
    component: ArticleResources
  },
  {
    path: '/sandbox',
    name: 'Sandbox',
    component: AIChat
  },
  {
    path:'/profile',
    name: 'Profile_Page',
    component: Profile_Page

  },
  {
    path: '/test',
    name:'TestPage',
    component:TestPage
  },
  {
    path: '/collection',
    name: 'MyCollection',
    component: MyCollection
  },
  // Catch-all route for any other paths that are not APIs
  {
    path: '/:catchAll(.*)',
    redirect: '/404' // Redirect to a 404 page or handle it as needed
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

// Add a beforeEach guard to prevent Vue Router from handling /api requests
router.beforeEach((to, from, next) => {
  if (to.path.startsWith('/api')) {
    // 处理跨域请求，这里假设后端允许所有来源的跨域请求（实际情况中需要根据后端配置来设置正确的跨域策略）
    axios.defaults.withCredentials = true;
    axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
    axios.defaults.headers.common['Access-Control-Allow-Methods'] = 'GET, POST, PUT, DELETE';
    axios.defaults.headers.common['Access-Control-Allow-Headers'] = 'Content-Type, Authorization';

    // 允许API请求通过，但不进行路由导航（因为这是API请求，不是页面导航请求）
    next(false);
  } else {
    next();
  }
});

export default router;
