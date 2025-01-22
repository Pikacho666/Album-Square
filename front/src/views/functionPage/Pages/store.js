/**
 * 仓库js
 * Pages目录下的vue文件可以使用这个库
 */

//照片id
var PHOTO = {};
//上一个页面的路径
var LASTPATH = "";
//用户信息
var USERINFO = {};

export const setPhoto = (val)=>{
    PHOTO = val;
}
export const getPhoto = ()=>{
    return PHOTO;
}
export const setLASTPATH = (val)=>{
    LASTPATH = val;
}
export const getLASTPATH = ()=>{
    return LASTPATH;
}
export const setUSERINFO = (val)=>{
    USERINFO = val;
}
export const getUSERINFO = ()=>{
    return USERINFO;
}