<template>
  <div>
    <el-form >
      <el-row>
        <!-- 列表 -->
        <el-row style="background-color: #FFF; text-align: center">
          <el-row>
            <el-row style="margin:0 auto;text-align: center; align-content: center">
              <el-col :span="24" style="line-height: 50px;font-size: 16px;font-weight: bold;color: #666;text-align: center ">
                项目管理
              </el-col>
            </el-row>
            <el-table border :data="list" class="el-table" :row-class-name="tableRowClassName"
                      :header-cell-style="{background:'#F5F5F5'}" >
              <el-table-column type="expand">
                <template  slot-scope="scope">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="设总：">
                      <span>{{scope.row.general}}</span>
                    </el-form-item>
                    <el-form-item label="互校人：">
                      <span>{{scope.row.checker}}</span>
                    </el-form-item>
                    <el-form-item label="计划出版时间：">
                      <span>{{timeConversion(scope.row.planned_publication_date)}}</span>
                    </el-form-item>
                    <el-form-item label="实际最终出版时间：">
                      <span>{{timeConversion(scope.row.actual_publication_date)}}</span>
                    </el-form-item>
                    <el-form-item label="接收外专业时间：">
                      <span>{{timeConversion(scope.row.professional_date)}}</span>
                    </el-form-item>
                    <el-form-item label="对外提资时间：">
                      <span>{{timeConversion(scope.row.withdrawal_date)}}</span>
                    </el-form-item>
                    <el-form-item label="出手日期：">
                      <span>{{timeConversion(scope.row.shot_date)}}</span>
                    </el-form-item>
                    <el-form-item label="完成日期：">
                      <span>{{timeConversion(scope.row.complete_time)}}</span>
                    </el-form-item>
                    <el-form-item label="组长完成时间：">
                      <span>{{timeConversion(scope.row.headman_date)}}</span>
                    </el-form-item>
                    <el-form-item label="互校人完成时间：">
                      <span>{{timeConversion(scope.row.checker_date)}}</span>
                    </el-form-item>
                    <el-form-item label="设计人本周完成比例：" >
                      <span>{{recordConversion(scope.row.designerList,0,0)}}%</span>
                    </el-form-item>
                    <el-form-item label="备注：" >
                      <span>{{recordConversion(scope.row.designerList,0,1)}}</span>
                    </el-form-item>
                    <el-form-item label="设计人上周完成比例：">
                      <span>{{recordConversion(scope.row.designerList,1,0)}}%</span>
                    </el-form-item>
                    <el-form-item label="备注：">
                      <span>{{recordConversion(scope.row.designerList,1,1)}}</span>
                    </el-form-item>
                    <el-form-item label="互校人本周完成比例：">
                      <span>{{recordConversion(scope.row.checkerList,0,0)}}%</span>
                    </el-form-item>
                    <el-form-item label="备注：">
                      <span>{{recordConversion(scope.row.checkerList,0,1)}}</span>
                    </el-form-item>
                    <el-form-item label="互校人上周完成比例：">
                      <span>{{recordConversion(scope.row.checkerList,1,0)}}%</span>
                    </el-form-item>
                    <el-form-item label="备注：">
                      <span>{{recordConversion(scope.row.checkerList,1,1)}}</span>
                    </el-form-item>
                    <el-form-item label="主设人本周完成比例：">
                      <span>{{recordConversion(scope.row.principalList,0,0)}}%</span>
                    </el-form-item>
                    <el-form-item label="备注：">
                      <span>{{recordConversion(scope.row.principalList,0,1)}}</span>
                    </el-form-item>
                    <el-form-item label="主设人上周完成比例：">
                      <span>{{recordConversion(scope.row.principalList,1,0)}}%</span>
                    </el-form-item>
                    <el-form-item label="备注：">
                      <span>{{recordConversion(scope.row.principalList,1,1)}}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column prop="projectName" min-width="28%" label="项目名称" align="center">
              </el-table-column>
              <el-table-column prop="number" min-width="24%" label="卷册号" align="center"  >
              </el-table-column>
              <el-table-column prop="volumeName" min-width="24" label="卷册名称" align="center" style="word-break: break-all;">
              </el-table-column>
              <el-table-column prop="tecName" min-width="8%" label="专业" align="center">
              </el-table-column>
              <el-table-column prop="grade"  min-width="10%" label="卷册等级"  align="center">
              </el-table-column>
              <el-table-column prop="designer" min-width="8%"  label="设计人" align="center">
              </el-table-column>
              <el-table-column
                align="center"  min-width="10%" style="text-align: center">
                <template slot-scope="scope" style="text-align: center">
                  <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                  <el-button
                    size="mini" style="margin-left: -2px"
                    @click="handleReport(scope.$index, scope.row)">Report</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-row>
        </el-row>
      </el-row>
    </el-form>
    <el-dialog
      title="操作页面"
      :visible.sync="dialogVisible"
      width="60%">
      <el-container>\
        <el-header style="background: #B3C0D1 ;color: #333; text-align: left;height:40px;padding-top: 10px" >
          <el-row>
            <el-col :span="8">卷册管理</el-col>
          </el-row>
        </el-header>
        <el-form :model="volume">
          <el-main>
                <el-form-item label="卷册号" style="width: 80%; ">
                  <el-input type="text" readonly v-model="volume.number" style="width: 80%;margin-left: 15px"></el-input>
                </el-form-item>
                <el-form-item label="卷册名称" style="width: 80%">
                  <el-input type="text" readonly v-model="volume.name" style="width: 80%"></el-input>
                </el-form-item>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="卷册等级" >
                      <el-input type="number" readonly inline  v-model="volume.grade" style="width: 50%;margin-right: 26px"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="专业" >
                      <el-select v-model="volume.tec" disabled placeholder="请选择"  style="width: 50%;margin-left: 15px">
                        <el-option
                          v-for="item in tecList"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="设计人" >
                      <el-select v-model="volume.designer" disabled filterable  placeholder="请选择"  style="width: 50%;margin-left: -15px">
                        <el-option
                          v-for="item in userList"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="互校人">
                      <el-select v-model="volume.checker"   filterable placeholder="请选择"  style="width: 50%">
                        <el-option
                          v-for="item in userList"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-button type="primary" size="mini" @click="updVolume"  round style="margin-left: 80%">确 定</el-button>
          </el-main>
        </el-form>
      </el-container>
    </el-dialog>
    <el-dialog
      id="report"
      title="每周汇报"
      :visible.sync="visible"
      width="40%">
      <div style="width:100%;text-align:center">
        <el-form id="paw" :model="proportion" :rules="ruleValidate" label-width="100px" >
          <el-row>
            <el-col>
            <span v-if="lastReport.state ===0 && getMonthByLong(lastReport.date) === theMonth">
              上周完成比例:{{lastReport.proportion}}%
            </span>
              <span v-if="lastReport.state ===0 && getMonthByLong(lastReport.date) !== theMonth">
              上月底完成比例:{{lastReport.proportion}}%
            </span></el-col>
          </el-row>
          <el-row>
            <el-form-item prop="report" label="完成比例" >
              <el-input pro v-model="proportion.report" type="number"  autocomplete="off"
                        placeholder="本周完成比例" max="100">
                <template slot="append">%</template>
              </el-input>
            </el-form-item>
          </el-row>
          <el-form-item prop="remark" label="备注" >
            <el-input v-model="proportion.remarks"  type="textarea"  autocomplete="off"
                      placeholder="存在问题、延期原因等" maxlength="100" show-word-limit></el-input>
          </el-form-item>
          <el-button type="primary" @click="reporting" v-if="lastReport.state === 0" >确定</el-button>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  "name": "HeadmanProject",
  "data"() {
    const report = async(rule, value, callback) => {
      var regPos = /^\d+(\.\d+)?$/;
      if (value === null || value === '') {
        this.paw1Check = 1
        return callback(new Error('完成比例不能为空'));
      } else if (!regPos.test(value) || value > 100 || value < 0) {
        this.paw1Check = 1
        return callback(new Error('完成比例必须为正数，且小于100大于0'));
      } else {
        this.paw1Check = 0
        callback()
      }
    }
    return {
      id: "",
      pid: "",
      list: [],
      project: {},
      volume: {},
      tecList: [],
      userList: [],
      listIndex: 0,
      meshRow: 0,
      dialogVisible: false,
      options: [],
      props: {
        multiple: true,
        value: 'id',
        label: 'name',
        children: 'list'
      },
      ruleValidate:{
        report: [
          { required: true, validator: report, trigger: 'blur' }
        ]
      },
      reportCheck : 0,
      lastReport : {},
      theMonth : new Date().getMonth() + 1,
      visible : false,
      proportion: {},
    }
  },
  "mounted"() {
    this.getLogIn();
  },
  "methods": {
    "getLogIn"() {
      let i = JSON.parse(sessionStorage.getItem("appraise"));
      this.id = i.id;
      this.pid = i.pid;
      this.getData();
    },
    "getData"() {
      this.$axios
        .post(this.$baseUrl + 'project/queryByHeadman', {
            "id": this.id
          }
        )
        .then(res => {
          this.list = res.data.data
        })
        .catch(res => (console.log(res)));
    },
    "timeConversion"(v) {
      if (v !== undefined && v !== "") {
        var date = new Date(Number(v))
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        return year + '-' + month + '-' + day;
      } else return "";
    },
    recordConversion(v,i,n) {
      if (v != null && v!== "") {
        let recordOne = v.split("##")
        if ( recordOne[i] != null && recordOne[i] != "") {
          return recordOne[i].split("$")[n]
        }else {
          return null
        }
      }
    },
    tableRowClassName({row, rowIndex}) {
      //把每一行的索引放进row
      row.index = rowIndex;
    },
    "handleEdit"(v, w) {

      this.$axios
        .post(this.$baseUrl + 'volume/queryById', {}, {"headers": {'id': w.vid}}
        )
        .then(res => {
          this.volume = res.data.data
        })
        .catch(res => (console.log(res)));
      this.dialogVisible = true;
      this.$axios
        .post(this.$baseUrl + 'projectTec/query', {}, {"headers": {'id': w.pid}}
        )
        .then(res => {
          this.tecList = res.data.data
        })
        .catch(res => (console.log(res)));
      this.$axios
        .post(this.$baseUrl + 'user/queryByTec', {}, {"headers": {'id': w.tid}}
        )
        .then(res => {
          this.userList = res.data.data
        })
        .catch(res => (console.log(res)));
    },

    "updProject"() {
      let projectUsers = [];
      let projectTec = [];
      console.log(this.project.tec.length)
      for (let i = 0; i < this.project.tec.length; i++) {
        console.log(this.project.tec[i])
        projectTec.push(this.project.tec[i][0])
        projectUsers.push(this.project.tec[i][1])
      }
      this.$axios
        .post(this.$baseUrl + 'project/upd', {
          "id": this.project.id,
          "name": this.project.name,
          "projectUsers": projectUsers,
          "projectTec": projectTec
        })
        .then(res => {
          if (res.data.code === 0)
            this.$message("操作成功")
          else this.$message.error("操作失败，请重试")
        })
        .catch(res => (console.log(res)));
    },
    "updVolume"() {
      let tec = this.volume.tec;
      if (typeof tec === "string") {
        tec = null
      }
      let designer = this.volume.designer;
      if (typeof designer === "string") {
        designer = null;
      }
      let checker = this.volume.checker;
      if (typeof checker === "string") {
        checker = null;
      }
      this.$axios
        .post(this.$baseUrl + 'volume/upd', {
          "id": this.volume.id,
          "number": this.volume.number,
          "name": this.volume.name,
          "grade": this.volume.grade,
          "tecId": tec,
          "plannedPublicationDate": this.volume.planned_publication_date,
          "actualPublicationDate": this.volume.actual_publication_date,
          "professionalDate": this.volume.professional_date,
          "withdrawalDate": this.volume.withdrawal_date,
          "shotDate": this.volume.shot_date,
          "completeDate": this.volume.complete_time,
          "designer": designer,
          "checker": checker
        })
        .then(res => {
          if (res.data.code === 0)
            this.$message("操作成功")
          else this.$message.error("操作失败，请重试")
        })
        .catch(res => (console.log(res)));
    },
getMonthByLong(v){
  if (v !== undefined && v !== "") {
    let date = new Date(v);
    return  date.getMonth()+1;
  }
},
getWeekByLong(v) {
  if (v !== undefined && v !== "") {
    let date = new Date(v);
    return date.get + 1;
  }
},
handleReport(v,w){
  this.proportion = {};
  this.reportVid = w.vid;
  this.$axios
    .post(this.$baseUrl + 'proportion/queryLastTime',{
        "volumeId" : w.vid,
        "userId" : this.id,
      }
    )
    .then(res => {this.lastReport = res.data.data
      if (this.lastReport.state === 1){
        this.proportion.report = this.lastReport.proportion;
        this.proportion.remarks = this.lastReport.remarks;
      }
    })
    .catch(res => (console.log(res)));
  this.$nextTick(() =>{
    this.$forceUpdate();
    this.visible = true;
  })
},
reporting(){
  if (this.reportCheck === 0){
    this.$axios
      .post(this.$baseUrl + 'proportion/add',{
          "volumeId" : this.reportVid,
          "userId" : this.id,
          "proportion" : this.proportion.report,
          "remarks" : this.proportion.remarks,
          "type" : 3,
        }
      )
      .then(res => {
        if (res.data.code === 0){
          this.$message("操作成功")
          this.visible = false;
        }
      })
      .catch(res => (console.log(res)));
  }
},
  }
}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 130px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.el-table {
  margin-top:20px;
  width: 80% ;
  horiz-align: center;
  left: 10%;
  font-size: 12px;
  word-wrap: break-word;
}


</style>
