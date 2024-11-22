<template>
  <div class="home-container">
    <div class="caption">
      <span class="s1">医师介绍</span> <span class="s2">共8名医生</span>
    </div>
    <section class="doctors">
      <div class="doctors-content">
        <div class="doctor-grid">
          <div class="item" v-for="doctor in doctors" :key="doctor.id">
            <a :href="`/doctor_detail`" target="_blank">
              <div class="doctor-avatar-wrapper">
                <img :src="doctor.image" alt="" class="doctor-avatar" />
                <div class="info">
                  <div class="inline">
                    <div class="h2">{{ doctor.name }}</div>
                    <div class="p">{{ doctor.title }}</div>
                  </div>
                </div>
              </div>
            </a>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "DoctorPage",
  data() {
    return {
      doctors: [
        {
          id: 1,
          image: require('@/assets/imgs/doctors/doctor1.jpg'),
          name: '杜斌',
          title: '副院长 教授 主任医师 博士研究生导师',
        },
        {
          id: 2,
          image: require('@/assets/imgs/doctors/doctor2.jpg'),
          name: '翁利',
          title: 'MICU主任 主任医师 博士研究生导师 Professor',
        },
        {
          id: 3,
          image: require('@/assets/imgs/doctors/doctor3.jpg'),
          name: '胡小芸',
          title: '副主任医师',
        },
        {
          id: 4,
          image: require('@/assets/imgs/doctors/doctor4.jpg'),
          name: '彭劲民',
          title: '副教授 副主任医师 硕士研究生导师',
        },
      ],
    };
  },
};
</script>

<style scoped>
.home-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
  margin-top: 100px;
}

.caption {
  font-size: 1.5rem; /* 大标题字体大小 */
  margin-bottom: 2rem; /* 标题与医生列表之间的间距 */
}

.s1 {
  font-weight: bold; /* 大标题加粗 */
}

.s2 {
  font-size: 0.9rem; /* 小标题字体缩小 */
  color: #666; /* 小标题颜色 */
}


.doctor-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 1.5rem;
}

.item {
  position: relative;
  overflow: hidden;
  border-radius: 12px;
  height: 350px; /* 设置高度以适应比例 */
}

.doctor-avatar-wrapper {
  width: 100%;
  aspect-ratio: 247 / 347; /* 设定宽高比例 */
  overflow: hidden; /* 隐藏溢出部分 */
  position: relative; /* 使 info 可以绝对定位 */
}

.doctor-avatar {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 使图片填满整个框 */
}

.info {
  position: absolute; /* 绝对定位 */
  bottom: 0; /* 固定在底部 */
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.6); /* 半透明背景 */
  color: white; /* 文字颜色 */
  padding: 10px; /* 增加内边距 */
  display: flex;
  flex-direction: column; /* 垂直排列 */
  justify-content: center; /* 居中对齐 */
  height: 35%; /* 设置固定高度 */
}

.inline {
  text-align: center; /* 中心对齐 */
  flex: 1; /* 占据剩余空间 */
}

.h2 {
  font-weight: bold;
  margin: 0; /* 确保没有额外的 margin */
  font-size: 1.2rem; /* 调整字体大小 */
  line-height: 1.3; /* 设置行高 */
}

.p {
  font-size: 1rem;
  margin: 5px 0 0; /* 增加上边距，减少下边距 */
  line-height: 1.5; /* 设置行高 */
}

@media (max-width: 1024px) {
  .doctor-grid {
    grid-template-columns: repeat(3, 1fr);
  }
}

@media (max-width: 768px) {
  .doctor-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 480px) {
  .doctor-grid {
    grid-template-columns: 1fr;
  }
}
</style>
