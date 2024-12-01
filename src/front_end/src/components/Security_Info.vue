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
export default {
  name: 'Security_Info',
  data() {
    return {
      security: {
        currentPassword: '',
        newPassword: '',
        confirmPassword: '',
        emailVerification: '',
      },
    };
  },
  methods: {
    // 发送邮箱验证码
    sendVerificationCode() {
      console.log('发送验证码到邮箱...');
      alert('验证码已发送，请查收您的邮箱');
    },

    // 修改密码操作
    changePassword() {
      const { currentPassword, newPassword, confirmPassword, emailVerification } = this.security;

      if (!currentPassword || !newPassword || !confirmPassword || !emailVerification) {
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
