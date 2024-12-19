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
        currentPassword: '',  // 当前密码
        newPassword: '',      // 新密码
        confirmPassword: '',  // 确认密码
        uid: ''        // 用户标识符（UID）
      },
      isLoading: false,  // 加载状态
      errorMessage: ''   // 错误信息
    };
  },
  methods: {
    // 获取当前用户的UID，并初始化相关数据
    async getOrignalPassword() {
      this.security.uid = this.$store.getters.uid;

      // 检查 UID 是否存在
      if (!this.security.uid) {
        console.error('password: uid 未定义');
        this.errorMessage = '无法获取用户标识符。';
        return;
      }

      // 发送请求获取用户原始密码等信息
      const url = 'http://localhost:8080/api/login/profile';

      try {
        const response = await axios.get(url, {
          params: { identifier: this.security.uid }
        });

        if (response.status === 200 && response.data) {
          // 映射响应数据
          this.security.originPassword = response.data.userPSW;
        } else {
          console.error('获取用户信息失败');
          this.errorMessage = '获取用户信息失败，请稍后重试。';
        }
      } catch (error) {
        console.error('请求失败', error);
        this.errorMessage = '无法获取用户信息，请稍后再试。';
      }
    },

    // 修改密码操作
    async changePassword() {
      const { currentPassword, newPassword, confirmPassword, uid } = this.security;

      console.log('User identifier:', uid);
      console.log('Request data:', {
        uid: uid,
        oldPassword: currentPassword,
        newPassword: newPassword
      });

      // 校验基本输入
      if (!currentPassword || !newPassword || !confirmPassword) {
        alert('请填写所有字段');
        return;
      }

      // 校验新密码和确认密码是否一致
      if (newPassword !== confirmPassword) {
        alert('新密码和确认密码不匹配');
        return;
      }

      // 确保 identifier 不为空
      if (!uid) {
        alert('用户ID不能为空');
        return;
      }

      // 向后端发送请求修改密码
      this.isLoading = true;
      const url = 'http://localhost:8080/api/login/change-password';

      // 使用 URLSearchParams 发送表单数据
      const formData = new URLSearchParams();
      formData.append('uid', uid);
      formData.append('oldPassword', currentPassword);
      formData.append('newPassword', newPassword);

      try {
        const response = await axios.post(url, formData, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        });

        console.log('Response:', response.data);

        if (response.data.success) {
          alert('密码修改成功');
        } else {
          alert('密码修改失败: ' + (response.data.message || '请稍后重试'));
        }
      } catch (error) {
        console.error('请求失败', error);
        alert('密码修改失败，请稍后再试');
      } finally {
        this.isLoading = false;
      }
    }
  },

  mounted() {
    this.getOrignalPassword();
  }
};
</script>

<style scoped>
/* 样式保持不变 */
.section {
  max-width: 600px;
  margin: 0 auto;
  padding: 50px 20px;
  background-color: transparent;
  border: none;
  box-shadow: none;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  min-height: 80vh;
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
  max-width: 300px;
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
  max-width: 200px;
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
