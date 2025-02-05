<template>
  <div class="main-content">
    <div style="width: 65%;margin: 20px auto">
      <div style="text-align: center;margin-top: 30px;font-size: 17px;font-weight: bold">{{communicateData.name}}</div>
      <div style="text-align: center;margin-top: 10px;color: #666666">
        <span>发布人：{{communicateData.userName}}</span>
        <span style="margin-left: 20px">发布时间:{{communicateData.time}}</span>
      </div>
      <div style="margin-top: 30px" v-html="communicateData.content" class="w-e-text w-e-text-container"></div>
    </div>
  </div>
</template>

<script>
import E from 'wangeditor'
export default {

  data() {
    return {
      communicateId:this.$route.query.id,
      communicateData:{}
    }
  },
  mounted() {
    this.loadCommunicate()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadCommunicate() {
      this.$request.get('/communicate/selectById/' + this.communicateId).then(res=>{
        if(res.code==='200'){
          this.communicateData = res.data
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
   color: #666666;
 }
</style>
