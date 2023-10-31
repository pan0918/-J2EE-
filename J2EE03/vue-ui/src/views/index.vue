<template>
  <el-container>
    <!-- width的宽度跟collapse一样动态控制 -->
    <el-aside width="collapse">
      <div class="logo" v-show="open"><h3><i class="el-icon-eleme"></i>学生信息管理系统</h3></div>
      <div class="logo" v-show="close"><h3><i class="el-icon-eleme"></i></h3></div>
      <!-- :collapse="isCollapse"  class="el-menu-vertical" 动态控制导航菜单的收起与展开  router：让index作为 path 进行路由跳转 -->
      <el-menu default-active="$route.path"
               router
               :default-openeds="openeds"
               :collapse="isCollapse"
               class="el-menu-vertical">
        <el-submenu index="1">
          <!-- 一级标题 -->
          <template slot="title">
            <i class="el-icon-s-tools"></i>
            <span slot="title">后台管理</span>
          </template>
          <!--  二级标题 -->
          <el-menu-item index="/console">
            <i class="el-icon-setting"></i>
            <span slot="title">控制台</span>
          </el-menu-item>
          <el-menu-item index="/student">
            <i class="el-icon-setting"></i>
            <span slot="title">学生管理</span>
          </el-menu-item>
        </el-submenu>
      </el-menu>

    </el-aside>
    <el-container>
      <el-header>
        <div class="trigger" @click="isShow">
          <!-- 点击展开收起导航和切换对应图标 -->
          <i class="el-icon-s-fold" v-show="open"></i>
          <i class="el-icon-s-unfold" v-show="close"></i>
        </div>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      openeds: ["1"],
      isCollapse: false, //导航栏默认为展开
      close: false, //第二个图标默认隐藏
      open: true, //默认显示第一个图标
    }
  },
  methods: {
    isShow() {
      this.isCollapse = !this.isCollapse;
      this.open = !this.open;
      this.close = !this.close;
    },
  }
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
  height: 100%;
  padding: 0 !important;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  height: 100vh;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
}

body > .el-container {
  margin-bottom: 40px;
}

.logo {
  height: 60px;
  line-height: 60px;
  background-color: antiquewhite;
  text-align: center;
}

.logo h3 {
  margin: 0;
  height: 60px;
}

.el-menu {
  border-right-width: 0;
}

.el-menu-vertical:not(.el-menu--collapse) {
  width: 240px;
}

.trigger {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  width: 54px;
}

.trigger i {
  font-size: 20px;
}

.trigger:hover {
  background-color: rgb(203, 215, 230);
}
</style>

