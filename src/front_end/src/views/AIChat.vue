# AIChat.vue
<template>
<!--  <div class="mt-[115px] flex h-[calc(100vh-164px)] bg-gray-100">-->
  <div class="fixed top-[120px] bottom-0 left-0 right-0 flex bg-gray-100">
    <!-- 左侧边栏 -->

    <div class="w-64 bg-white border-r border-gray-200 flex flex-col">
      <!-- 对话列表 -->
      <div class="flex-1 overflow-y-auto">
        <div class="px-4 py-2 text-base font-medium text-gray-1000">最近对话</div>
        <div class="space-y-1 px-2">
          <button
              v-for="chat in chatSessions"
              :key="chat.cid"
              @click="selectChat(chat.cid)"
              class="w-full px-4 py-3 text-left rounded-lg hover:bg-gray-100 text-base"
              :class="{'bg-blue-50 text-blue-600': currentCid === chat.cid}"
          >
            {{ chat.hName || '新对话' }}
          </button>
        </div>
      </div>

      <!-- 新建对话按钮固定在底部 -->
      <div class="p-4 border-t border-gray-200">
        <button
            @click="createNewChat"
            class="w-full flex items-center justify-center py-2 px-4 bg-blue-500 hover:bg-blue-600 text-white rounded-lg transition-colors"
        >
          <span class="mr-2">+</span>
          <span>新对话🤓☝️</span>
        </button>
      </div>
    </div>


    <!-- 右侧聊天区域 -->
    <div class="flex-1 flex flex-col">
      <!-- 聊天消息区域 -->
      <div class="flex-1 overflow-y-auto p-4 pb-2" ref="messageContainer">
        <div class="max-w-3xl mx-auto space-y-4">
          <template v-if="messages.length === 0">
            <div class="flex items-center justify-center h-full"> <!-- 使用 flex 和高度充满来居中 -->
              <p class="text-2xl text-gray-500 font-medium">开始新的对话吧！</p> <!-- 调整字体大小和粗细 -->
            </div>
          </template>

          <template v-else>
            <!-- 消息气泡部分 -->
            <div class="max-w-3xl mx-auto space-y-4 px-12">
              <div v-for="(message, index) in messages"
                   :key="index"
                   class="flex items-start gap-4 mb-6"
                   :class="[message.role === 'user' ? 'mr-4 justify-end' : 'ml-4 justify-start']"
              >
                <!-- 头像部分 -->
                <div class="flex-shrink-0 w-10 h-10 rounded-full overflow-hidden">
                  <img
                      :src="message.role === 'user'
              ? require('@/assets/imgs/icon/default-avatar.jpg')
              : require('@/assets/imgs/icon/ai-avatar.png')"
                      :alt="message.role === 'user' ? '用户头像' : 'AI头像'"
                      class="w-full h-full object-cover"
                  >
                </div>

                <!-- 消息内容部分 - 移到头像的同级 -->
                <div class="flex flex-col max-w-[40%]">
                  <div class="px-4 py-3 rounded-lg"
                       :class="[
               message.role === 'user'
                 ? 'bg-blue-500 text-white rounded-tr-none'
                 : 'bg-white text-gray-800 rounded-tl-none'
             ]"
                  >
                    {{ message.content }}
                  </div>
                </div>
              </div>
            </div>
          </template>
        </div>
      </div>

      <!-- 底部输入区域 -->
      <div class="fixed bottom-0 left-64 right-0 border-t border-gray-200 bg-white p-4">
        <div class="max-w-3xl mx-auto flex gap-4">
          <input
              v-model="inputMessage"
              type="text"
              placeholder="输入消息..."
              class="flex-1 px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-blue-500"
              @keyup.enter="sendMessage"
          >
          <button
              @click="sendMessage"
              class="px-6 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors"
              :disabled="!inputMessage.trim()"
          >
            发送
          </button>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted, nextTick, watch } from 'vue'
import axios from 'axios'

// 状态定义
// const inputMessage = ref('')
// const messages = ref([])
// const chatSessions = ref([])
// const currentCid = ref(null)
// const messageContainer = ref(null)
const inputMessage = ref('')
const messages = ref([
  // 确保每条测试消息都有 role 字段
  {
    content: '你好！',
    role: 'user',
    htime: new Date()
  },
  {
    content: '你好！我是AI助手，很高兴为您服务。',
    role: 'ai',
    htime: new Date()
  }
])

const chatSessions = ref([
  // 测试数据，实际应从API获取
  { cid: 1, hName: '对话 1' },
  { cid: 2, hName: '对话 2' }
])
const currentCid = ref(null)
const messageContainer = ref(null)


// 从本地存储获取用户ID，实际应用中应该从用户认证系统获取
const uid = ref(parseInt(localStorage.getItem('userId')) || 1)

// API 基础URL
const API_BASE_URL = '/api/chathistories'

// 初始化加载
onMounted(async () => {
  await loadChatSessions()
})


async function loadChatSessions() {
  try {
    const response = await axios.get(`${API_BASE_URL}/sessions/${uid.value}`)
    chatSessions.value = response.data
    if (chatSessions.value.length > 0 && !currentCid.value) {
      await selectChat(chatSessions.value[0].cid)
    }
  } catch (error) {
    console.error('Failed to load chat sessions:', error)
    // 使用测试数据
    chatSessions.value = [
      { cid: 1, hName: '测试对话 1' },
      { cid: 2, hName: '测试对话 2' }
    ]
  }
}

// 创建新会话
async function createNewChat() {
  try {
    const response = await axios.post(`${API_BASE_URL}/sessions/new`, {
      uid: uid.value,
      initialMessage: "开始新的对话"
    })
    const newCid = response.data
    await loadChatSessions()
    await selectChat(newCid)
  } catch (error) {
    console.error('Failed to create new chat:', error)
    // 使用测试数据
    const newChat = {
      cid: Date.now(),
      hName: '新对话'
    }
    chatSessions.value.unshift(newChat)
    await selectChat(newChat.cid)
  }
}

// 选择会话
// 在选择会话的函数中也确保数据格式正确
async function selectChat(cid) {
  currentCid.value = cid
  try {
    const response = await axios.get(`${API_BASE_URL}/messages/${cid}`)
    // 确保每条消息都有 role 字段
    messages.value = response.data.map(msg => ({
      ...msg,
      role: msg.role || 'ai' // 如果没有 role 字段，默认设置为 'ai'
    }))
  } catch (error) {
    console.error('Failed to load chat messages:', error)
    // 测试数据也要包含 role
    messages.value = [
      { content: '你好！', role: 'user', htime: new Date() },
      { content: '你好！我是AI助手，很高兴为您服务。', role: 'ai', htime: new Date() }
    ]
  }
  await scrollToBottom()
}

// 发送消息
async function sendMessage() {
  if (!inputMessage.value.trim() || !currentCid.value) return

  const messageContent = inputMessage.value
  inputMessage.value = ''

  // 添加用户消息
  const userMessage = {
    content: messageContent,
    role: 'user',  // 确保设置 role
    htime: new Date()
  }
  messages.value.push(userMessage)

  try {
    const response = await axios.post(`${API_BASE_URL}/send`, {
      cid: currentCid.value,
      uid: uid.value,
      content: messageContent,
      role: 'user'
    })

    // 添加 AI 响应
    if (response.data) {
      messages.value.push({
        ...response.data,
        role: 'ai'  // 确保 AI 响应也有 role
      })
    } else {
      // 测试用的模拟响应
      messages.value.push({
        content: `这是对 "${messageContent}" 的模拟回复`,
        role: 'ai',
        htime: new Date()
      })
    }

    await scrollToBottom()
    await loadChatSessions() // 刷新会话列表
  } catch (error) {
    console.error('Failed to send message:', error)
    // 添加错误提示
    messages.value.push({
      content: '消息发送失败，请重试',
      role: 'system',
      htime: new Date()
    })
  }
}



// 滚动到底部
async function scrollToBottom() {
  await nextTick()
  if (messageContainer.value) {
    messageContainer.value.scrollTop = messageContainer.value.scrollHeight
  }
}

// 监听消息变化，自动滚动到底部
watch(messages, async () => {
  await scrollToBottom()
}, { deep: true })

</script>

<style scoped>
::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background: transparent;
}

::-webkit-scrollbar-thumb {
  background-color: #E5E7EB;
  border-radius: 3px;
}

::-webkit-scrollbar-thumb:hover {
  background-color: #D1D5DB;
}
</style>