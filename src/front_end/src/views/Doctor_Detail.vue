<template>
  <div class="doctor-detail-wrapper">
    <div class="doctor-info">
      <div class="top-section">
        <div class="image-and-text">
          <img :src="doctor.image" alt="医生照片" class="doctor-photo" />
          <div class="text-details">
            <div class="doctor-info-text">
              <div class="doctor-name-and-title">
                <div class="name-and-title-wrapper">
                  <div class="doctor-name">{{ doctor.name }}</div>
                  <div class="doctor-job-title" style="font-size: 18px; color: #777; margin-left: 10px;">主任医师</div>
                </div>
              </div>
              <div class="doctor-details">
                <div style="margin-top: 20px;"><strong>所在医院：</strong>{{ doctor.hospital }}</div>
                <div style="margin-top: 20px;"><strong>专业特长：</strong><span class="tag">{{ doctor.title }}</span></div>
                <div style="margin-top: 20px;"><strong>擅长：</strong>{{ doctor.specialties }}</div>
                <div style="width: 800px; margin-top: 20px; border-bottom: 2px dashed #006400;"></div>
                <p>如果你想了解该医生的更多信息（包括学习经历、患者评价），请点击右方链接：<a href="https://www.pumch.cn/detail/4356.html" class="learn-more">了解更多</a></p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="bottom-section">
        <div class="introduction-box">
          <div class="introduction-title">个人简介</div>
          <p>{{ doctor.introduction }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "DoctorPage",
  data() {
    return {
      doctor: {
        image: require("@/assets/imgs/doctors/doctor1.jpg"),
        name: "杜斌",
        title: "精神科",
        specialties: "顽固性失眠、抑郁症、焦虑症等精神疾病诊疗",
        hospital: "北京协和医院",
        jobTitle: "主任医师",
        introduction: "杜斌，北京协和医院精神科主任医师。在精神疾病领域有着丰富的临床经验和深厚的专业造诣。",
      },
    };
  },
};
</script>

<style scoped>
.doctor-detail-wrapper {
  background-color: #f9f9f9;
  padding: 60px 30px;
  margin-top: 120px;
  border-radius: 10px;
  margin-left: 250px;
  max-width: 1150px;
}

.top-section {
  display: flex;
  align-items: flex-start;
  margin-bottom: 20px;
}

.image-and-text {
  display: flex;
  align-items: flex-start;
}

.doctor-photo {
  flex: 0 0 130px;
  margin-right: 40px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.text-details {
  flex: 1;
  display: flex;
  flex-direction: column;
  height: 130px;
}

.doctor-info-text {
  font-size: 16px;
}

.name-and-title-wrapper {
  display: flex;
  flex-direction: row;
  align-items: flex-end;
}

.doctor-name {
  font-size: 28px;
  font-weight: bold;
}

.doctor-job-title {
  font-size: 18px;
  color: #777;
}

.tag {
  display: inline-block;
  padding: 5px 10px;
  background-color: #4CAF50;
  color: white;
  border-radius: 15px;
}

.doctor-details strong {
  font-weight: bold;
}

.bottom-section {
  margin-top: 20px;
}

.introduction-box {
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 15px;
}

.introduction-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 8px;
}

.introduction-box p {
  font-size: 14px;
  line-height: 1.5;
}

.learn-more {
  text-decoration: underline;
}
</style>