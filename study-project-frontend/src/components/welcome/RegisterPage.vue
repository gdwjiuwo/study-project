<script setup>

import {Lock, Message, Promotion, User} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";

const form = reactive({
  username: '',
  password: '',
  rePassword: '',
  email: '',
  code: ''
})
const validatePass = (rule, value, callback) => {
  if (value === ''){
    callback(new Error('请输入用户名'))
  }else if(!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)){
    callback(new Error('用户名必须是中文、英文、数字'))
  }else
    callback()
}

const ValidatePassword = (rule, value, callback) => {
  if (value === ''){
    callback(new Error('请输入密码'))
  }else callback()
}

const ValidatePassword2=(rule, value, callback)=>{
  if (value === ''){
    callback(new Error('请再次输入密码'))
  }else if(value !== form.password){
    callback(new Error('两次输入密码不一致'))
  }else
    callback()
}



 const rules ={
  username:[{validator:validatePass,trigger:['blur','change']},
            {min:2,max:8,message:'用户名长度必须在2到8之间',trigger:['blur','change']}],

   password:[ {validator:ValidatePassword,trigger:['blur','change']},
       {min:6,max:16,message:'密码长度必须在6到16之间',trigger:['blur','change']}],
   rePassword:[ {validator:ValidatePassword2,trigger:['blur','change']}],
   email: [{type:'email', message:'请输入正确的电子邮箱地址', trigger:['blur','change']},
           {required: true, message:'请输入电子邮箱地址', trigger:['blur','change']}]
 }

 const isEmailValid=ref(false)
 const onValidate=(prop, isValid)=>{
  if (prop === 'email'){
    isEmailValid.value = isValid
  }
 }
</script>

<template>
  <div style="text-align: center;margin:0 20px">
    <div style="margin-top:100px ">
      <div style="font-size: 40px;color: black;font-weight: bolder">注册新用户</div>
      <div style="font-size: 20px;color: gray">请填写相关信息进行注册</div>
    </div>
    <div style="margin-top: 50px">
      <el-form :model="form" :rules="rules" @validate="onValidate">
        <el-form-item prop="username">
          <el-input v-model="form.username" type="text" placeholder="用户名">
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" placeholder="密码" >
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input v-model="form.rePassword" type="password" placeholder="请再次输入密码" >
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input v-model="form.email" type="email" placeholder="请输入电子邮箱地址">
            <template #prefix>
              <el-icon><Message /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-row>
            <el-col :span="18">
              <el-input v-model="form.code" type="email" placeholder="请输入验证码">
                <template #prefix>
                  <el-icon><Promotion /></el-icon>
                </template>
              </el-input>
            </el-col>
            <el-col :span="5">
              <el-button type="primary" :disabled="!isEmailValid">获取验证码</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>

    </div>

    <div style="margin-top: 60px">
      <el-button style="width: 100%" type="primary">注册</el-button>
    </div>
    <div style="margin-top: 10px">
      <el-link @click="$router.push('/')">已有账号？去登录</el-link>
    </div>
    </div>
</template>

<style scoped>

</style>