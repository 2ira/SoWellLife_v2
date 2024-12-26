<!-- src/views/ArticleDetail.vue -->
<template>
  <div class="article-detail">
    <div class="article-content">
      <!-- 左侧图片 -->
      <img :src="article.picture" alt="Article Image" class="article-image" />
      <!-- 右侧文字内容 -->
      <div class="article-text">
        <h1>{{ article.title }}</h1>
        <p class="article-section">
          <strong>症状类别：</strong>
          {{ article.type }}
        </p>
        <p class="article-section">
          <strong>症状描述：</strong>
          {{ article.symptom }}
        </p>
        <p class="article-section">
          <strong>原因分析：</strong>
          {{ article.causes }}
        </p>
        <p class="article-section">
          <strong>治疗方法：</strong>
          {{ article.treatment }}
        </p>
      </div>
    </div>
    <!-- 相关文章推荐 -->
    <h2 class="related-title">相关推荐</h2>
    <div class="related-articles">
      <RelatedArticleBox
          v-for="related in relatedArticles"
          :key="related.id"
          :rimg="related.rimg"
          :rname="related.rname"
          :rurl="related.rurl"
      />
    </div>
  </div>
</template>

<script>
import RelatedArticleBox from '@/components/RelatedArticleBox.vue';
import axios from "axios";
import { API_BASE_URL } from '@/utils/api';

export default {
  name: "ArticleDetail",
  components: {
    RelatedArticleBox
  },
  data() {
    return {
      article: {},
      relatedArticles: []
    };
  },
  created() {
    const articleId = this.$route.params.id;
    console.log('文章id: ', articleId);
    this.fetchArticle(articleId);
  },
  methods: {
    async fetchArticle(id) {
      try {
        const response = await axios.get(`${API_BASE_URL}/api/introductions/${id}`);
        const articleData = response.data;
        console.log(response.data);
        console.log("===================")

        console.log('原始article.image值:', articleData.picture); // 输出原始的image字段值
        console.log('原始article.image类型:', typeof articleData.picture); // 输出原始的image字段数据类型

        if(articleData.picture){
          articleData.picture = require('@/assets/imgs/'+articleData.picture);
          console.log('处理后article.image值:', articleData.picture); // 输出处理后的image字段值
        }
        this.article = articleData;

        // 确保获取到 article.type 后再获取相关资源
        this.fetchRelatedArticles(this.article.type);

        this.$nextTick(() => {
          this.article = articleData;
        });

      } catch (error) {
        console.error("Error fetching article:", error);
      }
    },
    async fetchRelatedArticles(symptomName) {
      try {
        // 假设后端 API 可以通过标题搜索相关文章
        // const response = await axios.get(`/api/articles?relatedTo=${encodeURIComponent(title)}`);
        // 调用后端 API，根据症状名称查询相关资源
        console.log('需要检索的rtag: ', symptomName);
        const resourcesResponse = await axios.get(`\`${API_BASE_URL}/api/resources/by-tag`, {
          params: { name: symptomName } // 将症状名称作为查询参数传递
        });
        // 获取来自视频资源（resourcevideo）的相关文章
        const videoResponse = await axios.get(`${API_BASE_URL}/api/resourceVideos/byTag`, {
          params: { name: symptomName }
        });

        // 合并两个 API 返回的数据
        const combinedArticles = [
          ...resourcesResponse.data,
          ...videoResponse.data
        ];

        // 打印每个相关文章的图片路径
        this.relatedArticles.forEach((related, index) => {
          console.log(`相关文章 ${index + 1} 的标题:`, related.title);
        });

        // 设置相关推荐
        this.relatedArticles = combinedArticles;
      } catch (error) {
        console.error("Error fetching related articles:", error);
      }
    }
  }
};
</script>

<style scoped>

.article-detail {
  padding: 20px;
  margin-top: 100px;
}

.article-content {
  display: flex;
  gap: 20px; /* 图片和文字之间的间距 */
  align-items: flex-start; /* 图片和文字顶部对齐 */
}

.article-image {
  max-width: 40%; /* 图片宽度占40% */
  height: auto;
  border-radius: 10px; /* 圆角 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 图片阴影效果 */
}

.article-text {
  flex: 1; /* 文字区域占剩余空间 */
  display: flex;
  flex-direction: column;
  gap: 15px; /* 各段文字之间的间距 */
  line-height: 1.8; /* 行高，增加可读性 */
}

.article-text h1 {
  font-size: 1.8rem;
  margin-bottom: 10px;
  color: #333;
}

.article-text .article-section {
  font-size: 1rem;
  color: #555;
  text-align: justify; /* 文字两端对齐 */
}

.related-title {
  margin-top: 100px; /* 控制“相关推荐”标题上方的空白，40px 代表大约两行间距 */
  margin-left: 100px;
}

.related-articles {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 20px;
  margin-left: 100px;
}

.related-articles > * {
  flex: 1 1 calc(25% - 20px); /* 一行三个格子，每个占三分之一的宽度 */
  max-width: 300px; /* 最大宽度 */
  box-sizing: border-box;
}

</style>