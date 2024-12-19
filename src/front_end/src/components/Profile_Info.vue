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
        <!-- 头像框 -->
        <div class="avatar-frame">
          <img
              :src="avatar_url ? require(`@/assets/imgs/${avatar_url}`) : require('@/assets/imgs/homepage/login.png')"
              alt="头像"
              class="avatar"
          />
        </div>

        <!-- 头像选择按钮 -->
        <div v-if="isHover" class="avatar-mask"></div>
        <button
            v-if="isHover"
            @click="toggleAvatarSelector"
            class="change-avatar-btn"
        >
          更换头像
        </button>

        <!-- 头像选择列表 -->
        <div v-if="showAvatarSelector" class="avatar-selector" @mouseleave="hideAvatarSelector">
          <div
              v-for="(avatar, index) in avatarList"
              :key="index"
              class="avatar-option"
              @click="selectAvatar(avatar)"
          >
            <img :src="require(`@/assets/imgs/${avatar}`)" alt="头像选项" class="avatar-option-img" />
          </div>
        </div>
      </div>
    </div>

    <!-- 用户信息部分 -->
    <div class="profile-item">
      <label for="user-id">用户ID:</label>
      <input id="user-id" v-model="id" disabled />
    </div>

    <div class="profile-item">
      <label for="user-email">邮箱:</label>
      <input id="user-email" v-model="email" disabled />
    </div>

    <div class="profile-item">
      <label for="user-name">用户名:</label>
      <input
          id="user-name"
          v-model="username"
          :disabled="isUsernameLocked"
          placeholder="请输入用户名"
      />
    </div>

    <!-- 修改密码按钮 -->
    <div class="save-item">
      <button @click="savechange" class="save-btn">保存</button>
      <button @click="cancelchange" class="cancel-btn">取消</button>
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
import { mapState, mapActions } from "vuex";

export default {
  name: "Profile_Info",
  props: {
    identifier: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      isUsernameLocked: false,
      isHover: false,
      id: '',
      email: '',
      username: '',
      avatar: '',
      avatar_url: '',
      showAvatarSelector: false, // 控制头像选择器的显示与隐藏
      avatarList: [
        "homepage/default-avatar.jpg",
        "homepage/image_1.jpeg", // 这里是你指定的头像列表
        "homepage/image_2.jpeg",
        "homepage/image_3.jpeg",
        "homepage/image_4.jpeg"
      ],
      isLoading: false,
      errorMessage: '',
      originalData: {}
    };
  },
  computed: {
    ...mapState({
      isLoggedIn: state => state.isLoggedIn,
      username: state => state.username,
      avatar_url: state => state.avatar_url,
    })
  },
  methods: {
    ...mapActions({
      updateUserInfo: 'updateUserInfo' // 映射 Vuex 的 action
    }),

    // 获取用户信息
    async getUserInfo() {
      const uid = this.$store.getters.uid;
      if (!uid) {
        console.error('uid 未定义');
        this.errorMessage = '无法获取用户信息，uid 未定义。';
        return;
      }

      const url = 'http://localhost:8080/api/login/profile';

      this.isLoading = true;
      this.errorMessage = '';

      try {
        const response = await axios.get(url, {
          params: { identifier: uid },
        });

        if (response.status === 200 && response.data) {
          this.id = response.data.uid;
          this.email = response.data.email;
          this.username = response.data.uname;
          this.avatar_url = response.data.avatarUser;
          this.avatar = response.data.avatarUser;

          // 备份原始数据
          this.originalData = {
            username: this.username,
            avatar_url: this.avatar_url
          };
        } else {
          console.error('获取用户信息失败，响应状态码非200或无有效返回数据');
          this.errorMessage = '获取用户信息失败，请稍后重试。';
        }
      } catch (error) {
        if (error.response) {
          this.errorMessage = error.response.data.message || '服务器错误，无法获取用户信息。';
        } else if (error.request) {
          this.errorMessage = '网络错误，无法获取用户信息。';
        } else {
          this.errorMessage = '请求配置错误，请联系开发者。';
        }
      } finally {
        this.isLoading = false;
      }
    },

    // 保存修改
    async savechange() {
      if (this.isLoading) return;

      this.isLoading = true;
      this.errorMessage = '';



      // 使用 FormData 来发送表单数据
      const formData = new FormData();
      formData.append("uid", this.id);
      formData.append("newUserName", this.username);
      formData.append("newAvatarUrl", this.avatar_url);

      try {
        const response = await axios.post('http://localhost:8080/api/login/update', formData);

        if (response.status === 200) {
          const data = response.data;

          if (data.nameUpdated || data.avatarUpdated) {
            alert('用户信息更新成功');
            this.$store.commit('UPDATE_USER_INFO', {
              username: this.username,
              avatar_url: this.avatar_url
            });
          } else {
            this.errorMessage = '没有任何信息更新。';
          }
        } else {
          this.errorMessage = '保存失败，请稍后再试。';
        }
      } catch (error) {
        console.error('保存失败:', error);
        this.errorMessage = '保存失败，请稍后再试。';
      } finally {
        this.isLoading = false;
      }
    },

    // 取消修改，恢复原始数据
    cancelchange() {
      this.username = this.originalData.username;
      this.avatar_url = this.originalData.avatar_url;
      this.$refs.fileInput.value = '';
    },

    // 选择头像
    selectAvatar(avatar) {
      this.avatar_url = avatar;  // 更新头像URL
      this.showAvatarSelector = false; // 关闭选择框
      this.isHover = false; // 更新完头像后隐藏悬浮按钮
    },

    // 切换头像选择器的显示与隐藏
    toggleAvatarSelector() {
      this.showAvatarSelector = !this.showAvatarSelector;
    },

    // 隐藏头像选择器
    hideAvatarSelector() {
      this.showAvatarSelector = false;
    },
  },
  mounted() {
    this.getUserInfo();
  },
};
</script>






<style scoped>
/* 头像框样式 */
.avatar-frame {
  position: relative;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  border: 3px solid #ddd;
  overflow: hidden;
}

.avatar {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.avatar-selector {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
  position: absolute;
  top: 130%;
  background: white;
  border: 1px solid #ccc;
  padding: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  z-index: 20;
}

.avatar-option {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  overflow: hidden;
  cursor: pointer;
}

.avatar-option-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

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

/* 更换头像按钮样式 */
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
  transition: all 0.3s ease;
}

.change-avatar-btn:hover {
  transform: scale(1.05); /* 鼠标悬停时按钮放大 */
}

/* 遮罩层样式 */
.avatar-mask {
  position: absolute;
  top: 0;
  left: 0;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background: rgba(0, 0, 0, 0.4);
  z-index: 10;
  transition: background 0.3s ease;
}

.avatar-mask:hover {
  background: rgba(0, 0, 0, 0.6); /* 鼠标悬停时背景变深 */
}

/* 用户信息部分 */
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

.save-item {
  display: flex;
  justify-content: center; /* 水平居中 */
  gap: 10px;               /* 按钮之间的间距 */
}

.save-btn, .cancel-btn {
  padding: 12px 20px;
  background-color: #218838;
  color: white;
  font-size: 1.2em;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s, box-shadow 0.2s;
  max-width: 200px; /* 限制最大宽度 */
  flex: 1;           /* 让每个按钮占据相等的空间 */
  text-align: center;/* 确保文本居中 */
}

.save-btn:hover, .cancel-btn:hover {
  background-color: rgba(33, 136, 56, 0.9);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.save-btn:active, .cancel-btn:active {
  transform: scale(0.98);
}

</style>
