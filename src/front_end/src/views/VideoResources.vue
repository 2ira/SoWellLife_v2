<script>
import axios from 'axios';

export default {
  name: 'VideoResources',
  data() {
    return {
      videos: [],              // 存储视频数据
      isLoading: true,         // 加载状态
      errorMessage: '',        // 错误信息
    };
  },
  mounted() {
    this.loadVideos();       // 组件挂载后加载视频
  },
  methods: {
    // 方法：加载视频数据
    loadVideos() {
      axios
          .get('http://localhost:8080/api/resourceVideos/all')  // 请求视频数据
          .then((response) => {
            console.log('API 返回的视频数据:', response.data);  // 打印返回的视频数据
            this.videos = response.data;    // 更新响应式数据
            this.isLoading = false;            // 数据加载完成
          })
          .catch((error) => {
            console.error('获取视频资源数据出错：', error);
            this.errorMessage = '无法加载视频资源，请稍后重试。'; // 设置错误信息
            this.isLoading = false;  // 错误时也需要更新加载状态
          });
    },

    // 切换收藏状态
    toggleFavorite(video) {
      video.isFavorited = !video.isFavorited;
    }
  }
};
</script>

<template>
  <div>
    <!-- 加载中显示 -->
    <div v-if="isLoading" class="loading-message">加载中...</div>

    <!-- 错误信息显示 -->
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>

    <!-- 视频资源列表 -->
    <div class="video-resources-container">
      <div
          class="video-resource-card"
          v-for="video in videos"
          :key="video.Rid"
      >
        <a :href="video.rurl" target="_blank">
          <img
              :src="require(`@/${video.rimg}`)"
              :alt="video.Rname"
              class="video-resource-image"
          />
          <div class="video-resource-info">
            <h3 class="video-resource-category">{{ video.rtag }}</h3>
            <p class="video-resource-title">{{ video.rname }}</p>
          </div>
        </a>

        <!-- 收藏按钮 -->
        <img
            @click.prevent="toggleFavorite(video)"
            :src="video.isFavorited
            ? require('@/assets/imgs/收藏2.png')
            : require('@/assets/imgs/收藏1.png')"
            alt="收藏"
            class="favorite-icon"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.video-resources-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 20px;
  padding-top: 120px;
  padding-bottom: 100px;
  max-width: 1200px;
  margin: 20px auto;
  padding-left: 40px;
  padding-right: 40px;
}

.video-resource-card {
  position: relative;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.2s ease;
}

.video-resource-card:hover {
  transform: translateY(-5px);
}

.video-resource-image {
  width: 100%;
  height: 210px;
  object-fit: cover;
}

.video-resource-info {
  padding: 15px;
}

.video-resource-category {
  font-size: 16px;
  color: #449d44;
  font-weight: bold;
}

.video-resource-title {
  font-size: 18px;
  color: #555;
  line-height: 1.5;
}

.favorite-icon {
  width: 30px;
  height: 30px;
  cursor: pointer;
  position: absolute;
  bottom: 10px;
  right: 10px;
}
</style>
