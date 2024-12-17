import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';  // 引入 Vuex store
import './assets/global.css';

// 创建 Vue 应用并使用 router 和 store
createApp(App)
    .use(router)  // 使用 Vue Router
    .use(store)   // 使用 Vuex Store
    .mount('#app');  // 挂载应用
