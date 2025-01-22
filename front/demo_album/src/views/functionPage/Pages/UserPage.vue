<template>
  <div class="container">
    <div class="userIntroduce">
      <el-row>
        <el-col :span="3">
          <img class="userAvatar" :src="userInfo.userAvatar" alt="" />
        </el-col>
        <el-col :span="6">
          <div style="position: relative; top: 50%; transform: translateY(-50%);">
            <h2 style="float: left;">{{ userInfo.userName }}</h2>
            <div style="font-size: 10px;color: gray;">
              <i >已发布 {{imagesData.length}} 条作品</i>
            </div>
          </div>
        </el-col>
        <el-col :span="2" :offset="10">
          <div style="position: relative; top: 50%; transform: translateY(-50%);">
            <el-button type="primary" @click="logout">退出登录</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="photoCard">
      <div
        @click="insertDialogVisiable = true"
        style="height: 370px; line-height: 370px"
      >
        <b>添加+</b>
      </div>
    </div>
    <div
      class="photoCard"
      v-for="(item, index) in imagesData"
      :key="index"
    >
      <div
        style="
          width: 100%;
          display: -webkit-box;
          -webkit-box-orient: vertical;
          overflow: hidden;
          -webkit-line-clamp: 1;
          padding: 4px;
          box-sizing: border-box;
          border-bottom: 1px gray solid;
          margin-bottom: 5px;
        "
      >
        {{ item.title }}
      </div>
      <img
      @click="handleClick(item)"
        style="
          width: 100%;
          height: 280px;
          background-size: contain;
          background-position: center;
        "
        :src="item.imgBase64"
        alt=""
      />
      <div class="footer">
        <img
          style="
            width: 40px;
            height: 40px;
            border-radius: 50%;
            float: left;
            margin-right: 4px;
          "
          :src="item.ownerAvatar"
          alt=""
        />
        <p style="float: left; font-size: 12px">{{ item.ownerName }}</p>
        <div style="float: right; margin-left: 10px">
          <el-icon style="font-size: 20px"><ChatLineSquare /></el-icon
          >{{ item.commentNum }}
        </div>
        <div style="float: right">
          <el-icon style="font-size: 20px"><Star /></el-icon>{{ item.likesNum }}
        </div>
        <div style="float: right;" @click="handelDelete(item)">
          <el-icon style="font-size: 20px;margin-right: 10px;"><Delete /></el-icon>
        </div>
      </div>
    </div>
    <el-dialog v-model="insertDialogVisiable" title="新增照片">
      <div style="height: 320px">
        <el-button type="primary" style="float: right" @click="handleInsert"
          >发布</el-button
        >
        <div
          @click="handlePhoto"
          style="
            width: 300px;
            height: 300px;
            box-shadow: var(--el-box-shadow-light);
            float: left;
          "
        >
          <img
            v-show="photos.imgBase64.value != ''"
            :src="photos.imgBase64.value"
            style="width: 100%; height: 100%"
            alt=""
          />
          <el-empty
            v-show="photos.imgBase64.value == ''"
            description="点击选择图片"
          />
        </div>
        <div
          style="
            width: calc(100% - 301px);
            float: left;
            padding: 5px;
            box-sizing: border-box;
          "
        >
          <el-form label-position="left" label-width="40px">
            <el-form-item label="Title">
              <el-input
                v-model="photos.title.value"
                type="textarea"
                autosize
              ></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </el-dialog>
    <input type="file" @change="handleChange" v-show="flase" id="fileLoader" />
  </div>
</template>
<script setup>
import {
  getCurrentDateTime,
  getUserAllPhotos,
  fileToBase64,
  insertPhoto,
  deletePhoto
} from "@/Util/api";
import { ElMessage } from "element-plus";
import { onMounted } from "vue";
import { getUSERINFO, setLASTPATH, setPhoto } from "./store";
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
const router = useRouter();
const route = useRoute();
//用户数据
const userInfo = getUSERINFO();
//照片数据
var imagesData = ref([]);
//insertDialog
var insertDialogVisiable = ref(false);
//照片实体
var photos = {
  imgBase64: ref(""),
  createTime: "",
  owner: localStorage.getItem("userId"),
  title: ref(""),
};
var handleClick = (item) => {
  setPhoto(item);
  setLASTPATH(route.path);
  router.push("/photoInfo");
};

var handleInsert = () => {
  for (const key in photos) {
    if (Object.hasOwnProperty.call(photos, key)) {
      const element = photos[key];
      if (element.value == "") {
        ElMessage.warning("请将信息填写完整");
        return;
      }
    }
  }
  let data = {
    imgBase64: photos.imgBase64.value,
    createTime: getCurrentDateTime(),
    owner: localStorage.getItem("userId"),
    title: photos.title.value,
  };
  insertPhoto(data).then((res) => {
    if (res.code == 200) {
      ElMessage.success(res.message);
      getData();
      insertDialogVisiable.value = false;
      return;
    }
    ElMessage.warning(res.message);
  });
};
var handlePhoto = () => {
  const fileUploader = document.querySelector("#fileLoader");
  fileUploader.click();
};
var handleChange = (e) => {
  const file = e.target.files[0];
  console.log(file);
  if (file.type != "image/jpeg" && file.type != "image/png") {
    ElMessage.warning("图片仅支持.jpg 或者 .png 格式！");
    return;
  }
  fileToBase64(file).then((base64) => {
    photos.imgBase64.value = base64;
  });
};
var getData = () => {
  getUserAllPhotos(localStorage.getItem("userId")).then((res) => {
    if (res.code == 200) {
      ElMessage.success(res.message);
      imagesData.value = res.data;
      return;
    }
    ElMessage.warning(res.message);
  });
};
var logout = ()=>{
  localStorage.setItem("userId",null);
  ElMessage.success("退出登录");
  router.push("/login")
}
var handelDelete = (item)=>{
  deletePhoto(item.id).then((res)=>{
    if(res.code == 200){
      ElMessage.success(res.message);
      getData();
      return
    }
    ElMessage.warning(res.message);
  })
}
onMounted(() => {
  if (localStorage.getItem("userId") == null) {
    ElMessage.warning("未登录~")
    router.push("/login");
    return;
  }
  getData();
});
</script>
<style scoped>
.container {
  width: 100%;
  height: 100%;
  padding: 20px;
  box-sizing: border-box;
  perspective: 1000px;
}
.photoCard {
  width: 270px;
  box-shadow: 0px 12px 32px 4px rgba(0, 0, 0, 0.04),
    0px 8px 20px rgba(0, 0, 0, 0.08);
  border-radius: 10px;
  box-sizing: border-box;
  padding: 10px;
  float: left;
  margin: 5px;
  transition: all 0.3s;
  background-color: white;
}
.photoCard:hover {
  transform: rotateX(5deg) translateY(-20px) scale(1.04);
  box-shadow: 0px 16px 48px 16px rgba(0, 0, 0, 0.08),
    0px 12px 32px rgba(0, 0, 0, 0.12), 0px 8px 16px -8px rgba(0, 0, 0, 0.16);
}
.photoCard:active {
  transform: scale(0.99);
}
.footer {
  position: relative;
  line-height: 50px;
}
.userIntroduce {
  width: 100%;
  height: 200px;
  box-sizing: border-box;
  padding: 10px;
  box-shadow: var(--el-box-shadow-lighter);
  margin: 0%;
  float: left;
}
.userAvatar {
    width: 170px;
    height: 170px;
    border-radius: 50%;
    float: left;
  }
</style>
