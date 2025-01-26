<template>
  <div class="main-content">
    <div style="width: 80%;margin:30px auto">
      <div style="text-align: center">
        <el-input placeholder="请输入器材名称查询" style="width: 300px" v-model="name"></el-input>
        <el-button type="primary" plain style="margin-left: 10px" @click="loadBorrow">查询</el-button>
        <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
      </div>
      <div style="margin-top: 30px">
        <el-row :gutter="30">
          <el-col :span="6" v-for="item in borrowData" style="margin-bottom: 30px">
            <img :src="item.img" alt="" style="width: 90%;height: 280px;border-radius: 10px;cursor:pointer">
            <div style="font-size: 17px;margin-top: 10px;font-weight: bold;display: flex">
              <div style="flex:1;color: #666666;">{{item.name}}</div>
              <div style="text-align: right">
                <el-tag type="primary" v-if="item.status==='空闲中'">{{item.status}}</el-tag>
                <el-tag type="danger" v-else>{{item.status}}</el-tag>
              </div>
            </div>

            <div style="margin-top: 10px;color: #666666">
              <div>位置:{{item.location}}</div>
              <div>编号:{{item.code}}</div>
            </div>
            <div style="margin-top: 15px">
              <el-button type="primary" style="padding-left: 30px;padding-right: 30px" @click="viewDescri(item.descr)">使用说明</el-button>
              <el-button type="info" style="padding-left: 30px;padding-right: 30px" :disabled="item.status==='使用中'" @click="reserveInit(item.id)">我要预约</el-button>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <el-dialog title="使用说明" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" >
       {{descr}}
      </el-form>
    </el-dialog>
    <el-dialog title="预约信息" :visible.sync="reserveVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px">
        <el-form-item prop="start" label="开始时间">
          <el-date-picker style="width: 100%"
              v-model="start"
              type="datetime"
              value-format="yyyy-MM-dd HH:mm::ss"
              placeholder="选择日期时间">
          </el-date-picker>
          <el-input v-model="start" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="end" label="结束时间">
          <el-date-picker style="width: 100%"
              v-model="end"
              type="datetime"
              value-format="yyyy-MM-dd HH:mm::ss"
              placeholder="选择日期时间">
          </el-date-picker>
          <el-input v-model="end" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="reserveVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
export default {
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      borrowData:[],
      descr:null,
      fromVisible:false,
      reserveVisible:false,
      name:null,
      start:null,
      end:null,
      borrowId:null,
    }
  },
  mounted() {
    this.loadBorrow()
  },
  methods: {
    loadBorrow(){
      this.$request.get('/borrow/selectAll',{
        params:{
          name:this.name
        }
      }).then(res=>{
        if(res.code==='200'){
          this.borrowData=res.data
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    viewDescri(descr){
      this.descr=descr
      this.fromVisible=true
    },
    reset(){
      this.name=null
      this.loadBorrow()
    },
    reserveInit(borrowId){
      if(this.user.role !== 'USER'){
        this.$message.warning('您的角色暂不支持该操作')
        return
      }
      this.borrowId=borrowId,
      this.start=null,
      this.end=null,
      this.reserveVisible=true
    },
    submit(){
      let data={
        userId:this.user.id,
        borrowId:this.borrowId,
        start:this.start,
        end:this.end,
        status:'待审核'
      }
      this.$request.post('/boreserve/add',data).then(res=>{
        if(res.code==='200'){
          this.$message.success('预约成功,请等待审核，您可以在器材预约里查看审核结果！')
          this.reserveVisible=false,
          this.borrowId=null
        }else {
          this.$message.error(res.msg)
        }
      })
    }
  }
};
</script>

