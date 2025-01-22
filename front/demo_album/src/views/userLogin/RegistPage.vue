<template>
  <div class="container">
    <h2>Welcome To Regist!</h2>
    <el-form label-position="left" label-width="80px" style="font-weight: bold">
      <el-form-item label="Avatar:">
        <div class="IconArea" @click="handleAvatar">
          <el-icon
            v-show="userInfo.userAvatar.value == ''"
            style="width: 100%; height: 100%; font-size: 40px"
            ><User
          /></el-icon>
          <img
            v-show="userInfo.userAvatar.value != ''"
            :src="userInfo.userAvatar.value"
            style="
              width: 100%;
              height: 100%;
              background-size: cover;
              border-radius: 50%;
            "
          />
        </div>
      </el-form-item>
      <el-form-item label="name:">
        <el-input v-model="userInfo.userName.value"></el-input>
      </el-form-item>
      <el-form-item label="account:">
        <el-input v-model="userInfo.account.value"></el-input>
      </el-form-item>
      <el-form-item label="password:">
        <el-input v-model="userInfo.password.value"></el-input>
      </el-form-item>
      <el-form-item>
        <el-checkbox
          v-model="checkBox"
          style="margin-right: 10px"
        ></el-checkbox>
        我已查看并遵守《用户协议》
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="regist" style="width: 100%"
          >Regist</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="Back" style="width: 100%"
          >Back</el-button
        >
      </el-form-item>
      <input
        type="file"
        @change="handleChange"
        v-show="flase"
        id="fileLoader"
      />
    </el-form>
  </div>
</template>
<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import { Regist, fileToBase64 } from "@/Util/api";
fileToBase64;
const router = useRouter();
//用户协议checkbox
var checkBox = ref(false);
//user
var userInfo = {
  userAvatar: ref(""),
  userName: ref(""),
  account: ref(""),
  password: ref(""),
};
//注册
var regist = () => {
  if (!checkBox.value) {
    ElMessage.warning("请勾选用户协议~");
    return;
  }
  if (
    Object.values(userInfo).forEach((element) => {
      if (element.value == "") {
        ElMessage.warning("请认真完成表格~");
        return;
      }
    })
  );
  var data = {};
  Object.keys(userInfo).forEach((element) => {
    data[element] = userInfo[element].value;
  });
  Regist(data).then((res) => {
    if (res.code == 200) {
      ElMessage.success(res.message);
      router.push("/login")
    }else{
        ElMessage.warning(res.message);
    }
  });
};
//返回
var Back = () => {
  router.push("/login");
};
//选择头像
var handleAvatar = () => {
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
    userInfo.userAvatar.value = base64;
  });
};
</script>
<style scoped>
.container {
  width: 400px;
  box-shadow: 0px 12px 32px 4px rgba(0, 0, 0, 0.04),
    0px 8px 20px rgba(0, 0, 0, 0.08);
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  border-radius: 10px;
}
.IconArea {
  width: 100px;
  height: 100px;
  box-shadow: 0px 12px 32px 4px rgba(0, 0, 0, 0.04),
    0px 8px 20px rgba(0, 0, 0, 0.08);
  border-radius: 50%;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
}
</style>
