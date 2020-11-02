<template id="main" style="margin: 0 auto;width: 100%">
  <div>
    <el-form :model="form">
      <el-row>
        <!-- 列表 -->
        <el-row style="background-color: #FFF; text-align: center">
          <el-row>
            <el-row style="margin:0 auto;text-align: center; align-content: center">
              <el-col :span="24" style="line-height: 50px;font-size: 16px;font-weight: bold;color: #666;text-align: center ">
                中国能建{{month}}月个人评分表
              </el-col>
            </el-row>
            <el-row style="margin:0 auto;text-align: center; align-content: center">
              <el-col :span="24" style="line-height: 10px;font-size: 12px;font-weight: bold;color: #666;text-align: center ">
                本月取值范围为{{min}}~{{max}}
              </el-col>
            </el-row>
            <el-table border :data="list" @filter-change="filterMethod" style="margin-top:20px;width: 80% ;horiz-align: center; left: 10%" >

              <el-table-column prop="name" label="姓名">
              </el-table-column>
              <el-table-column prop="department" label="部门"
                               column-key="department"
                               :filters="departmentList">
              </el-table-column>
              <el-table-column prop="technology" label="专业"
                               column-key="technology"
                               :filters="technologyList">
              </el-table-column>
              <el-table-column prop="scope" label="质量得分" >
                <template slot-scope="scope">
                  <el-input
                            type="text" :ref = "'designer' + scope.$index" v-model="scope.row.designer"
                            oninput="value=value.replace(/[^\d.]/g,'')"
                            :readonly="Boolean(scope.row.state)"
                            @blur="judge(scope.row.designer,'designer',scope.$index)"
                            @dblclick.native="changeState(scope.$index)"></el-input>
                </template>
              </el-table-column>

              <el-table-column prop="scope" label="进度得分">
                <template slot-scope="scope">
                  <el-input :readonly="Boolean(scope.row.state)"
                            type="text" :ref = "'personal'+scope.$index"
                            oninput="value=value.replace(/[^\d.]/g,'')" v-model="scope.row.personal"
                             @blur="judge(scope.row.personal,'personal',scope.$index)"
                            @dblclick.native="changeState(scope.$index)"></el-input>
                </template>
              </el-table-column>

              <el-table-column prop="scope" label="配合得分" >
                <template slot-scope="scope">
                  <el-input :readonly="Boolean(scope.row.state)"
                            type="text" :ref = "'coordinate'+scope.$index"
                            oninput="value=value.replace(/[^\d.]/g,'')" v-model="scope.row.coordinate"
                            @blur="judge(scope.row.coordinate,'coordinate',scope.$index)"
                            @dblclick.native="changeState(scope.$index)"></el-input>
                </template>
              </el-table-column>

            </el-table>
            <el-row style="padding: 5px 0;text-align: right;">
              <el-pagination @current-change="changeIndex" @size-change="changeSize" :current-page="pageIndex"
                             :page-sizes="[20,50,100]"
                             layout="total, prev, sizes, pager, next, jumper" :total="totalSize" :page-size="pageSize">
              </el-pagination>
            </el-row>
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
name: "Appraise",
  data() {
    return {
      id: "",
      pid: "",
      month: new Date().getMonth() + 1,
      search1: "",
      search2: "",
      min: "",
      max: "",
      isRouterAlive : true,
      list: [],
      form: {},
      appraiseData: {},
      pageIndex: 1,
      pageSize: 20,
      totalSize: 0,
      listData: [],
      tableData:[],
      technologyList:[],
      departmentList:[],
      queryByd:null,
      queryByt:null
    }
  },
  provide(){
    return{
      reload:this.reload
    }
  },
  mounted() {
    this.getLogIn();
    // this.getData();
    // this.getOtherData();
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
      this.pid = i.pid;
      this.getData();
      this.getOtherData();
    },
    getData() {
      this.$axios
        .post(this.$baseUrl + 'userScore/queryByGradeId',{
          "id" : this.id ,
          "pageIndex" : this.pageIndex,
          "pageSize" : this.pageSize,
          "queryByd": this.queryByd,
          "queryByt": this.queryByt}
        )
        .then(res => {this.list = res.data.data.list,this.totalSize = res.data.data.total})
        .catch(res => (console.log(res)));
    },
    getOtherData(){
      this.$axios
        .post(this.$baseUrl + 'range/query',{},{headers: {'id': this.id}})
        .then(res => {this.max = res.data.data.max ;
          this.min = res.data.data.min})
        .catch(res => (console.log(res)));
      this.$axios
        .post(this.$baseUrl + 'gradeScore/queryTec',{},{headers: {'id': this.id}})
        .then(res => (this.technologyList = res.data.data ))
        .catch(res => (console.log(res)));
      this.$axios
        .post(this.$baseUrl + 'gradeScore/queryDep',{},{headers: {'id': this.id}})
        .then(res => (this.departmentList = res.data.data ))
        .catch(res => (console.log(res)));
    },
    appraise() {
      for (var i = 0; i < this.list.length; i++) {
        if (this.list[i].designer == null || this.list[i].personal == null || this.list[i].coordinate == null) {
          if (this.list[i].designer == null && this.list[i].personal == null && this.list[i].coordinate == null) {
          } else {
            alert("评价应要么三项全评价，要么全不评价");
            return;
          }
        }
      }
      let e = 0;
      this.tableData.forEach((data)=>{
        if (data.pageIndex === this.pageIndex){
          data.list = this.list;
          e = 1;
          return 0;
        }
      })
      if (e === 0){
        let tData = {pageIndex:this.pageIndex , list:this.list}
        this.tableData.push(tData)
      }
      this.tableData.forEach(data =>{
        data.list.forEach(table =>{
          if (table.designer !=null && table.personal != null && table.coordinate!= null){
            let userScore = {
              gsId: table.id,
              designer: table.designer,
              personal: table.personal,
              coordinate: table.coordinate
            };
            this.listData.push(userScore);
          }
        })
      })
      this.$axios
        .post(this.$baseUrl + 'userScore/appraise', this.listData)
        .then(res => {
          if (0 === res.data.code) {
            this.getData();
            this.reload();
              alert("操作成功")
            this.tableData = [];
            this.getData();
          } else {
            alert(("操作失败，请重试"))
          }
        })
        .catch(res => (console.log(res)));
    },

    judge(v,w,n) {
      if ( v !== null && v !== '') {
        if (v < this.min || v > this.max) {
          this.$refs[w + n].focus();
          this.$message.error("输入的值不在范围内，请重新输入");
        }
      }

    },
    reload(){
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    },
    changeIndex(v) {
      for (let i = 0; i < this.list.length; i++) {
        if (this.list[i].designer == null || this.list[i].personal == null || this.list[i].coordinate == null) {
          if (this.list[i].designer == null && this.list[i].personal == null && this.list[i].coordinate == null) {
          } else {
            alert("评价应要么三项全评价，要么全不评价");
            return;
          }
        }
      }
      if (this.tableData.length === 0){
        let tData = {pageIndex:this.pageIndex , list:this.list};
        this.tableData.push(tData);
        this.pageIndex = v;
        this.getData();
      }else {
        let e = 0;
      this.tableData.forEach((data) =>{
        if(data.pageIndex === v){
          this.tableData.forEach((data1) =>{
            if (this.pageIndex === data1.pageIndex){
              data1.list = this.list
            }
          })
          this.list = data.list;
          this.pageIndex = v;
          e = 1;
          return 0;
        }
      })
        if (e === 0) {
          let tData = {pageIndex: this.pageIndex, list: this.list}
          this.tableData.push(tData);
          this.pageIndex = v;
          this.getData();
        }
      }
    },
    changeSize(v) {
      this.pageSize = v;
      this.getData();
    },
    changeState(v){
      this.list[v].state = false;
    },
    filterMethod(filter){
      for (let obj in filter){
        if (obj === "technology") {
          this.queryByd = null;
          this.queryByt = filter.technology
        }
        else {
          this.queryByt = null;
          this.queryByd = filter.department
        }
      }
      this.getData()
    },

  }


}
</script>

<style scoped>

</style>
