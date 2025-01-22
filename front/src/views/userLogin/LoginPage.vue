<template>
  <div class="container">
    <h2 style="margin-bottom: 30px;">Welcome To Login!</h2>
    <el-form label-position="left" label-width="80px" style="font-weight: bold">
      <el-form-item label="account:">
        <el-input v-model="userInfo.account.value"> </el-input>
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
        <el-button type="success" @click="login" style="width: 100%"
          >Login</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="regist" style="width: 100%"
          >Regist</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>
<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import { Login } from "@/Util/api";
const router = useRouter();

//用户协议checkbox
var checkBox = ref(false);
//user
var userInfo = {
  account: ref(""),
  password: ref(""),
};
//登录
var login = () => {
  if (!checkBox.value) {
    ElMessage.warning("请勾选用户协议~");
    return;
  }
  if (userInfo.account.value == "" || userInfo.password.value == "") {
    ElMessage.warning("请填写账号/密码~");
  }
  const data = {
    account: userInfo.account.value,
    password: userInfo.password.value,
  };
  console.log(data);
  Login(data).then((res) => {
    if (res.code == 200) {
      ElMessage.success(res.message);
      localStorage.setItem("userId",res.data.id);
      router.push("/mainPage");
    } else {
      ElMessage.warning(res.message);
    }
  });
};
//注册
var regist = () => {
  router.push("/regist");
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
</style>
