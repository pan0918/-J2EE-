<template>
  <div class="login-container">
    <el-form :model="ruleForm2" :rules="rules2"
             status-icon
             ref="ruleForm2"
             label-position="left"
             label-width="0px"
             class="demo-ruleForm login-page">
      <h3 class="title">学生信息管理系统</h3>
      <el-form-item prop="name">
        <el-input type="text"
                  v-model="ruleForm2.name"
                  auto-complete="off"
                  placeholder="请输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
                  v-model="ruleForm2.password"
                  auto-complete="off"
                  placeholder="请输入密码"
        ></el-input>
      </el-form-item>
      <el-checkbox
        v-model="checked"
        class="rememberme"
      >记住密码
      </el-checkbox>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="handleSubmit()" :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios, {request} from "axios";

export default {
  name: "login",
  data(){
    return {
      logining: false,
      ruleForm2: {
        name: '',
        password: '',
      },
      rules2: {
        name: [{required: true, message: 'please enter your account', trigger: 'blur'}],
        password: [{required: true, message: 'please enter your password', trigger: 'blur'}]
      },
      checked: false
    }
  },
  methods: {
    handleSubmit(){
      this.logining=true;
      this.$refs.ruleForm2.validate((valid) => {
        if(valid){
          this.$axios.post("http://localhost:9090/login", {
            name: this.ruleForm2.name,
            password: this.ruleForm2.password
          }).then(response=>{
            console.log(response)
            if(response.data === true){
              this.logining = false;
              sessionStorage.setItem('user', this.ruleForm2.name)
              this.$router.push({path: '/index'})
            }else{
              this.logining=false;
              alert("账号或密码错误！");
            }
          });
        }else{
          this.logining=false;
          console.log("账号或密码错误！")
        }
      })
    }
    /*handleSubmit(){
      this.logining = true;
      this.$axios.post("http://localhost:9090/login", {
        name: this.ruleForm2.name,
        password: this.ruleForm2.password
      }).then(response=>{
        console.log(response)
        if(response.data === true){
          this.logining = false;
          sessionStorage.setItem('user', this.ruleForm2.name)
          this.$router.push({path: '/index'})
        }else{
          this.logining=false;
          alert("校验失败！");
        }
      });
    }*/

  }
}
</script>

<style scoped>

.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  margin: 0;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}

.title{
  text-align: center;
}
</style>

