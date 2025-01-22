import axios from "axios";
// import { ElMessage } from "element-plus";
// import router from "@/router";
const targetUrl = "http://localhost:8081/";
const instance = axios.create();

export const fileToBase64 = (file) => {
  return new Promise((resolve, reject) => {
    // 创建 FileReader 实例，用于读取文件
    const reader = new FileReader();
    
    // 读取文件为 Data URL（Base64 编码格式）
    reader.readAsDataURL(file);
    
    // 文件读取成功时触发 onload 事件
    reader.onload = () => {
      // 获取 Base64 编码的图片数据
      const base64Image = reader.result;
      
      // 将 Base64 编码的字符串传递给 Promise 的 resolve
      resolve(base64Image);
    };
    
    // 文件读取失败时触发 onerror 事件
    reader.onerror = error => {
      // 将错误信息传递给 Promise 的 reject
      reject(error);
    };
  });
};

export const getCurrentDateTime = () => {
  const now = new Date();
  const year = now.getFullYear();
  let month = now.getMonth() + 1;
  let day = now.getDate();
  let hours = now.getHours();
  let minutes = now.getMinutes();
  let seconds = now.getSeconds();

  // Add leading zero if needed
  month = month < 10 ? '0' + month : month;
  day = day < 10 ? '0' + day : day;
  hours = hours < 10 ? '0' + hours : hours;
  minutes = minutes < 10 ? '0' + minutes : minutes;
  seconds = seconds < 10 ? '0' + seconds : seconds;

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}
// 登录
export const Login = (data) => {
    return instance.post(targetUrl + "user/login",data).then((res) => {
        return res.data;
    });
};
// 注册
export const Regist = (data) => {
    return instance.post(targetUrl + "user/regist",data).then((res) => {
        return res.data;
    });
};
export const getUserInfo = (data) => {
  return instance.get(targetUrl + "user/getUserInfo?id=" + data).then((res) => {
      return res.data.data;
  });
};
//修改用户信息
export const modifyUserInfo = (data) => {
  return instance.post(targetUrl + "user/modeifyUserInfo",data).then((res) => {
      return res.data;
  });
};


//获取全部照片
export const getAllPhotos = () => {
  return instance.get(targetUrl + "photo/getAllPhotos").then((res) => {
      return res.data;
  });
};
//获取个人全部照片
export const getUserAllPhotos = (data) => {
  return instance.get(targetUrl + "photo/getUserAllPhotos?userID=" + data).then((res) => {
      return res.data;
  });
};
//新增相片
export const insertPhoto = (data) => {
  return instance.post(targetUrl + "photo/insertPhoto",data).then((res) => {
      return res.data;
  });
};
//删除照片
export const deletePhoto = (data) => {
  return instance.get(targetUrl + "photo/deletePhoto?photoId=" + data).then((res) => {
      return res.data;
  });
};


//获取照片下评论
export const getComments = (data) => {
  return instance.get(targetUrl + "comment/getComment?id=" + data).then((res) => {
      return res.data;
  });
};
//添加评论
export const insertComment = (data) => {
  return instance.post(targetUrl + "comment/insertComment",data).then((res) => {
      return res.data;
  });
};
//删除评论
export const deleteComments = (data) => {
  return instance.get(targetUrl + "comment/deleteComment?id=" + data).then((res) => {
      return res.data;
  });
};

//查看点赞人员
export const queryLikes = (data) => {
  return instance.get(targetUrl + "user/queryLikes?photoId="+data).then((res) => {
      return res.data;
  });
};
//点赞/取消点赞
export const like = (data) => {
  return instance.post(targetUrl + "user/likes",data).then((res) => {
      return res.data;
  });
};