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
        />
      </div>
    </section>

    <section class="doctors">
      <h2>医生专家</h2>
      <div class="doctors-container">
        <div class="doctors-content">
          <div class="title-section">
            <p class="subtitle">来自全国的100位专家 (bushi)</p>
            <p class="subtitle">非常可靠！ (bushi)</p>
            <button class="meet-team-btn">
              MEET THE TEAM 
              <span class="arrow">
                <router-link to="/">
                  <img src="@/assets/imgs/homepage/arrow.svg" alt="arrow" class="logo-arrow">
                </router-link>                  
              </span>
            </button>
          </div>


          <div class="doctor-grid">
            <DoctorCard
              v-for="doctor in doctors"
              :key="doctor.id"
              :image="doctor.image"
              :name="doctor.name"
              :hospital="doctor.hospital"
              :description="doctor.description"
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
      image: require('@/assets/imgs/tests/ocd.jpg'),
      title: '强迫症 OCD'
    },
    {
      id: 2,
      image: require('@/assets/imgs/tests/bipolar.jpg'),
      title: '双相情感障碍 Bipolar Disorder'
    },
    {
      id: 3,
      image: require('@/assets/imgs/tests/depression.jpg'),
      title: '抑郁症 Depression'
    }
  ])

  const doctors = ref([
    {
      id: 1,
      image: require('@/assets/imgs/doctors/doctor1.jpg'),
      name: '陈医生',
      hospital: '中大五院',
      description: '精神科医生，擅长精神病学、精神分裂症、抑郁症、强迫症、躁狂症等精神疾病的治疗。'
    },
    {
      id: 2,
      image: require('@/assets/imgs/doctors/doctor2.jpg'),
      name: '苏医生',
      hospital: '中大三院',
      description: '精神科医生，擅长精神病学、精神分裂症、抑郁症、强迫症、躁狂症等精神疾病的治疗。'
    },
    {
      id: 3,
      image: require('@/assets/imgs/doctors/doctor3.jpg'),
      name: '李医生',
      hospital: '中大四院',
      description: '精神科医生，擅长精神病学、精神分裂症、抑郁症、强迫症、躁狂症等精神疾病的治疗。'
    },
    {
      id: 4,
      image: require('@/assets/imgs/doctors/doctor4.jpg'),
      name: '唐医生',
      hospital: '中大三院',
      description: '精神科医生，擅长精神病学、精神分裂症、抑郁症、强迫症、躁狂症等精神疾病的治疗。'
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
  