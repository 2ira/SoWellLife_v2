 <!-- AppNavbar.vue -->
<template>
  <div class="nav-container">
    <header>
      <div class="header-content">
        <div class="logo">
          <router-link to="/">
            <img src="@/assets/logo.png" alt="SoWellLife" class="logo-img">
          </router-link>
        </div>

        <div class="search-login">
          <div class="search-wrapper">
            <input type="text" placeholder="搜索..." class="search-bar">
            <span class="search-icon">🔍</span>
          </div>
            <div class="avatar-container" @click="goToProfile">
              <img 
                :src="avatarUrl || require('@/assets/imgs/homepage/default-avatar.jpg')" 
                alt="用户头像" 
                class="avatar-img"
              >
            </div>
          <button class="login-button">登录</button>
        </div>
      </div>
    </header>

    <nav class="navbar">
      <ul class="nav-links">
        <li><router-link to="/">首页</router-link></li>
        <li><router-link to="/symptoms">症状</router-link></li>
        <li class="resource-menu" @mouseover="showDropdown = true" @mouseleave="showDropdown = false">
          <span class="resource-link">资源</span>
          <div class="dropdown" v-show="showDropdown">
            <a href="#" @click.prevent="goToResources('video')">视频</a>
            <a href="#" @click.prevent="goToResources('article')">文章</a>
          </div>
        </li>
        <li><router-link to="/doctors">医生</router-link></li>
        <li><router-link to="/tests">测试</router-link></li>
        <li><router-link to="/sandbox">沙包</router-link></li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  name: "AppNavbar",
  data() {
    return {
      avatarUrl: null,
      showDropdown: false
    };
  },
  methods: {
    goToProfile() {
      this.$router.push('/profile');
    },
    goToResources(type) {
      // 根据传入的类型进行路由跳转逻辑
      if (type === 'video') {
        this.$router.push('/resources/video');
      } else if (type === 'article') {
        this.$router.push('/resources/article');
      }
    }
  }
};
</script>

  
  <style scoped>
.nav-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 80px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.05);
  background-color: rgb(68, 165, 146);
  z-index: 2000;
}

header {
  background-color: rgb(255, 255, 255);
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  padding: 1rem 0;
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 2rem;
}

.logo {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

.logo-img {
  height: 40px; /* 根据需要调整大小 */
  width: auto;
  /* 如果需要在hover时添加效果 */
  transition: transform 0.3s ease;
}


.search-login {
  display: flex;
  align-items: center;
  gap: 1.5rem;
  margin-left: auto;
}

.search-wrapper {
  position: relative;
}

.search-bar {
  padding: 0.5rem 2.5rem 0.5rem 1rem;
  border: 1px solid #e0e0e0;
  border-radius: 20px;
  width: 150px;
  font-size: 0.9rem;
  transition: all 0.3s ease;
}

.search-bar:focus {
  outline: none;
  border-color: #4CAF50;
  box-shadow: 0 0 0 2px rgba(76, 175, 80, 0.1);
}

.search-icon {
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
  color: #666;
  cursor: pointer;
}

.avatar-container {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  overflow: hidden;
  cursor: pointer;
  transition: transform 0.2s ease;
  border: 2px solid transparent;
}

.avatar-container:hover {
  transform: scale(1.05);
  border-color: #2C7A7B;
}

.avatar-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.login-button {
  padding: 0.5rem 1.5rem;
  background-color: #66ac91;
  color: white;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  font-weight: 500;
  transition: background-color 0.3s ease;
}

.login-button:hover {
  background-color: #64c8b7;
}

.navbar {
  background-color: #8cb3da;
  padding: 0.5rem 15rem;
  border-bottom: 1px solid #e0e0e0;
}

.nav-links {
  display: flex;
  justify-content: center;
  gap: 6rem;
  list-style: none;
  margin: 0;
  padding: 0;
}

.nav-links a {
  color: #666;
  text-decoration: none;
  font-size: 1.1rem;
  font-weight: 550;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: all 0.3s ease;
}

.nav-links a:hover {
  color: #4CAF50;
  background-color: #f0f0f0;
}

.resource-menu {
  position: relative;
}

.menu-wrapper {
  position: relative;
  padding: 0.5rem 0;
}

.resource-link {
  color: #666;
  text-decoration: none;
  font-size: 1.1rem;
  font-weight: 550;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: all 0.3s ease;
  cursor: pointer;
}

.resource-link:hover {
  color: #4CAF50;
}

.dropdown {
  position: absolute;
  top: 100%;
  left: 0;
  background-color: white;
  min-width: 100px;
  box-shadow: 0 1px 4px rgba(0,0,0,0.1);
  border-radius: 4px;
  /* 移除 margin-top，改用 padding-top 消除间隙 */
  padding-top: 0.1rem;
  z-index: 1000;
  display: flex;
  flex-direction: column;
}

.dropdown::before {
  content: '';
  position: absolute;
  top: -0.5px; /* 延伸覆盖区域 */
  left: 0;
  right: 0;
  height: 0.5px;
  background: transparent; /* 保持透明 */
}

.dropdown a {
  padding: 0.55rem 1rem;
  color: #666;
  text-decoration: none;
  transition: all 0.3s ease;
  display: block;
  white-space: nowrap;
}

.dropdown a:hover {
  background-color: #f0f0f0;
  color: #4CAF50;
}
  </style>
  
