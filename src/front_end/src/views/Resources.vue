<script setup>
// 引入 Vue 的 `ref` 以便处理动态数据
import { ref } from 'vue';

// 创建资源数组，每个资源包含图片路径、症状名、文章标题和目标链接
const resources = ref([
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
  },{
    id: 5,
    img: require("@/assets/imgs/video/5.png"),
    category: "成瘾",
    title: "【干货】成瘾始于痛苦，戒瘾终于平衡！深度解读多巴胺机制，用身体“内稳态”禁欲、戒瘾，保持自律",
    link: "https://www.bilibili.com/video/BV1Vm4y1x7JF/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 6,
    img: require("@/assets/imgs/video/6.png"),
    category: "创伤后应激障碍",
    title: "【TED科普】这12个迹象表明，你可能患上了复杂型创伤后应激障碍",
    link: "https://www.bilibili.com/video/BV1ff421B7VU/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 7,
    img: require("@/assets/imgs/video/7.png"),
    category: "多动症",
    title: "创伤制造ADHD注意力障碍?多巴胺封锁会加重多动症抑郁焦虑反应",
    link: "https://www.bilibili.com/video/BV1pK421e7AC/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 8,
    img: require("@/assets/imgs/video/8.png"),
    category: "反社会型人格障碍",
    title: "又妖又神？反社会人格障碍到底什么样？",
    link: "https://www.bilibili.com/video/BV1iN411h7bf/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 9,
    img: require("@/assets/imgs/video/9.png"),
    category: "分裂样人格障碍",
    title: "【精神分析】分裂样人格科普/人格特质/防御机制/形成原因/相处方式",
    link: "https://www.bilibili.com/video/BV1ab4y1H7JE/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 10,
    img: require("@/assets/imgs/video/10.png"),
    category: "分裂型人格障碍",
    title: "详解分裂型人格的11个特征【给力心理】",
    link: "https://www.bilibili.com/video/BV1vP41147EK/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 11,
    img: require("@/assets/imgs/video/11.png"),
    category: "回避型人格障碍",
    title: "回避型人格如何疗愈自己？这里有5条建议【武志红】",
    link: "https://www.bilibili.com/video/BV1BM4y1M796/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 12,
    img: require("@/assets/imgs/video/12.png"),
    category: "焦虑症",
    title: "你不知道的7种焦虑症",
    link: "https://www.bilibili.com/video/BV1cw411Q7C2?spm_id_from=333.788.videopod.sections&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 13,
    img: require("@/assets/imgs/video/13.png"),
    category: "进食障碍",
    title: "【心理学】6种进食障碍：暴饮暴食，神经性厌食症？",
    link: "https://www.bilibili.com/video/BV1hb41147kG/?spm_id_from=333.337.search-card.all.click"
  },
  {
    id: 14,
    img: require("@/assets/imgs/video/14.png"),
    category: "精神分裂",
    title: "【TED科普】什么是精神分裂症（中英字幕）",
    link: "https://www.bilibili.com/video/BV1XM4y1q7jt/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 15,
    img: require("@/assets/imgs/video/15.png"),
    category: "恐慌症",
    title: "惊恐发作（也称恐慌症）症状、病因和疗法",
    link: "https://www.bilibili.com/video/BV1FC4y1p7rS/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 16,
    img: require("@/assets/imgs/video/16.png"),
    category: "强迫症",
    title: "严肃地科普一下强迫症",
    link: "https://www.bilibili.com/video/BV1LY41177XP/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 17,
    img: require("@/assets/imgs/video/17.png"),
    category: "双向情感障碍",
    title: "【正经医生】精神科医生科普，什么是双相情感障碍！",
    link: "https://www.bilibili.com/video/BV1K54y1h7AC/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 18,
    img: require("@/assets/imgs/video/18.png"),
    category: "睡眠相位后移综合征",
    title: "睡眠相位后移综合征：小伙伴们，你最近睡得还好吗？",
    link: "https://www.bilibili.com/video/BV1DV411Y7DQ/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 19,
    img: require("@/assets/imgs/video/19.png"),
    category: "偷窃癖",
    title: "什么是偷窃癖",
    link: "https://www.bilibili.com/video/BV1aw411S7fF/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 20,
    img: require("@/assets/imgs/video/20.png"),
    category: "抑郁症",
    title: "抑郁症，是怎么改变你大脑的？",
    link: "https://www.bilibili.com/video/BV1da411s7BM/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 21,
    img: require("@/assets/imgs/video/21.png"),
    category: "自闭症",
    title: "自闭症科普",
    link: "https://www.bilibili.com/video/BV1eqyqYEEkT/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 22,
    img: require("@/assets/imgs/video/22.png"),
    category: "自闭症",
    title: "自闭症眼中的世界到底是怎样的？",
    link: "https://www.bilibili.com/video/BV16A41157PT/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 23,
    img: require("@/assets/imgs/video/23.png"),
    category: "自恋型人格障碍",
    title: "9个迹象表明，Ta或有自恋型人格障碍",
    link: "https://www.bilibili.com/video/BV1C5411Q7w3/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
  {
    id: 24,
    img: require("@/assets/imgs/video/24.png"),
    category: "自恋型人格障碍",
    title: "如何对付NPD型人格？自恋人格障碍者是你能拯救的吗？",
    link: "https://www.bilibili.com/video/BV1P14y117Ry/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73"
  },
]);

</script>

<template>
  <div class="resources-container">
    <div class="resource-card" v-for="resource in resources" :key="resource.id">
      <a :href="resource.link" target="_blank">
        <img :src="resource.img" :alt="resource.title" class="resource-image" />
        <div class="resource-info">
          <h3 class="resource-category">{{ resource.category }}</h3>
          <p class="resource-title">{{ resource.title }}</p>
        </div>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: "ResourceVideoPage"
};
</script>

<style scoped>
.resources-container {
  display: grid;
  grid-template-columns: repeat(4, minmax(300px, 1fr));
  gap: 20px;
  padding-top: 120px;
  margin: 0 200px;
  padding-bottom: 100px;
  margin-top: 50px;
}

.resource-card {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.resource-card:hover {
  transform: translateY(-5px);
}

.resource-image {
  width: 100%;
  height: auto;
  object-fit: cover;
}

.resource-info {
  padding: 15px;

}

.resource-category {
  font-size: 16px;
  color: #449d44;
  font-weight: bold;

}

.resource-title {
  font-size: 18px;
  color: #555;

  line-height: 1.5;
}
</style>