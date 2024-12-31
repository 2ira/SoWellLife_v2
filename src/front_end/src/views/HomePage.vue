<template>
  <AppNavbar @resource-click="goToResources" />
  <div class="background">
    <div class="home-container">
      <div class="news-section">
        <!-- 左侧轮播图部分 -->
        <div class="carousel-container">
          <HomeCarousel :items="carouselItems" />
        </div>

        <!-- 右侧资源列表部分 -->
        <div class="news-list">
          <h2 class="news-header">经典病症视频介绍</h2>
          <div class="resource-list">
            <!-- 点击资源卡片跳转到对应 URL -->
            <ResourceCard
                v-for="resource in resources"
                :key="resource.id"
                :title="resource.title"
                :author="resource.author"
                :time="resource.time"
                :url="resource.url"
            />
          </div>


          <button class="view-all-btn" @click="goToResources('video')">
            更多视频资源
            <span class="logo-arrow-container">
            <img src="@/assets/imgs/homepage/arrow_blue.svg" alt="arrow" class="logo-arrow" />
          </span>
          </button>
        </div>
      </div>

      <section class="tests">
        <h2 class="test-content">测试一下</h2>
        <div class="test-cards">
          <TestCard
              v-for="test in tests"
              :key="test.id"
              :image="test.image"
              :title="test.title"
              :testUrl="test.testUrl"
          />
        </div>
      </section>

      <section class="doctors">
        <h2 class="doctor-content">医生专家</h2>
        <div class="doctors-container">
          <div class="doctors-content">
            <div class="title-section">
              <p class="subtitle">来自全国的多位专家</p>
              <p class="subtitle">非常可靠！</p>

              <router-link to="/doctors" class="meet-team-btn">
                MEET THE TEAM
                <span class="arrow">
                <img src="@/assets/imgs/homepage/arrow.svg" alt="arrow" class="logo-arrow">
              </span>
              </router-link>
            </div>

            <div class="doctor-grid">
              <DoctorCard
                  v-for="doctor in doctors"
                  :key="doctor.id"
                  :image="doctor.image"
                  :name="doctor.name"
                  :hospital="doctor.hospital"
                  :description="doctor.description"
                  :doctorUrl="doctor.doctorUrl"
              />
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>

</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import AppNavbar from '@/components/AppNavbar.vue'
import HomeCarousel from "@/components/HomeCarousel.vue"
import ResourceCard from "@/components/ResourceCard.vue"
import TestCard from "@/components/TestCard.vue"
import DoctorCard from "@/components/DoctorCard.vue"

const router = useRouter()
// 方法
const goToResources = (type) => {
  if (type === 'video') {
    router.push('/resources/video')
  } else if (type === 'article') {
    router.push('/resources/article')
  } else {
    router.push('/resources/video')
  }
}

// 数据
const carouselItems = ref([
  {
    id: 1,
    image: require('@/assets/imgs/Appnavbar.jpg'),
    title: '关于酒精和药物的重要事实-在美国面对成瘾',
    category: '成瘾',
    author: 'FROM 美国卫生与公众服务部',
    url:'https://www.ncbi.nlm.nih.gov/books/NBK424847/'
  },
  {
    id: 2,
    image: require('@/assets/imgs/article/16.jpg'),
    title: '进食障碍研究',
    category: '进食障碍',
    author: 'FROM NIMH',
    url:'https://www.nimh.nih.gov/health/topics/eating-disorders'
  },
])

const resources = ref([
  {
    id: 1,
    title: '【科普】被害妄想症',
    author: '',
    time: '发布于2023-12-23 16:31:34',
    url:'https://www.bilibili.com/video/BV1Vi4y1Y76a/?vd_source=14d775208ff520b9e2b9c6265544ea73'
  },
  {
    id: 2,
    title: '又妖又神？反社会人格障碍到底什么样？',
    author: '',
    time: '发布于2023-08-05 19:00:00',
    url:'https://www.bilibili.com/video/BV1iN411h7bf/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73'
  },
  {
    id: 3,
    title: '【TED科普】什么是精神分裂症（中英字幕）',
    author: '',
    time: '发布于2023-06-04 19:25:50',
    url:'https://www.bilibili.com/video/BV1XM4y1q7jt/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73'
  },
  {
    id: 4,
    title: '抑郁症，是怎么改变你大脑的？',
    author: '',
    time: '发布于2022-06-19 11:33:55',
    url:'https://www.bilibili.com/video/BV1da411s7BM/?spm_id_from=333.337.search-card.all.click&vd_source=14d775208ff520b9e2b9c6265544ea73'
  },
])

const tests = ref([
  {
    id: 1,
    image: require('@/assets/imgs/tests/1.png'),
    title: '测试你有被害妄想症吗？',
    testUrl:'https://www.kuioo.cn/do-you-have-persecutory-delusions/question/'
  },
  {
    id: 2,
    image: require('@/assets/imgs/tests/2.png'),
    title: '「边缘型人格测试」你有BPD边缘性人格障碍吗？',
    testUrl: 'https://www.kuioo.cn/borderline-personality-disorder-bpd-test/question/'
  },
  {
    id: 3,
    image: require('@/assets/imgs/tests/3.png'),
    title: '成人多动症测试：你有注意力缺陷多动障碍吗？',
    testUrl:'https://www.kuioo.cn/adult-adhd-test-do-you-have-attention-deficit-hyperactivity-disorder/question/'
  }
])

const doctors = ref([
  {
    id: 1,
    image: require('@/assets/imgs/doctors/doctor1.jpg'),
    name: '段艳平',
    hospital: '北京协和医院',
    description: '综合医院常见精神心理障碍的诊治，包括：抑郁障碍、广泛性焦虑障碍和惊恐障碍的诊断。',
    doctorUrl:'https://www.pumch.cn/department_ims/doctor/detail/14143.html'
  },
  {
    id: 2,
    image: require('@/assets/imgs/doctors/doctor2.jpg'),
    name: '洪霞',
    hospital: '北京协和医院',
    description: '综合医院各类精神心理问题的诊治，对老年期各类精神心理障碍有丰富的临床经验。 ',
    doctorUrl: 'https://www.pumch.cn/department_ims/doctor/detail/4517.html'
  },
  {
    id: 3,
    image: require('@/assets/imgs/doctors/doctor3.jpg'),
    name: '史丽丽',
    hospital: '北京协和医院',
    description: '综合医院常见精神心理障碍及问题，如：抑郁、焦虑、功能性胃肠病的心理生理问题。',
    doctorUrl: 'https://www.pumch.cn/department_ims/doctor/detail/4516.html'
  },
  {
    id: 4,
    image: require('@/assets/imgs/doctors/doctor4.jpg'),
    name: '李涛',
    hospital: '北京协和医院',
    description: '功能性躯体症状、焦虑障碍、抑郁等综合医院常见精神心理障碍的药物及心理治疗。',
    doctorUrl: 'https://www.pumch.cn/department_ims/doctor/detail/4511.html'
  }
])
</script>


<style scoped>

.background{
  background-image: url('@/assets/imgs/background4.jpg');
  background-size: cover;
  background-position: center;
}


.home-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1rem;
  margin-top: 100px;

}

section {
  margin-bottom: 3rem;
}

section h2 {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 1.5rem;
}

.test-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
}


.test-content{
  color: #1a365d;
  font-family: 'Microsoft YaHei', sans-serif;
  font-weight: bold;
}

.doctor-content{
  color: #1a365d;
  font-family: 'Microsoft YaHei', sans-serif;
  font-weight: bold;
}


.section-header {
  text-align: left;
  margin-bottom: 2rem;
}
.news-section {
  display: flex;
  gap: 2rem;
  margin-bottom: 3rem;
  margin-top: 28px;
}

.carousel-container {
  flex: 0 0 60%;
}

.news-list {
  flex: 0 0 35%;
}



.news-header {
  //background-color: #367b99;
  background-color: #1a365d;
  border-radius: 8px;
  color: white;
  padding: 1rem;
  margin: 0;
  font-size: 1.25rem;
  font-family: 'Microsoft YaHei', sans-serif; /* 修改字体家族 */
  font-weight: bold; /* 加粗文字 */
  line-height: 1.5;
}

.resource-list {
  display: flex;
  flex-direction: column;
  gap: 0.7rem;
  margin-top: 1rem;
}

@media (max-width: 992px) {
  .news-section {
    flex-direction: column;
  }

  .carousel-container,
  .news-list {
    flex: none;
    width: 100%;
  }
}

.view-all-btn {
  background-color: transparent;
  //color: rgb(37, 81, 164);
  color: #1a365d;
  border: none;
  padding: 0.1rem 1rem;
  border-radius: 9px;
  cursor: pointer;
  font-size: 1.4rem;
  margin-top: 0rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  white-space: nowrap;
  margin-left: auto;
  margin-right: auto;
  font-weight: bold;
}

.meet-team-btn {
  background-color: #1a365d;
  //background-color: #2C7A7B;
  color: white;
  border: none;
  padding: 0.75rem 1rem;
  border-radius: 9px;
  cursor: pointer;
  font-size: 1.4rem;
  margin-top: 2rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  white-space: nowrap;
  margin-left: auto;
  margin-right: auto;
  font-weight: bold;
}


.title-section {
  margin-top: 2rem;
  flex: 0 0 25%;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.subtitle {
  color: #666;
  margin: 0.25rem 0;
  font-size: 1.6rem;
  font-family:SoWellLifeFont, sans-serif;
}

.arrow {
  display: inline-block;
}

.doctors-container {
  background-color: #c2d1c0;
  padding: 2rem;
}

.doctors-content {
  display: flex;
  gap: 4rem;
}

.doctor-grid {
  flex: 0 0 70%;
  display: grid;
  grid-template-columns: repeat(2, 1fr); /* 2列布局 */
  grid-template-rows: repeat(2, auto); /* 2行布局 */
  grid-auto-flow: row; /* 确保按行填充 */
  gap: 1.5rem;
  max-width: 900px;
}

@media (max-width: 1200px) {
  .doctors-content {
    flex-direction: column;
    gap: 2rem;
  }

  .title-section {
    flex: none;
  }

  .doctor-grid {
    max-width: none;
  }
}

@media (max-width: 768px) {
  .doctor-grid {
    grid-template-columns: 1fr;
    grid-template-rows: auto;
  }

  .doctors-container {
    padding: 1rem;
  }
}
</style>
