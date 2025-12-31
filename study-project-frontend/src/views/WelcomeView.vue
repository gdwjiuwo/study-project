<script setup>
import lalalaImage from '@/assets/lalala.jpg'
</script>

<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display:flex">
    <div style="flex: 1">
      <el-image style="width: 100%;height: 100%;object-fit: cover"
                :src="lalalaImage"></el-image>
    </div>
    <div class="welcome-title">
      <div style="font-size: 30px;font-weight: bold">登录测试界面</div>
      <div style="margin-top: 14px">该模块用于测试功能</div>
    </div>
    <div style="width: 600px;background: white">
       <router-view></router-view>
    </div>
  </div>
</template>

<style scoped>
.welcome-title{
  position: absolute;bottom: 30px;
  left: 30px;
  color: white;
  text-shadow: 0 0 10px black;

}
</style>