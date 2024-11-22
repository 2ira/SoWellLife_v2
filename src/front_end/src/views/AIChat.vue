# AIChat.vue
<template>
    <div class="flex mt-12 h-[calc(100vh-4rem)] overflow-hidden">
      <!-- 左侧边栏 -->
      <div class="w-64 bg-white border-r border-gray-200 flex flex-col">
      <!-- 沙包头像，后面再搞 -->
        <!-- <div class="p-4 flex items-center space-x-3 border-b border-gray-100">
          <img 
            :src="AIAvatar" 
            alt="AI Avatar" 
            class="w-8 h-8 rounded-full"
          >
          <span class="text-sm">沙包</span>
        </div> -->
      
      <!-- 新建对话按钮 -->
      <div class="p-4">
        <button
          @click="createNewChat"
          class="w-full flex items-center justify-center space-x-2 bg-blue-50 text-blue-600 rounded-lg py-2 text-sm hover:bg-blue-100 transition-colors"
        >
          <span class="text-xs">+</span>
          <span>新对话🤓</span>
        </button>
      </div>

      <!-- 最近对话列表 -->
      <div class="flex-1 overflow-y-auto">
        <div class="px-4 py-2 text-xs text-gray-500">最近对话</div>
        <div class="space-y-1">
          <button
            v-for="chat in chatHistory"
            :key="chat.id"
            :class="[
              'w-full px-4 py-2 text-sm text-left hover:bg-gray-50',
              { 'bg-gray-50': chat.active }
            ]"
            @click="selectChat(chat.id)"
          >
            {{ chat.title }}
          </button>
        </div>
      </div>
    </div>

    <!-- 右侧聊天区域 -->
    <div class="flex-1 flex flex-col bg-white">
      <!-- 聊天消息区域 -->
      <div class="flex-1 overflow-y-auto p-2">
        <div class="max-w-3xl mx-auto space-y-2">
          <!-- AI 初始消息 -->
          <div class="text-gray-900">
            {{ initialQuestion }}
          </div>
          

          <!-- 消息列表 -->
          <div v-for="(message, index) in messages" :key="index" class="space-y-4">
            <div :class="message.isAI ? 'text-gray-900' : 'text-blue-600'">
              {{ message.content }}
            </div>
          </div>
        </div>
      </div>

      <!-- 底部工具栏和输入框 -->
      <div class="border-t border-gray-100 bg-white">

        <!-- 输入框区域 -->
        <div class="p-6">
          <div class="max-w-3xl mx-auto">
            <div class="flex items-center gap-3 bg-gray-50 rounded-lg px-4 py-3">
              <input
                v-model="inputMessage"
                type="text"
                placeholder="try to talk with sandbox"
                class="flex-1 bg-transparent text-sm focus:outline-none"
                @keyup.enter="sendMessage"
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// 状态定义
// const AIAvatar = ref('/avatar-placeholder.png')
const inputMessage = ref('')
const initialQuestion = ref('当然可以，你说具体需要我帮什么忙呢？')
const messages = ref([])

// 聊天历史
const chatHistory = ref([
  { id: 1, title: '请求帮忙', active: true },
  { id: 2, title: '城市文创产品', active: false }
])



// 方法定义
const createNewChat = () => {
  chatHistory.value = chatHistory.value.map(chat => ({
    ...chat,
    active: false
  }))
  
  const newChat = {
    id: chatHistory.value.length + 1,
    title: '新对话',
    active: true
  }
  
  chatHistory.value.unshift(newChat)
  // 添加本地存储
  localStorage.setItem('chatHistory', JSON.stringify(chatHistory.value))
}

const selectChat = (chatId) => {
  chatHistory.value = chatHistory.value.map(chat => ({
    ...chat,
    active: chat.id === chatId
  }))
}


const sendMessage = () => {
  if (inputMessage.value.trim()) {
    messages.value.push({
      content: inputMessage.value,
      isAI: false,
      timestamp: new Date()
    })
    
    // 这里可以添加发送到后端的逻辑
    
    inputMessage.value = ''
  }
}


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