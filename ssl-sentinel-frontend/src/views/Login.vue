<template>
  <div class="login-container" :style="cover">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">SSL Sentinel</h3>
      </div>
      <!-- 用户名 -->
      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          placeholder="用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
          clearable
          maxlength="20"
        />
      </el-form-item>
      <!-- 密码 -->
      <el-form-item prop="password">
        <el-input
          ref="password"
          :key="passwordType"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          clearable
          maxlength="20"
          show-password
        />
      </el-form-item>
      <!-- MFA动态码 -->
      <el-form-item prop="code">
        <el-input
          v-model="loginForm.code"
          prop="code"
          auto-complete="off"
          placeholder="MFA动态码"
          maxlength="6"
          clearable
        />
      </el-form-item>
      <!-- 登 录 -->
      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleLogin"
      >
        <span v-if="!loading">登 录</span>
        <span v-else>登 录 中...</span>
      </el-button>
    </el-form>
    <!--  底部  -->
    <div class="el-login-footer">
      <div>
        Copyright © {{ 2024 }} - {{ new Date().getFullYear() }}  
      <a href="https://tch.cool" target="_blank">
        我剑也未尝不利
      </a>
       . All Rights Reserved.
      </div>
    </div>
  </div>
</template>

<script>
import { validUsername, validPassword, validCode } from '@/utils/validate'
import { login } from '@/api/index'
import cover from '@/assets/login.jpg'
import {  USER_NAME } from '@/utils/constant'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('用户名由字母组成并且至少8位'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (!validPassword(value)) {
        callback(new Error('密码不能小于8位，且不能全是数字'))
      } else {
        callback()
      }
    }
    const validateCode = (rule, value, callback) => {
      if (!validCode(value)) {
        callback(new Error('请输入6位数MFA动态码'))
      } else {
        callback()
      }
    }
    return {
      showDialog: false,
      loginForm: {
        username: null,
        password: null,
        code: null
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }],
        code: [{ required: true, trigger: 'blur', validator: validateCode }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  computed: {
    cover() {
      return 'background: url(' + cover + ') center center / cover no-repeat'
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  mounted() {
  },
  created() {
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          let that = this
          that.loading = true

          login(that.loginForm).then(res => {
            // 保存username
            localStorage.setItem('USER_NAME', that.loginForm.username)
            // 画面跳转
            that.$router.push('/home')
          }).catch(() => {
            that.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
$bg: #2d3a4b;

/* reset element-ui css */
.login-container {
  .el-input {
    /*display: inline-block;*/
    height: 38px;
    width: 100%;

    input {
      background: transparent;
      border: 0px;
      appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;

      &:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: #fff !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  background-color: $bg;

  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 520px;
    max-width: 100%;
    padding: 35px 35px 15px 35px;
    margin: 15% auto;

    .el-input {
      height: 38px;

      input {
        height: 38px;
      }
    }
  }

  .title {
    font-size: 26px;
    font-weight: 400;
    color: $light_gray;
    margin: 0px auto 40px auto;
    text-align: center;
    font-weight: bold;
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .el-login-footer {
    height: 50px;
    line-height: 20px;
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    color: #fff;
    font-family: Arial;
    font-size: 12px;
    letter-spacing: 1px;
    display: flex;
    flex-direction: column;
  }
}
</style>

