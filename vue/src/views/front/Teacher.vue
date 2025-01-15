<template>
  <div class="main-content">
    <el-carousel :interval="4000" type="card" height="400px">
      <el-carousel-item v-for="(imgUrl, index) in imgList" :key="index">
        <img :src="imgUrl" alt="" style="width: 100%; height: 100%; object-fit: cover;">
      </el-carousel-item>
    </el-carousel>
    <div style="width: 80%;margin: 30px auto">
      <div style="text-align: center;font-size: 28px;color: #737578">私教预约</div>
      <div style="text-align: center;font-size: 22px;color: #737578;margin: 15px 0">专业明星导师，一对一定制化教学</div>
      <div>
        <el-row :gutter="40">
          <el-col :span="6" v-for="item in teacherData" style="margin-bottom: 20px">
            <img @click="$router.push('/front/teacherDetail?id=' + item.id)" :src="item.avatar" alt="" style="width: 276px;height: 280px;border-radius: 20px;cursor: pointer;">
            <div style="margin-top: 10px;font-size: 17px;font-weight: bold;color: #666666">导师：{{item.name}}</div>
            <div style="display: flex; color: #666666;margin-top: 10px;text-align: center;align-items: center">
              <div style="flex: 1;text-align: start">风格:{{item.style}}</div>
              <div style="flex: 1">
                <el-button type="info">我要预约</el-button>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  }
</style>

<script>
export default {
  data() {
    return {
      imgList: [
        '/images/Carousel1.jpg',
        '/images/Carousel2.jpg',
        '/images/Carousel3.jpg',
        '/images/Carousel4.jpg',
        '/images/Carousel5.jpg',
        '/images/Carousel6.jpg',
        '/images/Carousel7.jpg',
        '/images/Carousel8.jpg',
        '/images/Carousel9.jpg',
        '/images/Carousel10.jpg',
        '/images/Carousel11.jpg',
        '/images/Carousel12.jpg'
      ],
      teacherData:[]
    }
  },
  mounted() {
    this.loadTeacher()
  },
  methods: {
    loadTeacher(){
      this.$request.get('/teacher/selectAll').then(res => {
        if(res.code==='200'){
          this.teacherData=res.data
        }else{
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>
