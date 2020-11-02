<template id="main">
  <div style="height: 100vh;text-align: center ;margin: auto" @keyup.enter="logIn">
    <div style="top: 50%;left:50%;position: absolute;margin-top: -130px;margin-left: -125px">
            <el-row style="line-height: 50px;font-size: 16px;font-weight: bold;color: #999;">用户登录 / USER
              LOGIN</el-row>
            <el-row style="padding: 0 0 15px 0;">
              <el-input type="text" clearable v-model="name" placeholder="请输入用户名称"
                        prefix-icon="el-icon-user" style="width: 250px"></el-input>
            </el-row>
            <el-row style="padding: 0 0 15px 0;">
              <el-input type="password" clearable show-password v-model="password" placeholder="请输入登录密码"
                        prefix-icon="el-icon-lock" style="width: 250px" ></el-input>
            </el-row>
            <el-row>
              <el-button type="primary" style="width: 240px;height: 50px; text-align: center" @click="logIn()">登 录
              </el-button>
            </el-row>
      <el-link style="top: 90%" @click="down" > 请使用360浏览器急速内核，点击下载</el-link>
    </div>
  </div>
</template>


<script>
export default{
  name: 'Login',
  data() {
    return {
      name: "",
      password: "",
    }
  },
  mounted() { },
  methods: {
    logIn() {
      if (this.name == "") {
        this.$message.error("请输入用户名称")
      } else if (this.password == "") {
        this.$message.error("请输入登录密码")
      } else {
        this.$axios
          .post(this.$baseUrl + 'user/login', {
            username: this.name,
            paw: this.password,
          })
          .then(res => {
            if (res.data.code == 10) {
              this.$message.error("账号或密码错误")
            }
            else {
              sessionStorage.setItem("appraise", JSON.stringify({
                id: res.data.data.id,
                pid: res.data.data.pid,
                pawState : res.data.data.pawState,
                tid : res.data.data.tid,
                name : res.data.data.name,
                position : res.data.data.position}));
              // alert(res.data.data.pid)
                this.$router.push('/appraiseMain')
            }
          })
          .catch(res => (console.log(res)));
      }
    },
    down(){
      this.$message.success("即将开始下载");
      window.location.href = this.$baseUrl + 'down/down360';
    }
  }
}
</script>

<style scoped>

</style>
