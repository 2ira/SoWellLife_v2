<template>
  <div class="profile-container">
    <!-- 错误信息显示 -->
    <div v-if="errorMessage" class="error-message">
      {{ errorMessage }}
    </div>

    <!-- 加载覆盖层 -->
    <div v-if="isLoading" class="loading-overlay">
      <p>加载中...</p>
    </div>

    <!-- 头像部分 -->
    <div class="avatar-container">
      <div
          class="avatar-wrapper"
          @mouseover="isHover = true"
          @mouseleave="isHover = false"
      >
        <!-- 显示头像 -->
        <img
            :src="avatar || require('@/assets/imgs/homepage/default-avatar.jpg')"
            alt="头像"
            class="avatar"
        />
        <!-- 鼠标悬浮时显示更换头像按钮 -->
        <div v-if="isHover" class="avatar-mask"></div>
        <button
            v-if="isHover"
            @click="triggerFileInput"
            class="change-avatar-btn"
        >
          更换头像
        </button>
      </div>
    </div>

    <!-- 用户信息部分 -->
    <div class="profile-item">
      <label for="user-id">用户ID:</label>
      <!-- 显示用户ID -->
      <input id="user-id" v-model="id" disabled />
    </div>

    <div class="profile-item">
      <label for="user-email">邮箱:</label>
      <!-- 显示邮箱 -->
      <input id="user-email" v-model="email" disabled />
    </div>

    <div class="profile-item">
      <label for="user-name">用户名:</label>
      <!-- 显示用户名 -->
      <input
          id="user-name"
          v-model="username"
          :disabled="isUsernameLocked"
          placeholder="请输入用户名"
      />
    </div>

    <!-- 隐藏的文件输入框，用于更换头像 -->
    <input
        type="file"
        ref="fileInput"
        style="display: none;"
        @change="onFileChange"
        accept="image/*"
    />
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Profile_Info",
  props: {
    identifier: {  // 从父组件接收 identifier 参数
      type: String,
      required: true
    }
  },
  data() {
    return {
      isUsernameLocked: false,  // 控制用户名是否可以编辑
      isHover: false,           // 控制头像是否悬浮显示更换按钮
      id: '',         // 用户ID
      email: '',      // 用户邮箱
      username: '',   // 用户名
      avatar: '',     // 用户头像
      isLoading: false,  // 控制加载状态
      errorMessage: '',  // 错误信息
    };
  },
  methods: {
    // 获取用户信息
    async getUserInfo() {
      // 从 Vuex 获取用户的 uid
      const uid = this.$store.getters.uid;
      console.log('profile.uid:{}', uid); // 输出后端返回的原始数据
      if (!uid) {
        console.error('uid 未定义');
        this.errorMessage = '无法获取用户信息，uid 未定义。';
        return;
      }

      const url = 'http://localhost:8080/api/login/profile';

      this.isLoading = true;
      this.errorMessage = '';

      try {
        // 使用 uid 作为参数请求用户信息
        const response = await axios.get(url, {
          params: { identifier: uid },
        });

        console.log('Response from API:', response.data); // 输出后端返回的原始数据

        if (response.status === 200 && response.data) {
          // 映射后端返回的字段到 Vue data
          this.id = response.data.uid;
          this.email = response.data.email;
          this.username = response.data.uname; // 映射后端的 UName 为 username
          this.avatar = response.data.avatarUser; // 假设 avatarUser 是头像字段

          console.log('User data after mapping:', this.id, this.email, this.username, this.avatar); // 输出映射后的数据

        } else {
          console.error('获取用户信息失败，响应状态码非200或无有效返回数据');
          this.errorMessage = '获取用户信息失败，请稍后重试。';
        }
      } catch (error) {
        if (error.response) {
          console.error('请求失败，服务器返回错误：', error.response.data);
          console.error('状态码：', error.response.status);
          this.errorMessage = error.response.data.message || '服务器错误，无法获取用户信息。';
        } else if (error.request) {
          console.error('请求失败，未收到服务器响应');
          this.errorMessage = '网络错误，无法获取用户信息。';
        } else {
          console.error('请求失败，配置错误：', error.message);
          this.errorMessage = '请求配置错误，请联系开发者。';
        }
      } finally {
        this.isLoading = false;
      }
    },


    // 处理文件选择（用于更换头像）
    onFileChange(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.avatar = e.target.result; // 更新头像
        };
        reader.readAsDataURL(file);
      }
    },

    // 激活文件输入框，允许用户选择图片更换头像
    triggerFileInput() {
      this.$refs.fileInput.click();
    },
  },
  mounted() {
    // 组件加载时获取用户信息
    this.getUserInfo();
    console.log('Received Identifier in Profile_Info:', this.identifier);
  },
};
</script>

<style scoped>
/* 样式部分保持不变或调整，以适应前端界面的需求 */

/* 加载覆盖层样式 */
.loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.error-message {
  color: #f44336;
  text-align: center;
  margin-bottom: 20px;
}

.profile-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: transparent;
  border: none;
  box-shadow: none;
}

.avatar-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.avatar-wrapper {
  position: relative;
}

.avatar {
  width: 120px;
  height: 120px;
  object-fit: cover;
  border-radius: 50%;
  border: 3px solid #ddd;
}

.change-avatar-btn {
  position: absolute;
  top: 0;
  left: 0;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  border: 3px solid #ddd;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  font-weight: bold;
  z-index: 11;
  display: block;
  text-align: center;
  line-height: 120px;
}

.avatar-mask {
  position: absolute;
  top: 0;
  left: 0;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background: rgba(0, 0, 0, 0.4);
  z-index: 10;
}

.profile-item {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.profile-item label {
  font-size: 1em;
  font-weight: bold;
  color: #555;
  width: 120px;
  margin-right: 10px;
}

.profile-item input {
  width: 100%;
  max-width: 300px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 1em;
  color: #333;
}

.profile-item input:disabled {
  background-color: #f1f1f1;
  color: #888;
}
</style>
