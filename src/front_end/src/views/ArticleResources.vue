<script>
import axios from 'axios';

export default {
  name: 'ArticleResources',
  data() {
    return {
      resources: [],           // 存储资源数据
      isLoading: true,         // 加载状态
      errorMessage: '',        // 错误信息
    };
  },
  mounted() {
    this.loadResources();     // 组件挂载后加载资源
  },
  methods: {
    // 方法：加载资源数据
    loadResources() {
      axios
          .get('http://localhost:8080/api/resources/all')  // 请求资源数据
          .then((response) => {
            console.log('API 返回的数据:', response.data);  // 打印返回的数据
            this.resources = response.data;    // 更新响应式数据
            // 打印出所有资源的图片路径
            this.resources.forEach(resource => {
              console.log('图片路径:', resource.rimg);  // 打印每个资源的图片路径
            });
            this.isLoading = false;            // 数据加载完成
          })
          .catch((error) => {
            console.error('获取资源数据出错：', error);
            this.errorMessage = '无法加载资源数据，请稍后重试。'; // 设置错误信息
            this.isLoading = false;  // 错误时也需要更新加载状态
          });
    },

    // 切换收藏状态
    toggleFavoriteResource(resource) {
      resource.isFavorited = !resource.isFavorited;
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

    <!-- 资源列表 -->
    <div class="article-resources-container">
      <div
          class="article-resource-card"
          v-for="resource in resources"
          :key="resource.rid"
      >
        <a :href="resource.rurl" target="_blank">
          <img
              :src="require(`@/${resource.rimg}`)"
              :alt="resource.rname"
              class="article-resource-image"
          />
          <div class="article-resource-info">
            <h3 class="article-resource-category">{{ resource.rtag }}</h3>
            <p class="article-resource-title">{{ resource.rname }}</p>
          </div>
        </a>

        <!-- 收藏按钮 -->
        <img
            @click.prevent="toggleFavoriteResource(resource)"
            :src="resource.isFavorited
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
.article-resources-container {
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

.article-resource-card {
  position: relative;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.2s ease;
}

.article-resource-card:hover {
  transform: translateY(-5px);
}

.article-resource-image {
  width: 100%;
  height: 210px;
  object-fit: cover;
}

.article-resource-info {
  padding: 15px;
}

.article-resource-category {
  font-size: 16px;
  color: #449d44;
  font-weight: bold;
}

.article-resource-title {
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
