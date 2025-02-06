<template>
  <div class="main-content">
    <div style="width: 65%;margin: 20px auto">
      <div style="text-align: center;margin-top: 30px;font-size: 17px;font-weight: bold">{{communicateData.name}}</div>
      <div style="text-align: center;margin-top: 10px;color: #666666">
        <span>发布人：{{communicateData.userName}}</span>
        <span style="margin-left: 20px">发布时间:{{communicateData.time}}</span>
      </div>
      <div style="margin-top: 30px" v-html="communicateData.content" class="w-e-text w-e-text-container"></div>
      <div style="font-size: 17px;font-weight: bold;margin: 30px 0;color: #9a6d2a ">请发布你对本论坛的评价:</div>
      <div>
        <el-input type="textarea" :rows="5" v-model="communicateComment" placeholder="请输入您的评价"></el-input>
        <div style="margin-top: 10px;text-align: right">
          <el-button type="primary" @click="submit">提 交</el-button>
        </div>
      </div>
      <div style="font-size: 17px;font-weight: bold;margin: 20px 0;color: #9a6d2a">看看其他小伙伴对本论坛的评价</div>
      <div style="margin: 20px 0 100px 0">
        <div v-for="comment in commentTree" :key="comment.id" style="margin-bottom: 30px">
          <el-row :gutter="10">
            <el-col :span="4">
              <div style="display: flex">
                <img :src="comment.userAvatar" alt="" style="width: 40px;height: 40px;border-radius: 50%">
                <div style="margin-left: 5px">{{comment.userName}}</div>
              </div>
            </el-col>
            <el-col :span="15">
              <div style="line-height: 40px">{{comment.content}}</div>
              <el-input v-if="comment.showReplyInput" type="textarea" :rows="3" v-model="comment.replyContent" placeholder="请输入回复内容"></el-input>
              <el-button v-if="comment.showReplyInput" type="primary" @click="replyComment(comment)">回复</el-button>
              <el-button v-if="!comment.showReplyInput" type="text" @click="showReplyInput(comment)">回复</el-button>
            </el-col>
            <el-col :span="5">
              <div style="line-height: 40px;text-align: right;color: #666666">{{comment.time}}</div>
            </el-col>
          </el-row>
          <!-- 展示回复消息 -->
          <div v-if="comment.replies.length > 0" style="margin-left: 50px">
            <div v-for="reply in comment.replies" :key="reply.id" style="margin-bottom: 15px">
              <el-row :gutter="10">
                <el-col :span="4">
                  <div style="display: flex">
                    <img :src="reply.userAvatar" alt="" style="width: 40px;height: 40px;border-radius: 50%">
                    <div style="margin-left: 5px">{{reply.userName}}</div>
                  </div>
                </el-col>
                <el-col :span="15">
                  <div style="line-height: 40px">{{reply.content}}</div>
                  <el-input v-if="reply.showReplyInput" type="textarea" :rows="3" v-model="reply.replyContent" placeholder="请输入回复内容"></el-input>
                  <el-button v-if="reply.showReplyInput" type="primary" @click="replyComment(reply)">回复</el-button>
                  <el-button v-if="!reply.showReplyInput" type="text" @click="showReplyInput(reply)">回复</el-button>
                </el-col>
                <el-col :span="5">
                  <div style="line-height: 40px;text-align: right;color: #666666">{{reply.time}}</div>
                </el-col>
              </el-row>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import E from 'wangeditor'
export default {
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      communicateId: this.$route.query.id,
      communicateData: {},
      communicateComment: null,
      commentData: [],
      commentTree: []
    }
  },
  mounted() {
    this.loadCommunicate()
    this.loadCommunicateComment()
  },
  methods: {
    loadCommunicateComment() {
      this.$request.get(`/communicateComment/selectByCommunicateId/${this.communicateId}`).then(res => {
        if (res.code === '200') {
          this.commentData = res.data.map(item => ({
            ...item,
            showReplyInput: false,
            replyContent: '',
            replies: []
          }))
          this.buildCommentTree()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadCommunicate() {
      this.$request.get('/communicate/selectById/' + this.communicateId).then(res => {
        if (res.code === '200') {
          this.communicateData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submit() {
      if (!this.communicateComment) {
        this.$message.warning('请输入评价内容')
        return
      }
      let data = {
        userId: this.user.id,
        communicateId: this.communicateId,
        content: this.communicateComment,
        parentId: null
      }
      this.$request.post('/communicateComment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('评价成功！')
          this.communicateComment = null
          this.loadCommunicateComment()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    showReplyInput(item) {
      item.showReplyInput = true
    },
    replyComment(item) {
      if (!item.replyContent) {
        this.$message.warning('请输入回复内容')
        return
      }
      let data = {
        userId: this.user.id,
        communicateId: this.communicateId,
        content: item.replyContent,
        parentId: item.id
      }
      this.$request.post('/communicateComment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('回复成功！')
          item.showReplyInput = false
          item.replyContent = ''
          this.loadCommunicateComment()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    buildCommentTree() {
      const commentMap = {}
      this.commentData.forEach(comment => {
        comment.replies = []
        commentMap[comment.id] = comment
      })
      this.commentTree = []
      this.commentData.forEach(comment => {
        if (comment.parentId === null) {
          this.commentTree.push(comment)
        } else {
          const parent = commentMap[comment.parentId]
          if (parent) {
            parent.replies.push(comment)
          }
        }
      })
    }
  }
}
</script>

<style>
p {
  color: #666666;
}
</style>