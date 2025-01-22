<template>
  <div>
    <div class="header">
      <IndexHeader></IndexHeader>
    </div>
    <div class="content">
      <router-view />
    </div>
  </div>
</template>
<script setup>
import IndexHeader from "@/components/IndexHeader.vue";
</script>
<style scoped>
.header {
  position: relative;
  width: 100%;
  top: 0%;
}
.content {
  position: absolute;
  width: 100%;
  height: calc(100% - 70px);
}
</style>
