
<template>
  <div class="background" ></div>
  <div class="collection-container">
    <div class="user-info" style="margin-top: 400px;">
      <img :src="avatarUrl || require('@/assets/imgs/tests/1.png')" alt="用户头像" class="avatar-img">
      <span class="nickname">AAA小唐</span>
    </div>
    <div class="collection-content">
      <!-- 新增的标签容器 -->
      <div class="tab-container">
        <span class="tab" :class="{ active: articleActive }" @click="showArticle = true;showVideo = false;articleActive = true;videoActive = false;">文章</span>
        <span class="tab" :class="{ active: videoActive }" @click="showArticle = false; showVideo = true;articleActive = false;videoActive = true;">视频</span>
      </div>
      <!-- 根据条件显示文章或视频 -->
      <div v-if="showArticle" class="article-resources-container active">
        <!-- 文章展示部分 -->
        <div class="article-resource-card" v-for="article in articleCollection" :key="article.id">
          <a :href="article.link" target="_blank">
            <img :src="article.img" :alt="article.title" class="article-resource-image" />
            <div class="article-resource-info">
              <h3 class="article-resource-category">{{ article.category }}</h3>
              <p class="article-resource-title">{{ article.title }}</p>
            </div>
          </a>
        </div>
      </div>
      <div v-if="showVideo" class="video-resources-container active">
        <!-- 视频展示部分 -->
        <div class="video-resource-card" v-for="video in videoCollection" :key="video.id">
          <a :href="video.link" target="_blank">
            <img :src="video.img" :alt="video.title" class="video-resource-image" />
            <div class="video-resource-info">
              <h3 class="video-resource-category">{{ video.category }}</h3>
              <p class="video-resource-title">{{ video.title }}</p>
            </div>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const showArticle = ref(true);
const showVideo = ref(false);
const articleActive = ref(true);
const videoActive = ref(false);

// 背景图路径
const avatarUrl = ref(null);
const articleCollection = ref([
  {
    id: 1,
    img: require("@/assets/imgs/article/1.jpg"),
    category: "被害妄想症",
    title: "偏执型人格障碍指南",
    link: "https://www.msdmanuals.cn/home/mental-health-disorders/schizophrenia-and-related-disorders/delusional-disorder?query=%E8%A2%AB%E5%AE%B3%E5%A6%84%E6%83%B3%EF%BC%9A"
  },
  {
    id: 2,
    img: require("@/assets/imgs/article/2.png"),
    category: "边缘型人格障碍",
    title: "边缘型人格障碍手册",
    link: "https://www.nami.org/Your-Journey/Individuals-with-Mental-Illness/Borderline-Personality-Disorder"
  },
  {
    id: 3,
    img: require("@/assets/imgs/article/3.jpg"),
    category: "边缘型人格障碍",
    title: "边缘型人格障碍手册",
    link: "https://www.nimh.nih.gov/health/topics/borderline-personality-disorder"
  },
  {
    id: 4,
    img: require("@/assets/imgs/article/4.jpg"),
    category: "边缘型人格障碍",
    title: "边缘型人格障碍手册",
    link: "https://www.nimh.nih.gov/health/topics/borderline-personality-disorder"
  },
]);
const videoCollection = ref([
  {
    id: 1,
    img: require("@/assets/imgs/video/1.png"),
    category: "被害妄想症",
    title: "【科普】被害妄想症",
    link: "https://www.bilibili.com/video/BV1Vi4y1Y76a/?vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 2,
    img: require("@/assets/imgs/video/2.png"),
    category: "边缘型人格障碍",
    title: "什么是边缘型人格障碍|特征和症状",
    link: "https://www.bilibili.com/video/BV1Ars2efEod?spm_id_from=333.788.videopod.sections&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 3,
    img: require("@/assets/imgs/video/3.png"),
    category: "边缘型人格障碍",
    title: "边缘型人格障碍介于毁灭与清醒之间,常有被害妄想的恐惧焦虑【脑科学】",
    link: "https://www.bilibili.com/video/BV1wh4y137Sw/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 4,
    img: require("@/assets/imgs/video/4.png"),
    category: "成瘾",
    title: "了解成瘾的神经机制",
    link: "https://www.bilibili.com/video/BV1Vm4y1x7JF/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
]);


</script>

<style scoped>
.collection-container {
  position: relative;
  width: 100%;
  height: 100vh;
}
.background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
  z-index: -1;
  background-image: url('@/assets/imgs/article/3.jpg');
}
.user-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}
.avatar-img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  transform: translateY(20px);
}
.nickname {
  margin-top: 10px;
  font-size: 18px;
  transform: translateY(20px);
}
.collection-content {
  margin-top: 50px;
  padding: 0 50px;
}
/* 新增的标签容器样式 */
.tab-container {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 20px;
}
.tab {
  cursor: pointer;
  padding: 10px 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: background-color 0.3s;
}
.tab.active {
  background-color: #007bff; /* 这里设置你想要的深色 */
  color: white;
}
/* 文章和视频容器样式调整 */
.article-resources-container,
.video-resources-container {
  display: none;
  padding-top: 0;
}
.article-resources-container.active,
.video-resources-container.active {
  display: grid;
}
.article-resources-container {
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 20px;
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
.video-resources-container {
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 20px;
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
</style>