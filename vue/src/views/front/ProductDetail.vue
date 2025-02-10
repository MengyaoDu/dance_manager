<template>
  <div class="main-content">
    <div style="width: 70%;margin: 30px auto">
      <div>
        <el-row :gutter="50">
          <el-col :span="6">
            <img :src="productData.img" alt="" style="width: 100%;height: 220px;border-radius: 5px">
          </el-col>
          <el-col :span="18">
            <div style="margin-bottom: 30px;font-size: 18px;color: #666666;font-weight: bold">产品名称:{{productData.name}}</div>
            <div style="margin-bottom: 30px;font-size: 18px;color: #666666;font-weight: bold">产品价格:<span style="color: red">￥{{productData.price}}</span></div>
            <div style="margin-top: 100px"><el-button type="primary" @click="buy">我要购买</el-button></div>
          </el-col>
        </el-row>
      </div>
      <div v-html="productData.content" class="w-e-text-container"></div>
    </div>
  </div>
</template>

<script>
import E from 'wangeditor'
export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      productId:this.$route.query.id,
      productData:{}
    }
  },
  mounted() {
    this.$request.get('/product/selectById/'+this.productId).then(res=>{
      if(res.code==='200'){
        this.productData=res.data
      }else {
        this.$message.error(res.msg)
      }
    })
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    buy(){
       let data={
          productId:this.productId,
          userId:this.user.id,
         price:this.productData.price
       }
       this.$request.post('/productOrders/add',data).then(res=>{
         if(res.code==='200'){
           this.$message.success('恭喜你购买成功！')
         }else{
           this.$message.error(res.msg)
         }
       })
    }
  }
}
</script>
