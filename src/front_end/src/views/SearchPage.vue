<template>
  <div>
    <!-- 搜索结果容器 -->
    <div id="results-container">
      <p v-if="resources.length === 0 && !isLoading">未找到相关资源。</p>
      <p v-if="isLoading">正在搜索，请稍后...</p>

      <div v-for="(resource, index) in resources" :key="index" class="resource-item">
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

<script>
import axios from "axios";
import { API_BASE_URL } from '@/utils/api';

export default {
  name: "SearchPage",
  props: {
    query: {
      type: String,
      default: "", // 如果没有传递 query，设置默认值为空字符串
    },
  },
  data() {
    return {
      localQuery: "", // 本地存储的搜索关键字
      resources: [], // 搜索结果
      isLoading: false, // 搜索加载状态
    };
  },
  watch: {
    // 监听 props 的 query 参数变化
    query: {
      immediate: true, // 初次加载时触发
      handler(newQuery) {
        this.localQuery = newQuery; // 更新本地的搜索关键字
        this.fetchResources(); // 根据新的搜索关键字发起搜索
      },
    },
    // 监听路由参数变化
    "$route.query.q": {
      immediate: true,
      handler(newQuery) {
        this.localQuery = newQuery || ""; // 更新本地的搜索关键字
        this.fetchResources(); // 根据新的搜索关键字发起搜索
      },
    },
  },
  methods: {
    // 搜索资源的方法
    fetchResources() {
      const searchQuery = this.localQuery ? this.localQuery.trim() : ""; // 确保 query 存在

      if (!searchQuery) {
        console.warn("搜索关键字为空，未发起请求！");
        this.resources = []; // 清空旧的搜索结果
        return;
      }

      // 发起请求前清空旧的搜索结果并设置加载状态
      this.resources = [];
      this.isLoading = true;

      // 定义请求的URL
      const byNameUrl = `${API_BASE_URL}//api/resources/by-name?rname=${encodeURIComponent(searchQuery)}`;
      const byTagUrl = `${API_BASE_URL}//api/resources/by-tag?name=${encodeURIComponent(searchQuery)}`;
      const byVideoNameUrl = `${API_BASE_URL}//api/resourceVideos/byName?rname=${encodeURIComponent(searchQuery)}`;
      const byVideoTagUrl = `${API_BASE_URL}//api/resourceVideos/byTag?name=${encodeURIComponent(searchQuery)}`;

      // 先发起四个请求
      Promise.all([fetch(byNameUrl), fetch(byTagUrl), fetch(byVideoNameUrl), fetch(byVideoTagUrl)])
          .then(([nameResponse, tagResponse, videoNameResponse, videoTagResponse]) => {
            if (!nameResponse.ok || !tagResponse.ok || !videoNameResponse.ok || !videoTagResponse.ok) {
              throw new Error("网络请求失败");
            }
            return Promise.all([nameResponse.json(), tagResponse.json(), videoNameResponse.json(), videoTagResponse.json()]);
          })
          .then(([nameData, tagData, videoNameData, videoTagData]) => {
            // 合并文章资源和视频资源
            let allResources = [...nameData, ...tagData, ...videoNameData, ...videoTagData];

            // 使用 Set 去重（假设资源对象有唯一的 rurl 字段，依据此字段去重）
            allResources = this.removeDuplicates(allResources);

            // 更新资源列表
            this.resources = allResources;
          })
          .catch((error) => {
            console.error("搜索资源时发生错误:", error);
            alert("搜索资源失败，请稍后重试！");
          })
          .finally(() => {
            // 搜索完成后取消加载状态
            this.isLoading = false;
          });
    },

    // 去重函数，根据资源的唯一标识去重（假设资源有 rurl 或其他唯一标识）
    removeDuplicates(resources) {
      const seen = new Set();
      return resources.filter((resource) => {
        // 假设每个资源都有 rurl 或其他唯一字段
        const isDuplicate = seen.has(resource.rurl);
        seen.add(resource.rurl);
        return !isDuplicate;
      });
    },

    toggleFavoriteResource(resource) {
      if (!this.userUid) {
        alert("当前用户没有登陆，请登陆后再进行操作");
        return;
      }

      const rid = resource.rid;
      if (!resource.isFavorited) {
        const params = new URLSearchParams();
        params.append("Rid", rid);
        params.append("flag", 0);
        params.append("Uid", this.userUid);

        axios
            .post(`${API_BASE_URL}/api/favorites/add`, params)
            .then((response) => {
              console.log("收藏状态已更改为 收藏");
              if (response.data && response.data.success) {
                resource.isFavorited = true;
              } else {
                console.error("添加收藏失败:", response.data.message);
                alert("添加收藏失败，请稍后再试");
              }
            })
            .catch((error) => {
              console.error("添加收藏失败:", error);
              alert("添加收藏失败，请稍后再试");
            });
      } else {
        const params = new URLSearchParams();
        params.append("Rid", rid);
        params.append("flag", 0);
        params.append("Uid", this.userUid);

        axios
            .post(`${API_BASE_URL}/api/favorites/remove`, params)
            .then((response) => {
              console.log("收藏状态已更改为 未收藏");
              if (response.data && response.data.success) {
                resource.isFavorited = false;
              } else {
                console.error("取消收藏失败:", response.data.message);
                alert("取消收藏失败，请稍后再试");
              }
            })
            .catch((error) => {
              console.error("取消收藏失败:", error);
              alert("取消收藏失败，请稍后再试");
            });
      }
    },
  },
};
</script>

<style scoped>
#results-container {
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

.resource-item {
  position: relative;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.2s ease;
}

.article-resource-image {
  width: 100%;
  height: 210px;
  object-fit: cover;
}

.resource-item:hover {
  transform: translateY(-5px);
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
