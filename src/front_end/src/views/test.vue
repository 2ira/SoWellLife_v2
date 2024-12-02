<!-- test.vue -->
<template>
  <div class="test-page">
    <ArticleBoxTest
        v-for="(article) in articles"
        :key="article.id"
        :title="article.title"
        :description="article.description"
        :articleId="article.id"
        :image="article.image"
        :jumpUrl="article.jumpUrl"
    />
  </div>
</template>

<script>
import ArticleBoxTest from "@/components/ArticleBoxTest.vue";

export default {
  name: "TestPage",
  components: {
    ArticleBoxTest
  },
  data() {
    return {
      articles: [
        {
          id: 1,
          title: "测试你有被害妄想症吗？",
          description: "“出现幻觉妄想！测试你有被害妄想症吗？”是一项被害妄想症测试，通过模拟情景和行为反应，评价个体是否存在被害妄想倾向，帮助了解个人心理状态，提倡心理健康意识。",
          image: require("@/assets/imgs/tests/1.png"),
          jumpUrl: "https://www.kuioo.cn/do-you-have-persecutory-delusions/question/"
        },
        {
          id: 2,
          title: "「边缘型人格测试」你有BPD边缘性人格障碍吗？",
          description: "“边缘型人格测试”是一种用于评价BPD（边缘性人格障碍）特征的心理健康工具。通过bpd测试，可以更好地了解个人的心理状态，寻求专业帮助。",
          image: require("@/assets/imgs/tests/2.png"),
          jumpUrl: "https://www.kuioo.cn/borderline-personality-disorder-bpd-test/question/"
        },
        {
          id: 3,
          title: "成人多动症测试：你有注意力缺陷多动障碍吗？",
          description: "事实上，研究表明，在18岁至44岁的成年人中，约有4%的人患有多动症。对于一些成年人来说，他们的多动症可能从小就没有得到诊断和治疗，这进一步加剧了他们面临的挑战。如果你担心自己可能患有多动症，可以考虑参加多动症测试，看看是否有必要由合格的医疗专业人员进行进一步评估。早期诊断和适当的治疗可以帮助多动症患者更好地控制症状，提高生活质量。",
          image: require("@/assets/imgs/tests/3.png"),
          jumpUrl: "https://www.kuioo.cn/adult-adhd-test-do-you-have-attention-deficit-hyperactivity-disorder/question/"
        },
        {
          id: 4,
          title: "反社会人格倾向测试",
          description: "三分钟反社会人格测试是一项用于评价个人社交行为和互动方式的测试。这项反社会人格测试可以帮助人们了解自己在社交方面可能存在的问题，并提供相应的建议和帮助。",
          image: require("@/assets/imgs/tests/4.png"),
          jumpUrl: "https://www.kuioo.cn/am-i-antisocial-antisocial-personality-disorder-quiz/question/"
        },
        {
          id: 5,
          title: "「回避型人格障碍测试」你有回避性人格吗？",
          description: "“回避型人格障碍测试”是一种心理评价工具，用于评价个体是否具有回避型人格特征，如过度敏感、社交恐惧和低自尊。通过测试，可以更好地理解自己的行为模式，寻求帮助。",
          image: require("@/assets/imgs/tests/5.png"),
          jumpUrl: "https://www.kuioo.cn/avoidant-personality-disorder-test-do-i-have-avpd/question/"
        },
        {
          id: 6,
          title: "焦虑症测试：你的焦虑程度有多深？",
          description: "焦虑症是一种常见的情绪障碍，它以过度、持续的担忧和恐惧为特征。这种情绪状态可能会严重影响个人的日常生活和心理健康。了解焦虑症并评价自己的焦虑水平，对于采取适当的应对措施至关重要。",
          image: require("@/assets/imgs/tests/6.png"),
          jumpUrl: "https://www.kuioo.cn/anxiety-test-find-out-your-anxiety-level/question/"
        },
        {
          id: 7,
          title: "「恐慌症症状测试」你的恐慌症指数有多高？",
          description: "想知道你的恐慌指数吗？试试“恐慌症测试”，简单几个问题，轻松了解你的焦虑水平，帮助你更好地管理情绪。",
          image: require("@/assets/imgs/tests/7.png"),
          jumpUrl: "https://www.kuioo.cn/panic-disorder-test-do-i-have-panic-disorder/question/"
        },
        {
          id: 8,
          title: "「强迫症测验」你有强迫型人格障碍倾向吗？",
          description: "「强迫症测验」快速评估你的强迫症倾向，你有强迫型人格障碍倾向吗？测强迫症指数了解你是否有过度强迫的行为和思维，为你的心理健康提供一个自我了解的窗口。",
          image: require("@/assets/imgs/tests/8.png"),
          jumpUrl: "https://www.kuioo.cn/obsessive-compulsive-personality-disorder-test-ocpd/question/"
        },
        {
          id: 9,
          title: "抑郁症心理测验「你有否患抑郁症？」快速测试",
          description: "探索心理健康，快速了解是否可能患有抑郁症。这个简短的自我评估抑郁症测试提供初步筛查，帮助你识别抑郁症状，及时寻求专业帮助。在我们的日常生活中，心理健康问题越来越受到关注，其中抑郁症是一种常见的情绪障碍。为了帮助个人更好地理解自己的心理状态，我们提供了一系列的抑郁症测试，抑郁症测试旨在评价您可能面临的抑郁症状，并提供相应的建议和指导。",
          image: require("@/assets/imgs/tests/9.png"),
          jumpUrl: "https://www.kuioo.cn/depression-quiz-am-i-depressed/question/"
        },
        {
          id: 10,
          title: "自闭症倾向测试",
          description: "如果你怀疑自己或你认识的人可能患有自闭症，从合格的医疗保健提供者那里获得专业诊断是很重要的。亚斯伯格症测试可以帮助你识别自闭症的一些常见症状，但它不能代替专业诊断。",
          image: require("@/assets/imgs/tests/10.png"),
          jumpUrl: "https://www.kuioo.cn/autism-spectrum-quotient-test-are-you-autistic/question/"
        },
        {
          id: 11,
          title: "「自恋型人格测试」测你有多自恋？",
          description: "你是否有过度关注自己外貌或成就的倾向？是否经常期待别人给予赞美和关注？通过自恋型人格测试，你可以探索自己是否具有自恋型人格的特征。这个自恋型人格测试虽然不是临床诊断，但可以提供关于自我形象和行为模式的有趣见解。",
          image: require("@/assets/imgs/tests/11.png"),
          jumpUrl: "https://www.kuioo.cn/narcissistic-personality-disorder-quiz/question/"
        },
      ]
    };
  }
};
</script>

<style scoped>
.test-page {
  padding: 50px;
  padding-left: 200px;
  padding-right: 200px;
  margin-top: 90px;
}
</style>