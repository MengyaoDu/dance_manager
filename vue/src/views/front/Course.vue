<template>
  <div class="main-content">
    <div style="width: 80%; margin: 30px auto">
      <div style="text-align: center">
        <el-input placeholder="请输入课程名称查询" style="width: 300px" v-model="name"></el-input>
        <el-button type="primary" plain style="margin-left: 10px" @click="loadCourse">查询</el-button>
        <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
      </div>
      <!-- 日期导航 -->
      <div style="display: flex; justify-content: center; align-items: center;margin-top: 30px; margin-bottom: 20px; padding: 10px; background-color: #f0f0f0; border-radius: 5px;">
        <span style="margin-right: 10px;">Today {{ currentDate }} ({{ getWeekDay(currentDate) }})</span>
        <el-button type="text" @click="prevDay">-</el-button>
        <el-button type="text" @click="nextDay">+</el-button>
      </div>
      <!-- 星期导航 -->
      <div style="display: flex; justify-content: flex-start; margin-bottom: 20px; overflow-x: auto; padding: 10px;">
        <el-button
            v-for="(day, index) in weekDays"
            :key="index"
            type="text"
            :class="{ 'active': selectedDayIndex === index }"
            @click="setCurrentDay(index)"
            style="margin: 0 5px; padding: 5px 10px; border: 1px solid #ccc; border-radius: 5px; cursor: pointer;"
        >
          {{ day }} ({{ getDateForDay(index) }})
        </el-button>
      </div>
      <!-- 课程列表 -->
      <div style="margin-top: 30px">
        <el-row :gutter="30">
          <el-col :span="24" v-for="item in filteredCourseData" :key="item.id" style="margin-bottom: 30px">
            <div style="background-color: #333; border-radius: 10px; padding: 20px;">
              <div style="display: flex; align-items: center; margin-bottom: 10px;">
                <span style="flex: 1; font-size: 17px; font-weight: bold; color: #fff;">{{ item.name }}</span>
                <span style="color: #fff;">{{ item.time }}</span>
              </div>
              <div style="display: flex; align-items: center; margin-bottom: 10px;">
                <img :src="item.img" alt="" style="width: 60px; height: 60px; border-radius: 50%; margin-right: 10px;"@click="$router.push('/front/courseDetail?id=' + item.id)">
                <div style="flex: 1; color: #fff;">
                  <div>{{ item.type }}</div>
                  <div>{{ item.teacher }}</div>
                  <div>难度 <span v-for="i in getDifficultyStars(item.difficulty)" :key="i">{{ i }}</span></div>
                  <div>{{ item.room }} 满{{ item.minPeople }}人开课</div>
                  <div>已预约 {{ item.bookedPeople }} / {{ item.totalPeople }}</div>
                </div>
                <el-button type="primary" :disabled="item.bookedPeople >= item.totalPeople" @click="handleBooking(item)">
                  {{ item.bookedPeople >= item.totalPeople? '加入等位' : '预约' }}
                </el-button>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentDate: new Date().toLocaleDateString(),
      weekDays: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
      courseData: [],
      name: null,
      selectedDayIndex: null  // 存储用户选择的具体日期的索引（例如：周一的索引为 0）
    };
  },
  computed: {
    filteredCourseData() {
      if (this.selectedDayIndex === null) {
        return this.courseData;  // 如果未选择特定日期，显示所有课程
      } else {
        return this.courseData.filter(course => {
          return course.time.includes(this.weekDays[this.selectedDayIndex]);  // 只显示用户选择的日期的课程
        });
      }
    }
  },
  mounted() {
    this.loadCourse();
  },
  methods: {
    loadCourse() {
      this.$request.get('/course/selectAll', {
        params: {
          name: this.name
        }
      }).then(res => {
        if (res.code === '200') {
          console.log('Received course data:', res.data);
          this.courseData = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    reset() {
      this.name = null;
      this.loadCourse();
    },
    prevDay() {
      const date = new Date(this.currentDate);
      date.setDate(date.getDate() - 1);
      this.currentDate = date.toLocaleDateString();
      this.loadCourse();
    },
    nextDay() {
      const date = new Date(this.currentDate);
      date.setDate(date.getDate() + 1);
      this.currentDate = date.toLocaleDateString();
      this.loadCourse();
    },
    setCurrentDay(index) {
      this.selectedDayIndex = index;  // 根据索引设置用户选择的日期
      this.loadCourse();  // 重新加载课程，以便根据新的选择过滤数据
    },
    handleBooking(item) {
      if (item.bookedPeople >= item.totalPeople) {
        this.$message.warning('该课程已满员，已加入等位');
      } else {
        this.$message.success('预约成功');
      }
    },
    getDateForDay(index) {
      const today = new Date(this.currentDate);
      const targetDay = new Date(today);
      targetDay.setDate(today.getDate() + index - today.getDay() + 1);
      return targetDay.toLocaleDateString();
    },
    getWeekDay(dateStr) {
      const date = new Date(dateStr);
      const days = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
      return days[date.getDay()];
    },
    getDifficultyStars(difficulty) {
      let stars = '';
      for (let i = 0; i < difficulty; i++) {
        stars += '★';
      }
      return stars;
    }
  }
};
</script>

<style scoped>
.active {
  color: yellow;
}
</style>