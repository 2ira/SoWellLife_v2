<!-- IHome.vue -->
<template>
    <div class="home-page">
      <ArticleBox
        v-for="article in articles"
        :key="article.id"
        :title="article.title"
        :description="article.description"
        :articleId="article.id"
        :image="article.image"
      />
    </div>
  </template>
  
  <script>
  import ArticleBox from "@/components/ArticleBox.vue";
  
  export default {
    name: "IHome",
    components: {
      ArticleBox
    },
    data() {
      return {
        articles: [
          { id: 1, title: "被害妄想症 Paranoid Disorder", description: "被害妄想症是一种精神健康障碍，患者往往处于恐惧状态，感觉被人议论诬陷、遭人暗算、财产被劫等。",image: "/resources/1.jpg"  },
          { id: 2, title: "边缘型人格障碍 BPD", description: "边缘性人格障碍是一种常见的人格障碍，患者表现出不稳定的自我认识、情感和行为，以及对他人的不稳定的依赖和恐惧。", image: "/resources//2.jpg" },
          { id: 3, title: "成瘾 Addiction", description: "成瘾是一种以强迫性使用某种物质或活动为特征的疾病", image: "/resources/3.jpg" },
          { id: 4, title: "创伤后应激障碍 PTSD", description: "创伤后应激障碍（PTSD）是因经历或见证恐怖事件而引发的心理健康疾病。", image: "/resources/4.png" },
          { id: 5, title: "多动症 ADHD", description: "注意缺陷与多动障碍是一种以注意力无法持久集中、过度活跃和情绪易冲动为主症的神经发育障碍。", image: "/resources/5.jpg" },
          { id: 6, title: "反社会型人格障碍 APD", description: "反社会型人格障碍又称无情型人格障碍或社会性病态，是对社会影响最为严重的类型。", image: "/resources/6.jpg" },
          { id: 7, title: "分裂样人格障碍 SPD", description: "分裂样人格障碍是一种慢性且普遍的精神健康疾病，其特点是普遍脱离社会关系和普遍不感兴趣，并且在人际关系中很少表达情绪。", image: "/resources/7.png" },
          { id: 8, title: "分裂型人格障碍 SPD", description: "分裂型人格障碍既被视作一种人格障碍，又被视作精神分裂症谱系的一种精神病性障碍。", image: "/resources/8.jpg" },
          { id: 9, title: "回避型人格障碍 APD", description: "回避型人格的核心冲突是亲密需求与安全需求的对抗，他们既不能完全和明确地放弃关系，但又会因为缺乏安全感而在人际中退缩。", image: "/resources/9.jpg" },
          { id: 10, title: "焦虑 Anxiety", description: "焦虑症是一种常见的心理障碍，表现为在日常情况下的过度担忧和恐惧，干扰日常活动，难以控制。", image: "/resources/10.png" },
          { id: 11, title: "进食障碍 Eating Disorders", description: "进食障碍是以进食行为异常、对食物及体重和体型的过分关注为主要临床特征的一组疾病。", image: "/resources/11.jpg" },
          { id: 12, title: "精神分裂症 Schizophrenia", description: "精神分裂症是一种常见的重性精神障碍，其典型症状为精神病性症状，即无法区分真实与虚幻，且伴有多种精神状态的紊乱。", image: "/resources/12.jpg" },
          { id: 13, title: "恐慌症 Panic Disorder", description: "恐慌症是一种焦虑障碍，特征为没有预兆地一再惊恐发作。", image: "/resources/13.jpg" },
          { id: 14, title: "强迫症 OCD", description: "强迫症是一种常见的精神疾病，主要表现为反复出现的强迫观念、强迫冲动或强迫行为。", image: "/resources/14.jpg" },
          { id: 15, title: "双相情感障碍 Bipolar Disorder", description: "双相情感障碍又名双相障碍，是一种既有躁狂症发作，又有抑郁症发作（典型特征）的常见精神障碍。", image: "/resources/15.png" },
          { id: 16, title: "睡眠相位后移综合征 DPS", description: "睡眠相位后移症候群是一种慢性睡眠紊乱，患者的昼夜节律相较于大众和社会规范的节律出现延迟。", image: "/resources/16.png" },
          { id: 17, title: "偷窃癖 Kleptomania", description: "偷窃癖属于意志控制障碍范畴的精神障碍。其表现是反复出现的、无法自制的偷窃行为，虽屡遭惩罚而难于改正。", image: "/resources/17.jpg" },
          { id: 18, title: "抑郁症 Depression", description: "抑郁症是一种高发病、高临床治愈率但低治疗接受率以及高复发率的精神障碍，主要特征是显著而持久的情绪低落。", image: "/resources/18.jpg" },
          { id: 19, title: "自闭症 Autism", description: "自闭症是一种广泛性发展障碍（多见于儿童），病征包括异常的语言能力、异常的交往能力、狭窄的兴趣以及固执的行为模式等。", image: "/resources/19.jpg" },
          { id: 20, title: "自恋型人格障碍 NPD", description: "自恋型人格障碍是一种心理障碍，表现为对自己的过度关注和自我中心。", image: "/resources/20.png" }
        ]
      };
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
  