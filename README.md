# 相册广场项目（Album Plaza）

## 项目简介

相册广场项目是一个基于 Spring Boot 和 Vue 开发的 Web 应用，用户可以上传、查看、分享图片。该系统包含用户管理、图片上传、图片查看、评论、点赞等功能，旨在为用户提供一个简单易用的图片展示平台。

## 技术栈

- 后端：Spring Boot
- 前端：Vue 3, Vue Router
- 数据库：MySQL
- 图片存储：转成 Base64 格式储存于数据库
- 构建工具：Maven, Vite

## 功能模块

### 1. 用户管理

- 用户注册与登录
- 用户信息修改
- 密码重置

### 2. 图片管理

- 图片上传与展示
- 图片查看与评论
- 图片点赞功能

### 3. 评论与互动

- 用户可以对图片进行评论
- 支持对评论点赞和回复

## 项目架构

### 后端架构

- **Spring Boot**：作为后端框架，负责处理请求，进行数据操作。
- **Spring Security**：用户认证与权限控制。
- **MySQL**：存储用户信息、图片信息、评论等数据。
- **Spring Data JPA**：数据访问层，简化数据库操作。
- **文件上传**：通过 Spring Boot 支持文件上传，存储图片文件。

### 前端架构

- **Vue 3**：前端框架，负责页面渲染和与后端交互。
- **Vue Router**：页面路由管理。
- **Axios**：用于与后端进行 API 请求。

## 环境搭建

### 1. 后端搭建

- 项目代码
  
  - back/demo_album即后端代码

- 配置数据库
  
  - 在`application.properties`文件中配置 MySQL 数据库连接：
  
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/demo_album
  spring.datasource.username=root
  spring.datasource.password=your_password
  ```

- 运行 Spring Boot 应用
  
  ```bash
  DemoAlbumApplication.java
  ```

### 2. 前端搭建

1. 项目代码
   
   - front/demo_album

2. 安装依赖
   
   ```bash
   npm install
   ```

3. 运行 Vue 应用
   
   ```bash
   npm run serve
   ```

## 项目运行截图

### 前端界面截图

- ![图片alt](/Users/jean/Desktop/demo_album%20项目源码/images/登录页面.jpg "图片title")
- ![用户登录页面](/Users/jean/Desktop/demo_album%20项目源码/images/注册页面.jpg)
- ![图片alt](/Users/jean/Desktop/demo_album%20项目源码/images/广场页面.jpg "图片title")
- ![图片alt](/Users/jean/Desktop/demo_album%20项目源码/images/图片详情页面.jpg "图片title")

## 项目优化与扩展

1. **图片优化**：可以实现图片压缩功能，减小图片体积，提高上传速度。
2. **广场加载逻辑**：更改加载逻辑为逐张加载
3. **用户权限管理**：实现管理员与普通用户角色，控制图片的上传、删除等操作。
4. **评论系统优化**：支持嵌套评论、评论举报等功能。
5. **分页功能**：对于图片列表、评论列表等，增加分页功能，提高用户体验。

## 联系方式

- **PersonWebsite**：[心中的念想](https://www.zrllove.cn "")
- **Email**：[admin@mail.zrllove.cn]([admin@mail.zrllove.cn](mailto:admin@mail.zrllove.cn))

## 许可证

本项目采用 [MIT License](LICENSE) 开源许可协议。
