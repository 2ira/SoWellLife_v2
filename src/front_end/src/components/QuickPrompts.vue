<template>
  <div class="w-full max-w-3xl mx-auto mb-4 px-26">
    <div class="flex flex-wrap gap-4 justify-center">
      <button
          v-for="(prompt, index) in prompts"
          :key="index"
          @click="emit('prompt-click', prompt.message)"
          class="px-7 py-2 bg-white hover:bg-gray-50 text-gray-700 rounded-full shadow-sm border border-gray-200 transition-colors duration-200 text-sm font-medium flex items-center gap-1"
      >
        {{ prompt.text }}
      </button>
    </div>
  </div>
</template>

<script setup>
import { defineEmits } from 'vue'

const prompts = [
  {
    text: "👋 向我介绍一些心理学知识",
    message: "你好，我想了解一些基础的心理学知识，可以给我介绍一下吗？"
  },
  {
    text: "😔 今天心情不好，陪我聊聊天",
    message: "今天心情有点低落，能陪我聊聊天吗？"
  },
  {
    text: "🤔 帮我分析一下我的情绪",
    message: "最近感觉情绪不太稳定，你能帮我分析一下可能的原因吗？"
  },
  {
    text: "💡 推荐一些改善心情的方法",
    message: "能给我推荐一些日常改善心情的方法吗？"
  },
  {
    text: "🌙 失眠了怎么办",
    message: "我最近总是睡不好，你有什么建议吗？"
  }
]

const emit = defineEmits(['prompt-click'])
</script>