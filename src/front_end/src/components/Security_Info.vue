<template>
  <div class="section">
    <div class="security-container">
      <div class="profile-item">
        <label for="current-password">当前密码:</label>
        <input id="current-password" type="password" v-model="security.currentPassword" placeholder="请输入当前密码" />
      </div>

      <div class="profile-item">
        <label for="new-password">新密码:</label>
        <input id="new-password" type="password" v-model="security.newPassword" placeholder="请输入新密码" />
      </div>

      <div class="profile-item">
        <label for="confirm-password">确认密码:</label>
        <input id="confirm-password" type="password" v-model="security.confirmPassword" placeholder="请确认新密码" />
      </div>

      <!-- 修改密码按钮 -->
      <div class="profile-item">
        <button @click="changePassword" class="save-btn">修改密码</button>
      </div>

    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'Security_Info',
  data() {
    return {
      security: {
        originPassword:'',
        currentPassword: '',
        newPassword: '',
        confirmPassword: '',
        identifier:''
      },
    };
  },
  methods: {
    // 获取用户信息
    async getOrignalPassword() {
      // 从 Vuex 获取当前用户的 UID
      const uid = this.$store.getters.uid;

      // 检查 UID 是否存在，如果不存在，显示错误信息
      if (!uid) {
        console.error('password: uid 未定义');
        this.errorMessage = '无法获取用户标识符。';
        return;
      }

      const url = 'http://localhost:8080/api/login/profile';

      this.isLoading = true;
      this.errorMessage = '';

      try {
        // 请求时传递 UID
        const response = await axios.get(url, {
          params: { identifier: uid },  // 使用 Vuex 中的 uid 作为请求参数
        });

        console.log('Response from API:', response.data); // 输出后端返回的原始数据

        if (response.status === 200 && response.data) {
          // 映射响应数据
          this.security.originPassword = response.data.userPSW;
          console.log('User data after mapping:', this.security.originPassword);
        } else {
          console.error('获取用户信息失败，响应状态码非200或无有效返回数据');
          this.errorMessage = '获取用户信息失败，请稍后重试。';
        }
      } catch (error) {
        // 错误处理
        if (error.response) {
          console.error('请求失败，服务器返回错误：', error.response.data);
          console.error('状态码：', error.response.status);
          this.errorMessage =
              error.response.data.message || '服务器错误，无法获取用户信息。';
        } else if (error.request) {
          console.error('请求失败，未收到服务器响应');
          this.errorMessage = '网络错误，无法获取用户信息。';
        } else {
          console.error('请求失败，配置错误：', error.message);
          this.errorMessage = '请求配置错误，请联系开发者。';
        }
      } finally {
        // 请求结束，设置加载状态
        this.isLoading = false;
      }
    },

    // 修改密码操作
    changePassword() {
      const { originPassword,currentPassword, newPassword, confirmPassword } = this.security;
      if(originPassword!=currentPassword){
        alert('原密码不正确！');
        return;
      }

      if (!currentPassword || !newPassword || !confirmPassword ) {
        alert('请填写所有字段');
        return;
      }

      if (newPassword !== confirmPassword) {
        alert('新密码和确认密码不匹配');
        return;
      }

      if (newPassword.length < 8) {
        alert('新密码长度至少为8个字符');
        return;
      }

      console.log('修改密码:', currentPassword, newPassword);
      alert('密码修改成功');
    },
  },
  mounted() {
    this.getOrignalPassword();
  }
};
</script>

<style scoped>
/* 设置容器的最大宽度、居中显示，并调整上下内边距 */
.section {
  max-width: 600px;
  margin: 0 auto;
  padding: 50px 20px;  /* 增加上下内边距，避免内容贴近顶部 */
  background-color: transparent;
  border: none;
  box-shadow: none;
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* 内容自上而下排列 */
  min-height: 80vh; /* 最小高度控制为60% */
}

/* 统一字体 */
h2 {
  text-align: left;
  font-size: 1.8em;
  font-weight: 600;
  color: #333;
  margin-bottom: 30px;
}

.profile-item {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  gap: 12px;
}

.profile-item label {
  font-size: 1em;
  font-weight: bold;
  color: #555;
  width: 120px;
  margin-right: 10px;
}

.profile-item input {
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 1em;
  width: 100%;
  max-width: 300px;  /* 限制最大宽度 */
  transition: border-color 0.3s;
}

.profile-item input:focus {
  border-color: #007bff;
  outline: none;
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
  max-width: 200px;  /* 限制最大宽度 */
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
