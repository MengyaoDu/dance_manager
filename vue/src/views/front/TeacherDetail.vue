<template>
  <div class="main-content">
    <div style="width: 65%;margin: 20px auto">
      <div style="margin-top: 20px;font-size: 17px;font-weight: bold;color: #666666">{{teacherData.name}} 详细介绍</div>
      <div style="margin-top: 20px" v-html="teacherData.content" class="w-e-text-container"></div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      teacherId:this.$route.query.id,
      teacherData:{}
    }
  },
  mounted() {
    this.loadTeacher()
  },
  //methods：本页面所有的点击事件或者其他函数定义区
  methods:{
    loadTeacher(){
      this.$request.get('./teacher/selectById/'+this.teacherId).then(res=>{
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
<style>
  p{
    color: #333333;
  }
</style>
