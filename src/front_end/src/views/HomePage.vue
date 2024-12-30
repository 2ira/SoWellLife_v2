<!-- HomePage.vue -->
<template>
  <AppNavbar @resource-click="goToResources" />
  <div class="home-container">
    <div class="news-section">
      <!-- 左侧轮播图部分 -->
      <div class="carousel-container">
        <HomeCarousel :items="carouselItems" />
      </div>
      
      <!-- 右侧资源列表部分 -->
      <div class="news-list">
        <h2 class="news-header">BANG NEWS</h2>
        <div class="resource-list">
          <ResourceCard
            v-for="resource in resources"
            :key="resource.id"
            :title="resource.title"
            :author="resource.author"
            :time="resource.time"
          />
          <button class="view-all-btn">
            READ MORE NEWS
            <router-link to="/">
              <img src="@/assets/imgs/homepage/arrow_blue.svg" alt="arrow" class="logo-arrow">
            </router-link>            
          </button>
        </div>
      </div>
    </div>

    <section class="tests">
      <h2>测试一下</h2>
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
      <h2>医生专家</h2>
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
      image: require('@/assets/imgs/resources/ppd.jpg'),
      title: 'Why Is It Still So Hard To Diagnose Adult ADHD?',
      category: 'HEALTH NEWS',
      author: 'By hhh'
    },
    {
      id: 2,
      image: require('@/assets/imgs/resources/schizotypal.jpg'),
      title: 'Why Is It Still So Hard To Diagnose Adult ADHD?',
      category: 'HEALTH NEWS',
      author: 'By hhhh'
    },
  ])

  const resources = ref([
    {
      id: 1,
      title: 'New Guidelines May Help Lower Your Stroke Risk: Key Updates You Need to Know',
      author: 'By hhhhh',
      time: '15 hours ago'
    },
    {
      id: 2,
      title: 'This Simple Tip Can Help You Adjust to the Daylight Saving Time Change',
      author: 'By hhhhh',
      time: '19 hours ago'
    },
    {
      id: 3,
      title: 'This Simple Tip Can Help You Adjust to the Daylight Saving Time Change',
      author: 'By Stephanie Brown',
      time: '19 hours ago'
    },
    {
      id: 4,
      title: 'This Simple Tip Can Help You Adjust to the Daylight Saving Time Change',
      author: 'By Stephanie Brown',
      time: '19 hours ago'
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


.section-header {
  text-align: left;
  margin-bottom: 2rem;
}
.news-section {
  display: flex;
  gap: 2rem;
  margin-bottom: 3rem;
  margin-top: 15px;
}

.carousel-container {
  flex: 0 0 60%;
}

.news-list {
  flex: 0 0 35%;
}



.news-header {
  background-color: #367b99;
  border-radius: 8px;
  color: white;
  padding: 1rem;
  margin: 0;
  font-size: 1.25rem;
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
  color: rgb(37, 81, 164);
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
  background-color: #2C7A7B;
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
  