<!-- IHome.vue -->
<template>
    <div class="home-page">
<!--      <LoadingSpinner v-if="isLoading"></LoadingSpinner> &lt;!&ndash; 加载时显示加载动画,这里这个控件还没有得到 &ndash;&gt;-->
      <ArticleBox
        v-for="article in articles"
        :key="article.id"
        :title="article.title"
        :description="article.description"
        :articleId="article.id"
        :image="article.image"
      />
      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div> <!-- 显示错误信息 -->
    </div>
  </template>
  
  <script>
  import ArticleBox from "@/components/ArticleBox.vue";
  import axios from "axios";

  export default {
    name: "IHome",
    components: {
      ArticleBox
    },
    data() {
      return {
        articles: [],
        isLoading:true,
        errorMessage:''
      };
    },
    mounted() {
      console.log('即将发送请求，请求URL: ', 'http://localhost:8080/api/introductions/articles');
      try {
        this.loadArticles();
      } catch (e) {
        console.error('加载文章数据时出错：', e);
        this.isLoading = false;
        this.errorMessage = '加载文章数据出错，请稍后重试。';
      }
    },
    methods:{
        loadArticles(){
          axios.get('/api/introductions/articles',{
            headers: {
              'Accept': 'application/json' // 只发送必要的请求头
            }
          })
              .then(response => {
                console.log(response.config.headers); // 打印请求头
                this.articles = response.data;
                console.log("the article is",this.articles)
                this.isLoading = false; // 数据获取成功后，将加载状态设为false
              })
              .catch(error => {
                console.error('获取文章数据出错：', error);
                this.isLoading = false; // 出错时也将加载状态设为false
                this.errorMessage = '无法获取文章数据，请稍后重试。'; // 设置错误信息
              });
        }
        }
  };
  </script>
  
  <style scoped>
  .home-page {
    padding: 50px;
    padding-left: 200px;
    padding-right: 200px;
    margin-top: 90px
  }
  </style>
  