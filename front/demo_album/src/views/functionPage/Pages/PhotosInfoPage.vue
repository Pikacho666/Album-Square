<template>
  <div class="container">
    <div class="header">
      <img class="userAvatar" :src="Photo.ownerAvatar" alt="" />
      <h4 style="float: left">{{ Photo.ownerName }}</h4>
      <p style="
          float: left;
          font-size: 10px;
          margin-left: 10px;
          font-style: italic;
        ">
        发布于 {{ Photo.createTime }}
      </p>
      <div style="float: right">
        <el-icon class="likes" @click="handleLikes">
          <Star v-show="!isLikes"></Star>
          <CircleCheckFilled v-show="isLikes"></CircleCheckFilled>
        </el-icon>
        <el-button type="success" @click="back">返回</el-button>
      </div>
    </div>
    <div class="content">
      <img style="width: 50%" :src="Photo.imgBase64" alt="" />
      <p style="height: 40px; text-align: left">
        <b>{{ Photo.title }}</b>
      </p>
      <p style="height: 30px; text-align: left; color: gray">评论区</p>
      <div class="commentArea">
        <el-empty v-if="commentsData.length == 0" description="暂无评论" />
        <div class="comment" v-for="(item, index) in commentsData" :key="index">
          <el-row>
            <el-col :span="2">
              <img :src="item.ownerAvatar" style="
                  width: 50px;
                  height: 50px;
                  border-radius: 50%;
                  float: right;
                " alt="" />
            </el-col>
            <el-col style="font-weight: bold" :span="10">
              {{ item.ownerName }}
              <div v-show="item.ownerId == userInfo.id" style="float: right;">
                <div style="margin-left: 10px; font-size: 15px; color: gray; float: right; cursor: pointer;" @click="deleteComment(item)">删除</div>
                <div style="background-color: gray;color: white;float: right; width: 10px; padding: 2px; font-size: 10px;">我</div>
              </div>
            </el-col>
            <el-col style="font-size: 10px; color: gray" :span="6" :offset="4">
              {{ item.commentTime }}
            </el-col>
          </el-row>
          <el-row>
            <el-col style="text-align: left" :span="20" :offset="3">
              {{ item.content }}
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
    <div class="footer">
      <el-input placeholder="友好的评论温暖人心" v-model="comments" style="width: 90%"></el-input>
      <el-button type="success" @click="handleComment">评论</el-button>
    </div>
  </div>
</template>
<script setup>
import { ElMessage } from "element-plus";
import { onMounted, ref } from "vue";
import { getLASTPATH, getPhoto, getUSERINFO } from "./store";
import { getComments, insertComment, getCurrentDateTime, queryLikes, like, deleteComments } from "@/Util/api";
import router from "@/router";
var Photo = getPhoto();
var comments = ref("");
//评论数据
var commentsData = ref([]);
//是否点赞
var isLikes = ref(false);
//用户数据
var userInfo = {};
var handleComment = () => {
  if (comments.value == '') {
    ElMessage.warning("不能评论空内容哦~");
    return
  }
  if(Object.keys(userInfo).length == 0){
    ElMessage.warning("未登录~");
    router.push("/login")
    return
  }
  let data = {
    content: comments.value,
    ownerName: userInfo.userName,
    ownerAvatar: userInfo.userAvatar,
    commentTime: getCurrentDateTime(),
    photoId: Photo.id,
    ownerId: userInfo.id
  }
  insertComment(data).then((res) => {
    if (res.code == 200) {
      ElMessage.success(res.message);
      comments.value = "";
      getData();
      return
    }
    ElMessage.warning(res.message);
  })
}
var getData = () => {
  queryLikes(Photo.id).then((res) => {
    if (res.data.length == 0) {
      return;
    }
    let userId = localStorage.getItem("userId");
    for (let index = 0; index < res.data.length; index++) {
      if (userId == res.data[index]) {
        isLikes.value = true;
        return;
      }
    }
  })
  getComments(Photo.id).then((res) => {
      commentsData.value = res.data;

  })
}
var handleLikes = ()=>{
    let data = {
      photoId:Photo.id,
      userId : parseInt(localStorage.getItem("userId"))
    }
    like(data).then((res)=>{
      if(res.code == 200){
        isLikes.value = false;
        getData();
        return;
      }
      ElMessage.warning("操作失败~");
    })
}
var deleteComment = (item)=>{
  deleteComments(item.id).then((res)=>{
    if(res.code == 200){
      ElMessage.success(res.message);
      getData();
      return;
    }
    ElMessage.warning(res.message);
  })
}
var back = ()=>{
  router.push(getLASTPATH());
}
onMounted(async () => {
  getData();
  userInfo = getUSERINFO();
})
</script>
<style scoped>
.container {
  width: 50%;
  height: 100%;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  box-shadow: 0px 12px 32px 4px rgba(0, 0, 0, 0.04),
    0px 8px 20px rgba(0, 0, 0, 0.08);
  overflow: hidden;
}

::-webkit-scrollbar {
  width: 0;
}

.header {
  width: 100%;
  height: 70px;
  line-height: 70px;
  box-sizing: border-box;
  padding: 10px;
}
.userAvatar {
    width: 70px;
    height: 70px;
    border-radius: 50%;
    float: left;
    margin-right: 10px;
  }

.content {
  width: 100%;
  height: calc(100% - 120px);
  box-shadow: var(--el-box-shadow-light);
  box-sizing: border-box;
  padding: 5px;
  overflow: scroll;
}

.likes {
  width: 50px;
  height: 50px;
  font-size: 30px;
  transform: translateY(15%);
}

.footer {
  width: 100%;
  height: 50px;
  box-shadow: var(--el-box-shadow-light);
}

.commentArea {
  width: 100%;
}

.comment {
  width: 100%;
  box-shadow: var(--el-box-shadow-light);
  box-sizing: border-box;
  padding: 5px;
}
</style>
