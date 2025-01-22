<template>
  <div class="main-content">
    <div style="width: 70%;margin: 30px auto">
      <div>
        <el-row :gutter="50">
          <el-col :span="6">
            <img :src="courseData.img" alt="" style="width: 100%;height: 220px;border-radius: 5px">
          </el-col>
          <el-col :span="18">
            <div style="margin-bottom: 20px;font-size: 18px;color: #666666;font-weight: bold">课程名称:{{courseData.name}}</div>
            <div style="margin-bottom: 20px;font-size: 18px;color: #666666">授课导师:{{courseData.teacherName}}</div>
            <div style="margin-bottom: 20px;font-size: 18px;color: #666666">上课时间:{{courseData.time}}</div>
            <div style="margin-bottom: 20px;font-size: 18px;color: #666666">课程价格:<span style="color: red"> ￥{{courseData.price}}</span></div>
            <div style="margin-top: 30px"><el-button type="primary">我要预约</el-button></div>
          </el-col>
        </el-row>
      </div>
      <div style="margin-top: 20px" v-html="courseData.content" class="w-e-text-container"></div>
    </div>
  </div>
</template>

<script>
import E from 'wangeditor'
export default {

  data() {
    return {
      courseId:this.$route.query.id,
      courseData:{}
    }
  },
  mounted() {
    this.$request.get('/course/selectById/' + this.courseId).then(res=>{
      if(res.code==='200'){
        this.courseData=res.data
      }else{
        this.$message.error(res.msg)
      }
    })
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {

  }
}
</script>
