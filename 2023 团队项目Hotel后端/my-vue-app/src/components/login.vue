<template>
    <div id="box">
      用户：<input type="text" v-model="name">
      <br/>
      密码：<input type="password" v-model="pwd">
      <br/>
      <button type="submit" @click="login">登录</button>
    </div>
  </template>
  <script setup>
  import {ref,reactive} from "vue";
  let name=ref('');
  let pwd=ref('');
  function login(){
    if (name.value=='admin' && pwd.value=='123456'){
      alert("登录成功")
    }else {
      alert("登录失败")
    }
  }
  </script>
  
  <style scoped>
  p {
    font-size: 18px;
    line-height: 20px;
  }
  </style>