<script setup>
import {User,Lock} from '@element-plus/icons-vue'
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router/index.js";
const form= reactive({
  username:'',
  password:'',
  remember:false
})
const login=()=>{
 if(!form.username||!form.password){
   ElMessage.warning('请输入账号和密码')
 }else {
   post('/api/auth/login',{
     username:form.username,
     password:form.password,
     remember:form.remember
   },(message)=>{
     ElMessage.success(message)
     router.push('/index')
   })

 }
}

</script>

<template>
  <div style="text-align: center;margin:0 20px">
    <div style="margin-top:150px ">
      <div style="font-size: 40px;color: black;font-weight: bolder">登录</div>
      <div style="font-size: 20px;color: gray">请输入账号和密码进行登录</div>
    </div>
    <div style="margin-top: 50px">
      <el-input v-model="form.username" type="text" placeholder="用户名/邮箱">
        <template #prefix>
          <el-icon><User /></el-icon>
        </template>
      </el-input>
      <el-input v-model="form.password" type="password" style="margin-top: 20px"  placeholder="密码">
        <template #prefix>
          <el-icon><Lock /></el-icon>
        </template>
      </el-input>
    </div>
    <el-row style="margin: 15px;">
      <el-col :span="12" style="text-align: left">
        <el-checkbox v-model="form.remember" label="记住我" ></el-checkbox>
      </el-col>
      <el-col :span="12" style="text-align: right;">
        <el-link>忘记密码?</el-link>
      </el-col>
    </el-row>
    <div style="margin-top: 30px">
      <el-button @click="login()" style="width: 370px" type="success" plain>立即登录</el-button>
    </div>
    <el-divider>
      <span style="color: grey ;font-size: 15px">没有账号？</span>
    </el-divider>
    <div>
      <el-button @click="router.push('/register')" style="width: 370px" type="warning" plain>注册账号</el-button>
    </div>
  </div>
</template>

<style >

</style>