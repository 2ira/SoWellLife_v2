<!-- ResourceCard.vue -->
<template>
  <div class="resource-card">
    <div class="content">
      <h3>{{ title }}</h3>
      <div class="meta">
        <span class="time">{{ time }}</span>
        <span class="author">{{ author }}</span>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "ResourceCard",
  props: {
    title: {
      type: String,
      required: true
    },
    time: {
      type: String,
      required: true
    },
    author: {
      type: String,
      required: true
    }
  }
}
</script>
  
<style scoped>
.resource-card {
  background: white;
  overflow: hidden;
  box-shadow: 2px 2px 8px rgba(0,0,0,0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  height: 100%;
  display: flex;
  flex-direction: column;
  padding:0.2rem 0.2rem;   /* 调整上下左右的内边距 */
}

.resource-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
}


.content h3 {
  margin: 0 0 0.7rem;  /* 增加标题和meta信息之间的距离 */
  font-size: 1.1rem;
  color: #1B4D62;
  line-height: 1.4;
}

.meta {
  display: flex;
  gap: 1rem;
  font-size: 0.875rem;
  color: #666;
}


.time {
  margin: 0;
  color: #666;
  font-size: 0.95rem;
  line-height: 1.5;
}

.author {
  margin: 0;
  color: #888;
  font-size: 0.95rem;
  line-height: 1.5;
}
</style>