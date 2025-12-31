<script setup>

import {User,Lock} from '@element-plus/icons-vue'

</script>

<template>
  <div style="text-align: center;margin:0 20px">
    <div style="margin-top:150px ">
      <div style="font-size: 40px;color: black;font-weight: bolder">登录</div>
      <div style="font-size: 20px;color: gray">请输入账号和密码进行登录</div>
    </div>
    <div style="margin-top: 50px">
      <el-input type="text" placeholder="用户名/邮箱">
        <template #prefix>
          <el-icon><User /></el-icon>
        </template>
      </el-input>
      <el-input type="password" style="margin-top: 20px"  placeholder="密码">
        <template #prefix>
          <el-icon><Lock /></el-icon>
        </template>
      </el-input>
    </div>
    <el-row style="margin: 15px;">
      <el-col :span="12" style="text-align: left">
        <el-checkbox v-model="checked1" label="记住我" ></el-checkbox>
      </el-col>
      <el-col :span="12" style="text-align: right;">
        <el-link>忘记密码?</el-link>
      </el-col>
    </el-row>
    <div style="margin-top: 30px">
      <el-button style="width: 370px" type="success" plain>立即登录</el-button>
    </div>
    <el-divider>
      <span style="color: grey ;font-size: 15px">没有账号？</span>
    </el-divider>
    <div>
      <el-button style="width: 370px" type="warning" plain>注册账号</el-button>
    </div>
  </div>
</template>

<style >

</style>