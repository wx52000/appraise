<template>
  <div id="main" style="height: 100vh">
    <el-container style="height: 100%">
      <!-- 左 -->
      <el-aside width="20%" heigth="100%" style="background-color: #3a8ee6;">
        <!-- logo名字 -->
        <el-row @click.native="back()" style="padding: 10px 0 0 20px ; text-align: center">
          <el-col :span="22">
            <el-row style="margin-top: 8px;text-align: center;color: #000000;font-size: 16px;font-weight: bold;">发电工程公司综合管理
            </el-row>
          </el-col>
        </el-row>
        <!-- 导航 -->
        <el-row>
          <el-menu  default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"background-color="#3a8ee6" >
          <el-menu-item index="1" @click="toPage('/home/adminProject')">
            <span slot="title">项目管理</span>
          </el-menu-item>
            <el-submenu index="2">
              <template slot="title">
                <el-row >个人评价</el-row>
              </template>
              <el-menu-item-group>
                <el-menu-item class="nav2" index="2-1" @click="toPage('/home/perScore')">整体数据
                </el-menu-item>
                <el-menu-item class="nav2" index="2-2" @click="toPage('/home/perDetails')">数据详情
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">
                <el-row >专业评价</el-row>
              </template>
              <el-menu-item-group>
                <el-menu-item class="nav2" index="3-1" @click="toPage('/home/tecScore')">整体数据
                </el-menu-item>
                <el-menu-item class="nav2" index="3-2" @click="toPage('/home/tecDetails')">数据详情
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title">
                <el-row >系统管理</el-row>
              </template>
              <el-menu-item-group>
                <el-menu-item class="nav2" index="4-1" @click="toPage('/home/personal')">人员管理
                </el-menu-item>
                <el-menu-item class="nav2" index="4-2" @click="toPage('/home/department')">部门管理
                </el-menu-item>
                <el-menu-item class="nav2" index="4-3" @click="toPage('/home/appraiseManage')">评价管理
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-row>
      </el-aside>
      <!-- 顶 -->
      <el-container>
        <el-header >
          <el-row style="margin: 17px 0; font-size:20px;color: #999;">
            <el-dropdown trigger="click" style = "float: right;width:100px;margin-right: 10%">
              <el-row >
                <span style="font-family:'FontAwesome',serif;"></span>
                <span style="font-family:'微软雅黑',serif;font-size:18px;color: #000000"> {{name}} </span>
                <span style="font-family:'FontAwesome',serif;font-size:12px;"></span>
              </el-row>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native=logOut()>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-row>
        </el-header>
        <!-- 主 -->
        <el-main style="padding: 0; height: 100%"  >
          <router-view v-if ="isRouterAlive" />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
name: "Home",
  provide () {    //父组件中通过provide来提供变量，在子组件中通过inject来注入变量。
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      logIn: true,
      checkInPage: false,
      id: "",
      name: "",
      level: "",
      isRouterAlive: true,
      list: [],
    }
  },
  mounted() {
    this.getLogIn();
  },
  methods: {handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    getLogIn() {
      let i = JSON.parse(sessionStorage.getItem("appraise"));
      if (i == null) {
        this.$message.error("请登录！");
        setTimeout(function () { window.location.href = "/"; }, 1000)
      } else
      {
        this.logIn = true;
        this.id = i.id;
        this.name = i.name;
      }
    },
    logOut() {
      sessionStorage.removeItem("appraise");
      this.getLogIn();
    },
    back() {
      this.$router.push('/home');
    },
    toPage(path){
      this.$router.replace(path);
    },
    reload () {
      this.isRouterAlive = false;            //先关闭，
      this.$nextTick(function () {
        this.isRouterAlive = true;         //再打开
      })
    },
  }
}
</script>

<style scoped>


.nav2 {
  padding: 0px 0px 0px 30px;
}
</style>
