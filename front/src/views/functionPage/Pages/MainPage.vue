<template>
  <div class="container">
    <el-empty
            v-show="imagesData.length == 0"
            description="暂无数据"
          />
    <div class="photoCard" v-for="(item, index) in imagesData" :key="index" @click="handleClick(item)">
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
      </div>
    </div>
  </div>
</template>
<script setup>
import { getAllPhotos,getUserInfo } from "@/Util/api";
import { ElMessage } from "element-plus";
import { onMounted } from "vue";
import { ref } from "vue";
import { setLASTPATH, setPhoto, setUSERINFO} from "./store";
import { useRoute, useRouter } from "vue-router";
const router = useRouter();
const route = useRoute();
//照片数据
var imagesData = ref([]);
onMounted(async() => {
  //判断是否有用户信息
  if(localStorage.getItem("userId") != null){
    console.log(localStorage.getItem("userId"));
    setUSERINFO( await getUserInfo(localStorage.getItem("userId")));
  }
  getAllPhotos().then((res) => {
    if (res.code == 200) {
      ElMessage.success(res.message);
      imagesData.value = res.data;
      return;
    }
    ElMessage.warning(res.message);
  });
});
var handleClick = (item)=>{
  setPhoto(item);
  setLASTPATH(route.path);
  router.push('/photoInfo');
}
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
.photoCard:active{
  transform: scale(0.99);
}
.footer {
  position: relative;
  line-height: 50px;
}
</style>
