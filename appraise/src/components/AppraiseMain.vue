<template>
  <div>
    <div>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" >
    <el-submenu index="1">
      <template slot="title">项目管理</template>
      <el-menu-item index="/appraiseMain/generalProject" @click="toPage('/appraiseMain/generalProject')">设总管理</el-menu-item>
      <el-menu-item index="/appraiseMain/principalProject" @click="toPage('/appraiseMain/principalProject')">主设人管理</el-menu-item>
      <el-menu-item index="/appraiseMain/designerProject" @click="toPage('/appraiseMain/designerProject')">设计人管理</el-menu-item>
      <el-menu-item index="/appraiseMain/checkerProject" @click="toPage('/appraiseMain/checkerProject')">互校人管理</el-menu-item>
    </el-submenu>
    <el-submenu index="2">
    <template slot="title">我的评价</template>
    <el-menu-item index="/appraiseMain/appraise" @click="toPage('/appraiseMain/appraise')">个人评价</el-menu-item>
    <el-menu-item index="/appraiseMain/tecAppraise" @click="toPage('/appraiseMain/tecAppraise')">专业评价</el-menu-item>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title">我的得分</template>
    <el-menu-item index="/appraiseMain/perScore" @click="toPage('/appraiseMain/perScore')">个人得分</el-menu-item>
      <el-menu-item index="/appraiseMain/tecScore" @click="toPage('/appraiseMain/tecScore')">专业得分</el-menu-item>
    </el-submenu>
      <el-menu-item v-if="position !== null && position.indexOf(`11`) != -1" index="/appraiseMain/headmanProject" @click="toPage('/appraiseMain/headmanProject')">组长管理</el-menu-item>
    <el-menu-item index="/home" @click="toPage('/home')"
                  style="float: right" v-if="pid == 1">系统管理</el-menu-item>
  </el-menu>
    </div>
    <div>
      <router-view/>
    </div>
    <el-dialog
      id="pawDialog"
      title="密码修改"
      :visible.sync="visible"
      width="40%"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :showClose="false">
      <div style="width:100%;text-align:center">
      <el-form id="paw" :model="paw" :rules="ruleValidate" label-width="100px"  >
        <el-form-item prop="paw1" label="新密码"  style="width: 60%; padding-left: 15%">
          <el-input pro v-model="paw.paw1" type="password"  autocomplete="off"
                    placeholder="请输入新密码"></el-input>
        </el-form-item>
        <el-form-item prop="paw2" label="确认密码" style="width: 60%; padding-left: 15%">
          <el-input v-model="paw.paw2"  type="password"  autocomplete="off"
                          placeholder="请再次输入新密码"  ></el-input>
        </el-form-item>
            <el-button type="primary" @click="updPaw " >确定</el-button>
      </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "AppraiseMain",
  data() {
    const pawRule1 = async(rule, value, callback) => {
      if (value.length < 6) {
        this.paw1Check = 1
        return callback(new Error('密码不能少于6位！'));
      } else if (value.length > 16) {
        this.paw1Check = 1
        return callback(new Error('密码最长不能超过16位！'));
      } else {
        this.paw1Check = 0
        callback()
      }
    }
    const pawRule2 = async(rule, value, callback) => {
      if (value.length < 1) {
        this.paw2Check = 1
        return callback(new Error('重复密码不能为空！'));
      } else if(this.paw.paw1 !== this.paw.paw2){
        this.paw2Check = 1
        return callback(new Error('两次输入密码不一致！'));
      }else{
        this.paw2Check = 0
        callback()
      }
    };
    return {
      logIn : false,
      activeIndex: '1',
      visible : false ,
      id : "",
      name : "",
      pid : "",
      position : "",
      paw : {
        paw1 :"",
        paw2 :""
      },
      paw1Check : 0,
      paw2Check : 1,
      ruleValidate: {
        paw1: [
          { required: true, validator: pawRule1, trigger: 'blur' }
        ],
        paw2: [
          { required: true, validator: pawRule2, trigger: 'blur' }
        ]
      }
    };
  },
  mounted() {
    this.getLogIn()
    this.activeIndex = this.$route.path
  },
  methods: {
    getLogIn() {
      let i = JSON.parse(sessionStorage.getItem("appraise"));
      if (i == null) {
        this.$message.error("请登录！");
        setTimeout(function () { window.location.href = "/" }, 1000)
      } else
      {
        this.logIn = true;
        this.id = i.id;
        this.name = i.name;
        this.pid = i.pid;
        if (i.position != null){
          this.position = i.position;
        }
        if (i.pawState === 0){
          this.visible = true
        }
      }
    },
    toPage(path) {
      this.$router.replace(path);
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    updPaw(){
      if (this.paw1Check ===0 && this.paw2Check ===0 ) {
        this.$axios
          .post(this.$baseUrl + "user/paw", {
            "id": this.id,
            "paw": this.paw.paw2
          })
          .then(res => {
            if (res.data.code === 0) {
              this.$message("密码修改成功");
              this.visible = false
            }
          })
          .catch(res => console.log(res))

      }else {
        this.$message.error("密码输入有误")
        return 0;
      }
    },

  }
}

</script>

<style scoped>

</style>
