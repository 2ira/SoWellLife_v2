<!-- src/components/RelatedArticleBox.vue -->
<template>
    <div class="related-article-box" @click="handleClick">
      <img :src="image" alt="Related Article Image" class="related-article-image" />
      <p class="related-article-title">{{ title }}</p>
    </div>
  </template>
  
  <script>
  export default {
    name: "RelatedArticleBox",
    props: {
      image: String,
      title: String,
      id: Number
    },
    methods: {
      handleClick() {
        this.$emit('article-click', this.id);
      }
    }
  };
  </script>
  
  <style scoped>
  .related-article-box {
    width: 22%;
    background-color: #f9f9f9;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    cursor: pointer;
    text-align: center;
    overflow: hidden;
    width: 100%; /* 保证方框自适应 */
    transition: transform 0.2s;
  }
  .related-article-box:hover {
    transform: scale(1.05);
  }
  .related-article-image {
    width: 100%;
    height: 150px;
    object-fit: cover;
  }
  .related-article-title {
    font-size: 16px;
    color: #333;
    padding: 10px;
    font-weight: bold;
  }
  </style>
  