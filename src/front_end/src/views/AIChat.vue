# AIChat.vue
<template>
  <div class="fixed top-[120px] bottom-0 left-0 right-0 flex bg-gray-100">
    <!-- 左侧边栏 -->

    <div class="w-64 bg-white border-r border-gray-200 flex flex-col">

      <!-- AI头像和名称 -->
      <div class="p-4 flex items-center">
        <div class="w-12 h-12 rounded-full overflow-hidden">
          <img
              :src="require('@/assets/imgs/icon/ai-avatar.svg')"
              alt="AI头像"
              class="w-full h-full object-cover"
          >
        </div>
        <span class="ml-3 text-lg font-bold text-gray-900">沙包</span>
      </div>

      <!-- 分割线 -->
      <div class="h-px bg-gray-200 mx-4"></div>


      <!-- 对话列表区域 -->
      <div class="flex-1 overflow-y-auto">
        <!-- 最近对话标题和折叠按钮 -->
        <div
            class="px-7 py-2 flex items-center justify-between cursor-pointer hover:bg-gray-50"
            @click="toggleChatList"
        >

          <div class="flex items-center">
  <span class="mr-4">
    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path
          d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 13.8214 2.48697 15.5291 3.33782 17L2.5 21.5L7 20.6622C8.47087 21.513 10.1786 22 12 22Z"
          stroke="currentColor"
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
      />
    </svg>
  </span>
            <span class="text-base font-medium text-gray-900">最近对话</span>
          </div>

          <svg
              class="w-4 h-4 text-gray-500 transform transition-transform duration-200"
              :class="{'rotate-90': !isChatListCollapsed}"
              viewBox="0 0 20 20"
              fill="currentColor"
          >
            <path fill-rule="evenodd" d="M7.293 4.293a1 1 0 011.414 0L14.414 10l-5.707 5.707a1 1 0 01-1.414-1.414L11.586 10 7.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd" />
          </svg>
        </div>

        <!-- 对话列表 -->
        <div v-show="!isChatListCollapsed" class="space-y-1 px-2">
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
      <div class="flex-1 overflow-y-auto p-4 pb-24 relative" ref="messageContainer">
        <div class="max-w-3xl mx-auto space-y-4">
<!--          <template v-if="messages.length === 0">-->
<!--            <div class="absolute inset-0 flex items-center justify-center"> &lt;!&ndash; 使用 flex 和高度充满来居中 &ndash;&gt;-->
<!--              <p class="text-2xl text-gray-500 font-medium">开始新的对话吧！</p> &lt;!&ndash; 调整字体大小和粗细 &ndash;&gt;-->
<!--            </div>-->
<!--          </template>-->
          <template v-if="showWelcome">
            <!-- 使用 React 组件作为欢迎界面 -->
            <WelcomeAnimation />
          </template>

          <template v-else>
            <div class="max-w-6xl ml-1 mr-auto space-y-4">
              <div v-for="(message, index) in messages"
                   :key="index"
                   class="flex items-start gap-2 mb-5"
                   :class="[message.role === 'user' ? 'flex-row-reverse pr-0' : 'pl-0']"
              >
                <!-- 头像部分 -->
                <div class="flex-shrink-0 w-10 h-10 rounded-full overflow-hidden">
                  <img
                      :src="message.role === 'user'
          ? require('@/assets/imgs/icon/default-avatar.jpg')
          : require('@/assets/imgs/icon/ai-avatar.svg')"
                      :alt="message.role === 'user' ? '用户头像' : 'AI头像'"
                      class="w-full h-full object-cover"
                  >
                </div>

                <!-- 消息内容部分 -->
                <div class="flex flex-col max-w-[65%]">
                  <div class="px-3 py-3 rounded-lg"
                       :class="[
           message.role === 'user'
             ? 'bg-blue-500 text-white rounded-tr-none ml-auto'
             : 'bg-white text-gray-800 rounded-tl-none mr-auto'
         ]"
                  >
                    {{ message.content }}
                  </div>
                </div>
              </div>

              <!-- 加载动画 -->
              <div v-if="isLoading" class="flex items-start gap-2 mb-5">
                <div class="flex-shrink-0 w-10 h-10 rounded-full overflow-hidden">
                  <img
                      :src="require('@/assets/imgs/icon/ai-avatar.svg')"
                      alt="AI头像"
                      class="w-full h-full object-cover"
                  >
                </div>
                <div class="flex flex-col max-w-[65%]">
                  <div class="px-6 py-4 rounded-lg bg-white text-gray-800 rounded-tl-none mr-auto">
                    <div class="flex space-x-2">
                      <div class="w-2 h-2 bg-gray-500 rounded-full animate-bounce"></div>
                      <div class="w-2 h-2 bg-gray-500 rounded-full animate-bounce" style="animation-delay: 0.2s"></div>
                      <div class="w-2 h-2 bg-gray-500 rounded-full animate-bounce" style="animation-delay: 0.4s"></div>
                    </div>
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
import WelcomeAnimation from '@/components/WelcomeAnimation.vue' // 导入欢迎动画组件

// 状态定义

const showWelcome = ref(true)  // 新增：控制是否显示欢迎界面

const isLoading = ref(false)  // 新增加载状态
const inputMessage = ref('')

const messages = ref([])
const chatSessions = ref([])
const currentCid = ref(null)
const messageContainer = ref(null)
// 添加折叠状态控制
const isChatListCollapsed = ref(false)

// 从本地存储获取用户ID，实际应用中应该从用户认证系统获取
const uid = ref(parseInt(localStorage.getItem('userId')) || 1)

// API 基础URL
const API_BASE_URL = '/api/chathistories'

// 初始化加载
onMounted(async () => {
  await loadChatSessions()
})

// 切换折叠状态的函数
const toggleChatList = () => {
  isChatListCollapsed.value = !isChatListCollapsed.value
}

// 在 <script setup> 的顶部添加
axios.defaults.baseURL = 'http://localhost:8080'; // 替换为你的后端服务地址
axios.defaults.timeout = 10000; // 10秒超时
axios.interceptors.request.use(config => {
  console.log('发送请求:', config);
  return config;
});

axios.interceptors.response.use(
    response => {
      console.log('收到响应:', response);
      return response;
    },
    error => {
      console.error('请求错误:', {
        url: error.config?.url,
        status: error.response?.status,
        data: error.response?.data,
        message: error.message
      });
      return Promise.reject(error);
    }
);

async function loadChatSessions() {
  try {
    const response = await axios.get(`${API_BASE_URL}/sessions/${uid.value}`)
    chatSessions.value = response.data
    if (chatSessions.value.length > 0 && !currentCid.value) {
      await selectChat(chatSessions.value[0].cid)
    }
  } catch (error) {
    console.error('Failed to load chat sessions:', error)
  }
}

// 创建新会话
async function createNewChat() {
  try {
    const response = await axios.post(`${API_BASE_URL}/sessions/new`, {
      uid: uid.value,
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
  showWelcome.value = false;
  currentCid.value = cid;
  try {
    const response = await axios.get(`${API_BASE_URL}/messages/${cid}`);
    // 过滤掉系统指令消息
    messages.value = response.data
        .filter(msg => msg.role !== 'system')
        .map(msg => ({
          ...msg,
          role: msg.role || 'ai'
        }));
  } catch (error) {
    console.error('Failed to load chat messages:', error);
    messages.value = [
      { content: '你好！我是AI心理助手"沙包"，很高兴和你一起聊天😊', role: 'ai', htime: new Date() }
    ];
  }
  await scrollToBottom();
}

// 发送消息
async function sendMessage() {
  if (!inputMessage.value.trim() || !currentCid.value || isLoading.value) return;

  const messageContent = inputMessage.value;
  inputMessage.value = '';
  isLoading.value = true;

  // 先在界面上显示用户消息，但不保存到数据库
  const userMessage = {
    content: messageContent,
    role: 'user',
    htime: new Date(),
    tempId: Date.now() // 添加临时ID用于标识
  };
  messages.value.push(userMessage);

  // 重试相关变量
  const maxRetries = 5;
  let currentTry = 0;
  let success = false;

  while (currentTry < maxRetries && !success) {
    try {
      // 如果没有当前会话ID，先创建新会话
      if (!currentCid.value) {
        const response = await axios.post(`${API_BASE_URL}/sessions/new`, {
          uid: uid.value,
          initialMessage: messageContent
        });
        currentCid.value = response.data;
      }
      showWelcome.value = false; // 发送消息时隐藏欢迎界面
      const response = await axios.post(`${API_BASE_URL}/send`, {
        cid: parseInt(currentCid.value),
        uid: parseInt(uid.value),
        content: messageContent,
        shouldSave: currentTry === 0 // 只在第一次尝试时设置为保存
      });

      if (response.data && response.data.content) {
        // AI回复成功，删除临时消息标记
        userMessage.tempId = null;

        messages.value.push({
          content: response.data.content,
          role: 'ai',
          htime: new Date()
        });
        success = true;
      } else {
        throw new Error('无效的响应数据');
      }
    } catch (error) {
      console.error(`第 ${currentTry + 1} 次尝试失败:`, error);
      currentTry++;

      if (currentTry === maxRetries) {
        // 所有重试都失败，添加错误消息，保留用户消息但不存数据库
        messages.value.push({
          content: "沙包出了点小问题，后面再试试吧~😀",
          role: 'ai',
          htime: new Date(),
          isError: true // 标记为错误消息
        });
      } else {
        await new Promise(resolve => setTimeout(resolve, 1000 * currentTry));
      }
    }
  }

  isLoading.value = false;
  await scrollToBottom();
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

/* 加载动画样式 */
@keyframes bounce {
  0%, 80%, 100% { transform: scale(0.0) }
  40% { transform: scale(1.0) }
}

.animate-bounce {
  animation: bounce 1.4s infinite ease-in-out both;
}

/* 添加过渡效果 */
.rotate-90 {
  transform: rotate(90deg);
}
</style>