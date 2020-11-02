<template>
  <div>
    <el-form :model="form">
      <el-row>
        <!-- 列表 -->
        <el-row style="background-color: #FFF; text-align: center">
          <el-row>
            <el-row style="margin:0 auto;text-align: center; align-content: center">
              <el-col :span="24" style="line-height: 50px;font-size: 16px;font-weight: bold;color: #666;text-align: center ">
                中国能建{{month}}月专业评分表
              </el-col>
            </el-row>
            <el-row style="margin:0 auto;text-align: center; align-content: center">
              <el-col :span="24" style="line-height: 10px;font-size: 12px;font-weight: bold;color: #666;text-align: center ">
                本月取值范围为{{min}}~{{max}}
              </el-col>
            </el-row>
            <el-table border :data="list" style="margin-top:20px;width: 80% ;horiz-align: center; left: 10%" >

              <el-table-column prop="technology" label="专业">
              </el-table-column>
              <el-table-column prop="department" label="部门">
              </el-table-column>
              <el-table-column prop="scope" label="质量得分">
                <template slot-scope="scope">
                  <el-input :readonly="Boolean(scope.row.state)"
                            type="text" :ref = "'designer' + scope.$index" v-model="scope.row.designer"
                            oninput="value=value.replace(/[^\d.]/g,'')"
                            @blur="judge(scope.row.designer,'designer',scope.$index)"
                            @dblclick.native="changeState(scope.$index)"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="scope" label="进度得分">
                <template slot-scope="scope">
                  <el-input :readonly="Boolean(scope.row.state)"
                            type="text" :ref = "'personal' + scope.$index" v-model="scope.row.personal"
                            oninput="value=value.replace(/[^\d.]/g,'')"
                            @blur="judge(scope.row.personal,'personal',scope.$index)"
                            @dblclick.native="changeState(scope.$index)"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="scope" label="配合得分">
                <template slot-scope="scope">
                  <el-input :readonly="Boolean(scope.row.state)"
                            type="text" :ref = "'coordinate' + scope.$index" v-model="scope.row.coordinate"
                            oninput="value=value.replace(/[^\d.]/g,'')"
                            @blur="judge(scope.row.coordinate,'coordinate',scope.$index)"
                            @dblclick.native="changeState(scope.$index)"></el-input>
                </template>
              </el-table-column>

            </el-table>
          </el-row>
        </el-row>
        <el-row style="padding: 20px 0 0 0;text-align: center;">
          <el-button type="primary" @click="appraise">确认评价</el-button>
        </el-row>
      </el-row>
    </el-form>
  </div>
</template>

<script>
export default {
name: "TecAppraise",
  data() {
    return {
      id: "",
      month: new Date().getMonth() + 1,
      search1: "",
      search2: "",
      search3: "",
      search4: "",
      search5: "",
      min: "",
      max: "",
      list: [],
      form: {},
      appraiseData: {}
    }
  },
  provide(){
    return{
      reload:this.reload
    }
  },
  date(){
    return{
      isRouterAlive : true
    }
  },
  mounted() {
    this.getLogIn();
    // this.getData();
  },
  methods: {
    update(e) {
      this.$forceUpdate(e);
    },
    search() {
      this.getData();
    },
    reset() {
      this.search1 = "";
      this.search2 = "";
      this.search3 = "";


      this.getData();
    },
    getLogIn() {
      let i = JSON.parse(sessionStorage.getItem("appraise"));
      this.id = i.id;
      this.getData();
    },
    getData() {
      this.$axios
        .post(this.$baseUrl + 'tecScore/queryByGradeId',{},{headers: {'id': this.id}})
        .then(res => (this.list = res.data.data))
        .catch(res => (console.log(res)));
      this.$axios
        .post(this.$baseUrl + 'range/query',{},{headers: {'id': this.id}})
        .then(res => (this.max = res.data.data.max , this.min = res.data.data.min))
        .catch(res => (console.log(res)));

    },
    appraise() {
      for (var i = this.list.length-1; i >= 0; i--) {
        if (this.list[i].designer == null || this.list[i].personal == null || this.list[i].coordinate == null) {
          if (this.list[i].designer == null && this.list[i].personal == null && this.list[i].coordinate == null) {
            this.list.splice(i ,1 )
          } else {
            alert("评价应要么三项全评价，要么全不评价");
            return;
          }
        }
      }
        this.$axios
          .post(this.$baseUrl + 'tecScore/appraise', this.list)
          .then(res => {
            if (0 == res.data.code) {
              alert("操作成功")
              this.getData()
            } else {
              alert(("操作失败，请重试"))
            }
          })
          .cache(res => (console.log(res)));
    },
    judge(v,w,n) {
      if (v<this.min || v>this.max) {

        // this.$nextTick(() => {
        // })
        this.$refs[w + n].focus();
        this.$message.error("输入的值不在范围内，请重新输入");
      }

    },
    reload(){
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    },
    changeState(v){
      this.list[v].state = false;
    }

  }

}
</script>

<style scoped>

</style>
