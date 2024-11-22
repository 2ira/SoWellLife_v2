<script setup>
import { ref } from 'vue';

const articles = ref([
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
    category: "成瘾",
    title: "IMPORTANT FACTS ABOUT ALCOHOL AND DRUGS",
    link: "https://www.ncbi.nlm.nih.gov/books/NBK424847/"
  },
  {
    id: 5,
    img: require("@/assets/imgs/article/5.jpg"),
    category: "成瘾",
    title: "THE NEUROBIOLOGY OF SUBSTANCE USE, MISUSE, AND ADDICTION",
    link: "https://www.ncbi.nlm.nih.gov/books/NBK424849/"
  },
  {
    id: 6,
    img: require("@/assets/imgs/article/6.jpg"),
    category: "创伤后应激障碍",
    title: "PTSD症状与治疗",
    link: "https://www.apa.org/topics/ptsd"
  },
  {
    id: 7,
    img: require("@/assets/imgs/article/7.png"),
    category: "多动症",
    title: "多动症治疗指南",
    link: "https://www.cdc.gov/adhd/about/?CDC_AAref_Val=https://www.cdc.gov/ncbddd/adhd/facts.html"
  },
  {
    id: 8,
    img: require("@/assets/imgs/article/8.png"),
    category: "反社会型人格障碍",
    title: "反社会人格障碍研究",
    link: "https://www.ncbi.nlm.nih.gov/books/NBK207194/"
  },
  {
    id: 9,
    img: require("@/assets/imgs/article/9.jpg"),
    category: "反社会型人格障碍",
    title: "Antisocial Personality Disorder: Often Overlooked and Untreated",
    link: "https://www.psychiatry.org/News-room/APA-Blogs/Antisocial-Personality-Disorder-Often-Overlooked"
  },
  {
    id: 10,
    img: require("@/assets/imgs/article/10.jpg"),
    category: "分裂样人格障碍",
    title: "分裂样人格障碍解析",
    link: "https://zhuanlan.zhihu.com/p/670363612"
  },
  {
    id: 11,
    img: require("@/assets/imgs/article/11.jpg"),
    category: "分裂样人格障碍",
    title: "分裂样人格障碍解析",
    link: "https://en.wikipedia.org/wiki/Schizoid_personality_disorder"
  },
  {
    id: 12,
    img: require("@/assets/imgs/article/12.png"),
    category: "分裂型人格障碍",
    title: "分裂型人格障碍症状",
    link: "https://www.mayoclinic.org/diseases-conditions/schizotypal-personality-disorder/symptoms-causes/syc-20353919"
  },
  {
    id: 13,
    img: require("@/assets/imgs/article/13.jpg"),
    category: "回避型人格障碍",
    title: "一直在逃跑的人生：回避型人格障碍万字科普",
    link: "https://zhuanlan.zhihu.com/p/666931809"
  },
  {
    id: 14,
    img: require("@/assets/imgs/article/14.jpg"),//链接有问题
    category: "焦虑",
    title: "成瘾疾病研究",
    link: "https://www.msdmanuals.cn/home/mental-health-disorders/personality-disorders/avoidant-personality-disorder#%E7%97%87%E7%8A%B6_v36026687_zh"
  },
  {
    id: 15,
    img: require("@/assets/imgs/article/15.png"),
    category: "焦虑",
    title: "焦虑症治疗手册",
    link: "https://www.ncbi.nlm.nih.gov/pmc/articles/PMC7071237/"
  },
  {
    id: 16,
    img: require("@/assets/imgs/article/16.jpg"),
    category: "进食障碍",
    title: "进食障碍研究",
    link: "https://www.nimh.nih.gov/health/topics/eating-disorders"
  },
  {
    id: 17,
    img: require("@/assets/imgs/article/17.jpg"),
    category: "精神分裂症",
    title: "Transforming the understanding and treatment of mental illnesses.",
    link: "https://www.nimh.nih.gov/health/statistics/schizophrenia"
  },
  {
    id: 18,
    img: require("@/assets/imgs/article/18.jpg"),
    category: "恐慌症",
    title: "恐慌症症状与治疗",
    link: "https://www.nimh.nih.gov/health/statistics/panic-disorder"
  },
  {
    id: 19,
    img: require("@/assets/imgs/article/19.jpg"),
    category: "强迫症",
    title: "强迫症治疗指南",
    link: "https://www.msdmanuals.cn/home/mental-health-disorders/obsessive-compulsive-and-related-disorders/obsessive-compulsive-disorder-ocd"
  },
  {
    id: 20,
    img: require("@/assets/imgs/article/20.jpg"),
    category: "双相情感障碍",
    title: "双相情感障碍手册",
    link: "https://www.nimh.nih.gov/health/statistics/bipolar-disorder"
  },
  {
    id: 21,
    img: require("@/assets/imgs/article/21.jpg"),
    category: "睡眠相位后移综合征",
    title: "睡眠相位后移综合征",
    link: "https://www.nimh.nih.gov/health/statistics/bipolar-disorder"
  },
  {
    id: 22,
    img: require("@/assets/imgs/article/22.jpg"),
    category: "偷窃癖",
    title: "偷窃癖症状与治疗",
    link: "https://www.mayoclinic.org/zh-hans/diseases-conditions/kleptomania/symptoms-causes/syc-20364732"
  },
  {
    id: 23,
    img: require("@/assets/imgs/article/23.jpg"),
    category: "抑郁症",
    title: "抑郁症症状与治疗",
    link: "https://www.who.int/zh/news-room/fact-sheets/detail/depression"
  },
  {
    id: 24,
    img: require("@/assets/imgs/article/24.jpg"),
    category: "自闭症",
    title: "自闭症谱系障碍解析",
    link: "https://www.msdmanuals.cn/home/children-s-health-issues/learning-and-developmental-disorders/autism-spectrum-disorder"
  },
  {
    id: 25,
    img: require("@/assets/imgs/article/25.jpg"),
    category: "自恋型人格障碍",
    title: "自恋型人格障碍研究",
    link: "https://www.psychologytoday.com/us/conditions/narcissistic-personality-disorder"
  },

]);


function toggleFavoriteArticle(article) {
  article.isFavorited =!article.isFavorited;
}

</script>

<template>
  <div> <!-- 添加根元素 -->
    <div class="article-resources-container">
      <div class="article-resource-card" v-for="article in articles" :key="article.id">
        <a :href="article.link" target="_blank">
          <img :src="article.img" :alt="article.title" class="article-resource-image" />
          <div class="article-resource-info">
            <h3 class="article-resource-category">{{ article.category }}</h3>
            <p class="article-resource-title">{{ article.title }}</p>
          </div>
        </a>
        <img
            @click.prevent="toggleFavoriteArticle(article)"
            :src="article.isFavorited? require('@/assets/imgs/收藏2.png') : require('@/assets/imgs/收藏1.png')"
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