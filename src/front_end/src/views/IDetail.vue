<!-- src/views/ArticleDetail.vue -->
<template>
  <div class="article-detail">
    <h1>{{ article.title }}</h1>
    <img :src="article.image" alt="Article Image" class="article-image" />
    <p>{{ article.content }}</p>

    <!-- 相关文章推荐 -->
    <h2 class="related-title">相关推荐</h2>
    <div class="related-articles">
      <RelatedArticleBox 
        v-for="related in relatedArticles" 
        :key="related.id"
        :id="related.id"
        :image="related.image" 
        :title="related.title"
        @article-click="goToArticle"
      />
    </div>
  </div>
</template>
  
<script>
import RelatedArticleBox from '@/components/RelatedArticleBox.vue';
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
      this.fetchArticle(articleId);
      this.fetchRelatedArticles(this.article.title);  // 通过 Rtag 匹配相关资源
    },
    methods: {
    async fetchArticle(id) {
      try {
        // const response = await axios.get(/api/articles/${id});
        this.article.title="Article "+id;
        this.article.content = "This is the content of Article "+id; // response.data; 假设后端返回的文章对象
        this.article.image = "/resources/"+id;
        if (id==4||id==7||id==10||id==15||id==16||id==20)
          this.article.image+=".png";
        else
          this.article.image+=".jpg";
      } catch (error) {
        console.error("Error fetching article:", error);
      }
    },
    async fetchRelatedArticles(title) {
      try {
        // 假设后端 API 可以通过标题搜索相关文章
        // const response = await axios.get(`/api/articles?relatedTo=${encodeURIComponent(title)}`);
        this.relatedArticles = [
      { id: 1, title: title+": 1", image: this.article.image },
      { id: 2, title: title+": 2", image: this.article.image },
      { id: 3, title: title+": 3", image: this.article.image },
      { id: 4, title: title+": 4", image: this.article.image },
      { id: 5, title: title+": 5", image: this.article.image },
      { id: 6, title: title+": 6", image: this.article.image },
      { id: 7, title: title+": 7", image: this.article.image },
      { id: 8, title: title+": 8", image: this.article.image }
    ] // response.data;  // 返回相关文章数组
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
    text-align: center; /* 内容居中 */
    margin-top: 100px;
  }

  .article-image {
    max-width: 30%; /* 图片自适应宽度 */
    height: auto; /* 保持比例 */
    margin: 20px 0; /* 图片上下间距 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 阴影效果 */
  }

  .related-title {
    margin-top: 100px; /* 控制“相关推荐”标题上方的空白，40px 代表大约两行间距 */
  }

  .related-articles {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }

  .related-articles > * {
    flex: 1 1 calc(25% - 20px); /* 每个方框宽度为 25%，中间有间距 */
    box-sizing: border-box;
  }

</style>