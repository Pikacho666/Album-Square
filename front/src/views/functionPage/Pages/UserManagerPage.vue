<template>
    <div class="container">
        <el-button type="success" style="position: relative;right: 0%;top: 0%;"
            @click="chengeInfo">{{ buttonTitle }}</el-button>
        <div class="avatar" @click="handleAvatar">
            <img style="width: 80%;height: 80%; border-radius: 50%;" :src="userInfo.userAvatar.value" alt="" />
        </div>
        <div class="info" style="margin-top: 100px;">
            <el-form label-position="left">
                <el-form-item label="昵称:">
                    <el-input :disabled="changeMode" style="width: 200px;" v-model="userInfo.userName.value"></el-input>
                </el-form-item>
                <el-form-item label="密码:">
                    <el-input :disabled="changeMode" style="width: 200px;" v-model="userInfo.password.value"></el-input>
                </el-form-item>
            </el-form>
        </div>
        <input type="file" @change="handleChange" v-show="flase" id="fileLoader" />
    </div>
</template>
<script setup>
import { onMounted, ref } from 'vue';
import { getUSERINFO, setUSERINFO } from './store';
import { ElMessage } from 'element-plus';
import { fileToBase64, modifyUserInfo } from '@/Util/api';
var defaultUserInfo = getUSERINFO();
var changeMode = ref(true);
var buttonTitle = ref("修改");
var userInfo = {
    userAvatar: ref(""),
    userName: ref(""),
    password: ref(""),
};
var handleAvatar = () => {
    if(changeMode.value){
        ElMessage.warning("请先点击右上角修改哦~")
        return
    }
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
var chengeInfo = () => {
    if (changeMode.value) {
        changeMode.value = false;
        buttonTitle.value = "保存"
    }else{
        buttonTitle.value = "修改"
        changeMode.value = true;
        var data = {
            id:defaultUserInfo.id,
            userAvatar:userInfo.userAvatar.value,
            password:userInfo.password.value,
            userName:userInfo.userName.value
        }
        modifyUserInfo(data).then((res)=>{
            if(res.code == 200){
                ElMessage.success(res.message);
                setUSERINFO(res.data);
                return;
            }
            ElMessage.warning(res.message);
        })
    }
}

onMounted(() => {
    Object.keys(userInfo).forEach(element => {
        console.log(element);
        userInfo[element].value = defaultUserInfo[element];
    });
    console.log(userInfo);
})
</script>
<style scoped>
.container {
    width: 700px;
    height: 300px;
    padding: 10px;
    box-sizing: border-box;
    box-shadow: var(--el-box-shadow-lighter);
    position: absolute;
    top: 30%;
    left: 50%;
    transform: translate(-50%, -50%);
}

.avatar {
    width: 300px;
    height: 300px;
    float: left;
}

.info {
    width: 300px;
    height: 300px;
    float: left;
}
</style>