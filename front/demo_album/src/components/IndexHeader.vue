<template>
  <div class="container">
    <div style="float: left; font-size: 25px; font-style: italic;position: relative; top: 50%; transform: translateY(-50%);
">图片分享平台</div>
    <ul class="ul_items" style="display: flex; list-style: none">
      <li @click="$router.push('/mainPage')">广场</li>
      <li @click="$router.push('/userPage')">我的</li>
      <li @click="$router.push('/userManagerPage')">用户管理</li>
    </ul>
  </div>
</template>
<script setup>

</script>
<style scoped>
.container {
  width: 100%;
  height: 70px;
  box-shadow: 0px 12px 32px 4px rgba(0, 0, 0, 0.04),
    0px 8px 20px rgba(0, 0, 0, 0.08);
  padding: 10px;
  box-sizing: border-box;
  background-color: #dfe4ea;
}
.ul_items {
  float: left;
  position: relative;
  left: 50px;
  top: 50%;
  transform: translateY(-50%);
}
li {
    margin-right: 20px;
    font-size: 20px;
    color: black;
    transition: all 0.3s;
    cursor: pointer;
  }
  li:hover {
    color: gray;
  }
</style>
