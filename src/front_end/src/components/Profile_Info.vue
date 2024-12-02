<template>
  <div class="profile-container">
    <!-- 头像部分 -->
    <div class="avatar-container">
      <div class="avatar-wrapper" @mouseover="isHover = true" @mouseleave="isHover = false">
        <img :src="user.avatar || require('@/assets/imgs/homepage/default-avatar.jpg')" alt="头像" class="avatar" />
        <div v-if="isHover" class="avatar-mask"></div>
        <button v-if="isHover" @click="triggerFileInput" class="change-avatar-btn">更换头像</button>
      </div>
    </div>

    <div class="profile-item">
      <label for="user-id">用户ID:</label>
      <input id="user-id" v-model="user.id" disabled />
    </div>

    <div class="profile-item">
      <label for="user-email">邮箱:</label>
      <input id="user-email" v-model="user.email" disabled />
    </div>

    <div class="profile-item">
      <label for="user-name">用户名:</label>
      <input id="user-name" v-model="user.username" :disabled="isUsernameLocked" placeholder="请输入用户名" />
    </div>

    <div class="profile-item">
      <button @click="saveProfile" class="save-btn">保存</button>
    </div>

    <input type="file" ref="fileInput" style="display: none;" @change="onFileChange" />
  </div>
</template>

<script>
export default {
  name: "Profile_Info",
  data() {
    return {
      isUsernameLocked: false,
      isHover: false,
      user: {
        id: '1234567890',
        email: 'example@example.com',
        username: '默认用户名',
        avatar: null, // 初始没有头像
      },
    };
  },
  methods: {
    onFileChange(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.user.avatar = e.target.result;
        };
        reader.readAsDataURL(file);
      }
    },

    triggerFileInput() {
      this.$refs.fileInput.click();
    },

    saveProfile() {
      console.log('保存用户资料:', this.user);
      alert('资料已保存');
    },
  },
};
</script>

<style scoped>
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
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
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
  width: 100%; /* 使用100%宽度，让它填满可用空间 */
  max-width: 300px; /* 限制最大宽度，避免过长 */
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

.save-btn {
  padding: 12px 20px;
  background-color: #218838;
  color: white;
  font-size: 1.2em;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s, box-shadow 0.2s;
  display: block;
  width: 100%;
  max-width: 200px; /* 设置按钮的最大宽度 */
  margin-left: 178px;
}

.save-btn:hover {
  background-color: rgba(33, 136, 56, 0.9);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.save-btn:active {
  transform: scale(0.98);
}
</style>
