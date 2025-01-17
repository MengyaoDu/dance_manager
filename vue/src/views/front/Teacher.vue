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
                <el-button type="info" @click="reserveInit(item.id)">我要预约</el-button>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <el-dialog title="预约信息" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" >
        <el-form-item prop="content" label="预约说明">
          <el-input type="textarea" :rows="5" v-model="content" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit">提 交</el-button>
      </div>
    </el-dialog>
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
      teacherData:[],
      content:null,
      fromVisible:false,
      teacherId:null,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
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
    },
    reserveInit(teacherId){
      if(this.user.role !== 'USER'){
        this.$message.warning('您的角色暂不支持该操作')
        return
      }
      this.teacherId=teacherId
      //打开对话框
      this.fromVisible=true
    },
    submit(){
      let data={
        userId:this.user.id,
        teacherId:this.teacherId,
        content:this.content
      }
      this.$request.post('/reserve/add', data).then(res=>{
        if(res.code==='200'){
          this.$message.success('恭喜你预约成功！')
          this.teacherId=null
          this.content=null
          this.fromVisible=false
        }
      })
    }
  }
}
</script>
