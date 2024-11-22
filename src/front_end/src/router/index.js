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
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
