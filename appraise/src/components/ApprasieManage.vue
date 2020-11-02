<template>
  <div>
    <el-row style="margin:0 auto;text-align: center; align-content: center">
      <el-col :span="24" style="line-height: 50px;font-size: 16px;font-weight: bold;color: #666;text-align: center ">
        中国能建评价管理
      </el-col>
    </el-row>
    <el-row style="text-align: right">
      <el-button type="primary" style="height: 40px;text-align: center; margin-right: 13%" @click="appraiseVisible = true" >Excel导入</el-button>
      <el-button type="primary" style="height: 40px;text-align: center; " @click="getRange" >评价取值范围管理</el-button>
    </el-row>
    <el-row style="text-align: left;height: 100%;padding-left: 10%">
      <el-tabs tab-position="left" v-model="value" @tab-click="getTec(value)" >
        <el-tab-pane
          v-for="(item, index) in department"
          :key="item.id"
          :label="item.name"
          :name="item.id.toString()" >
          <el-tabs   v-model="tecValue" @tab-click="getGrade(tecValue)">
            <el-tab-pane
              v-for="tec in technology"
              :key="tec.id "
              :label="tec.name"
              :name="tec.id.toString()">
                  <template>
                    <el-table
                      :data="grade"
                      style="width: 100%"
                      height="330"
                    @expand-change="getScore">
                      <el-table-column type="expand">
                        <template slot-scope="props">
                          <el-form label-position="left" inline class="demo-table-expand">
                            <el-table
                              :data="score"
                              style="width: 100%"
                              height="200foreach"
                              :show-header="false">
                              <el-table-column
                                prop="username">
                              </el-table-column>
                              <el-table-column
                                prop="name">
                              </el-table-column>
                              <el-table-column>
                              <template slot-scope="scope">
                                <el-button
                                  @click="scoreDel(scope.row)"
                                  type="text"
                                  size="small">
                                  <i class="el-icon-close"></i>
                                </el-button>
                              </template>
                              </el-table-column>
                            </el-table>
                          </el-form>
                        </template>
                      </el-table-column>
                      <el-table-column
                        label="工号"
                        prop="username">
                      </el-table-column>
                      <el-table-column
                        label="姓名"
                        prop="name">
                      </el-table-column>
                      <el-table-column>
                        <template slot-scope="scope">
                          <el-button
                            @click="getUser(scope.row.id)"
                            type="text"
                            size="small">
                            新增
                          </el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </template>
            </el-tab-pane>
          </el-tabs>
        </el-tab-pane>
      </el-tabs>
    </el-row>

    <el-dialog
      title="评价管理"
      :visible.sync="visible"
      width="60%">
        <el-form id="user">
          <el-row>
            <el-col :span="8" >姓名<el-input v-model="search1" style="width: 50%" ></el-input></el-col>
            <el-col :span="8" >工号<el-input v-model="search2" style="width: 50%" ></el-input></el-col>
            <el-col :span="8" style="text-align: right;">
              <el-button type="primary" @click="search()">查询</el-button>
              <el-button @click="reset()">重置</el-button>
            </el-col>
          </el-row >
        <el-row style="width: 100%;text-align: center;">
        <template>
          <el-table
            ref="multipleTable"
            :data="user"
            tooltip-effect="dark"
            style="width: 90%;left: 5%"
            @selection-change="handleSelectionChange">
            <el-table-column
              type="selection"
              width="55"
            >
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
            >
            </el-table-column>
            <el-table-column
              prop="username"
              label="工号"
              >
            </el-table-column>
            <el-table-column
              prop="technology"
              label="专业"
              >
            </el-table-column>
            <el-table-column
              prop="department"
              label="部门"
              >
            </el-table-column>
          </el-table>
          <el-row style="padding: 5px 0;text-align: right;">
            <el-pagination @current-change="changeIndex" @size-change="changeSize" :current-page="pageIndex"
                           :page-sizes="[20,50,100]"
                           layout="total, prev, sizes, pager, next, jumper" :total="totalSize" :page-size="pageSize">
            </el-pagination>
          </el-row>
          <div style="margin-top: 20px">
            <el-row style="margin-top: 10px">
              <el-button @click="toggleSelection">取消选择</el-button>
              <el-button @click="visible = false">取 消</el-button>
              <el-button type="primary" @click="scoreAdd">确 定</el-button>
            </el-row>
          </div>
        </template>
        </el-row>
      </el-form>
    </el-dialog>
    <el-dialog
      title="评价管理"
      :visible.sync="rangeVisible"
      width="60%">
      <el-form id="range">
        <el-row>
          <el-col :span="8" >最小值<el-input v-model="min" style="width: 50%" ></el-input></el-col>
          <el-col :span="8" >最大值<el-input v-model="max" style="width: 50%" ></el-input></el-col>
          <el-col :span="8" style="text-align: right;">
            <el-button type="primary" @click="rangeUpd">确定</el-button>
            <el-button @click="rangeReset">重置</el-button>
          </el-col>
        </el-row >
      </el-form>
    </el-dialog>
    <el-dialog title="Excel导入" :visible.sync="appraiseVisible" width="500px" style="padding:0;" @close="appraiseVisible = false">
      <el-row>
        <el-col :span="14">
          <span>打分人与被打分人上传</span>
      <el-upload
        style="margin-top: 10px"
        class="upload-demo"
        ref="upload"
        action="1111"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        multiple
        :limit="2"
        :auto-upload="false"
        :on-change="getFile">
        <el-button slot="trigger" size="small" type="primary">点击上传</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        <div slot="tip" class="el-upload__tip">请同时上传打分人和被打分人文件，且被打分文件名称中包含“被打分”，打分人名称中不包含</div>
      </el-upload>
        </el-col>
        <el-col :span="10">
          <span>设总被打分人上传</span>
      <el-upload
        style="margin-top: 10px"
        class="upload-demo"
        ref="upload"
        :action="this.$baseUrl + 'excel/amongExcel'"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList">
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传excel文件</div>
      </el-upload>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
export default {
  inject:['reload'],
  name: "Manage",
  data() {
    return {
      id: "",
      month: new Date().getMonth() + 1,
      department: [],
      technology: [],
      grade: [],
      score: [],
      visible : false,
      appraiseVisible : false,
      value:"",
      search1: "",
      search2: "",
      tecValue: "",
      graValue: "",
      pageIndex: 1,
      pageSize: 20,
      totalSize : 0,
      list: [],
      user : [],
      multipleSelection: [],
      addAppraise: [],
      range:{},
      rangeVisible : false,
      min: "",
      max: "",
      fileList : [],
      files : [],

    }
  },
  mounted() {
    this.getLogIn();
    // this.getData();
  },
  methods: {
    search() {
      this.getUser();
    },
    reset() {
      this.search1 = "";
      this.search2 = "";
      this.pageIndex = 1;
      this.getUser();
    },
    getLogIn() {
      let i = JSON.parse(sessionStorage.getItem("appraise"));
      this.id = i.id;
      this.getData();
    },
    getData() {
      this.$axios
        .post(this.$baseUrl + 'department/query', {},)
        .then(res => (this.department = res.data.data))
        .catch(res => (console.log(res)));
      this.$axios
        .post(this.$baseUrl + 'userScore/queryScore', {}, {headers: {'id': this.id}})
        .then(res => (this.list = res.data.data))
        .catch(res => (console.log(res)));
    },
    getTec(v) {
      this.$axios
        .post(this.$baseUrl + 'technology/query', {}, {headers: {"id": v}})
        .then(res => (this.technology = res.data.data))
        .catch(res => (console.log(res)));
    },
    getGrade(v) {
      this.$axios
        .post(this.$baseUrl + 'user/queryByTid', {}, {headers: {"id": v}})
        .then(res => (this.grade = res.data.data))
        .catch(res => (console.log(res)));
    },
    getScore(v) {
      this.$axios
        .post(this.$baseUrl + 'userScore/selectByGradeId', {
          "id" : v.id
        })
        .then(res => (this.score = res.data.data))
        .catch(res => (console.log(res)));
    },
    getUser(v) {
      this.$axios
        .post(this.$baseUrl + 'user/queryNotScore', {
          "id": v,
          "name": this.search1,
          "username": this.search2,
          "pageIndex" : this.pageIndex,
          "pageSize" : this.pageSize
        })
        .then(res => {this.user = res.data.data.list,
          this.totalSize = res.data.data.total
        })
        .catch(res => (console.log(res)));
      this.graValue = v;
      this.visible = true
    },
    scoreAdd() {
      this.addAppraise = [];
      this.setAddAppraise();
      this.$axios
        .post(this.$baseUrl + 'gradeScore/add', this.addAppraise)
        .then()
        .catch(res => (console.log(res)));
      alert("添加成功");
      this.reload();
    },
    scoreDel(v) {
      this.$axios
        .post(this.$baseUrl + 'gradeScore/del', {
          "gradeId": v.gradeId,
          "scoreId": v.scoreId
        })
        .then()
        .catch(res => (console.log(res)));
      alert("删除成功");
      this.reload();
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    toggleSelection() {
        this.$refs.multipleTable.clearSelection();
    },
    setAddAppraise(){
      for (var i=0; i<this.multipleSelection.length; i++){
        var  add = {gradeId:"" , scoreId : ""}
        add.gradeId = this.graValue;
        add.scoreId = this.multipleSelection[i].id;
        this.addAppraise.push(add)
      }
    },
    getRange(v) {
      this.$axios
        .post(this.$baseUrl + 'range/query')
        .then(res => (this.min = res.data.data.min,
        this.max = res.data.data.max))
        .catch(res => (console.log(res)));
      this.graValue = v;
      this.rangeVisible = true
    },
    rangeReset(){
      this.$axios
        .post(this.$baseUrl + 'range/query')
        .then(res => (this.min = res.data.data.min,
          this.max = res.data.data.max))
        .catch(res => (console.log(res)));
    },
    rangeUpd(){
      this.$axios
        .post(this.$baseUrl + 'range/update',{
          "id" : 1,
          "min" : this.min,
          "max" : this.max
        })
        .then(res => (this.min = res.data.data.min,
          this.max = res.data.data.max))
        .catch(res => (console.log(res)));
      this.$message("修改成功");
      this.rangeVisible = false;
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    getFile(file , fl) {
        var excelName = file.name;
        var idx = excelName.lastIndexOf(".");
        if (idx !== -1){
          var ext = excelName.substr(idx+1).toUpperCase();
          ext = ext.toLowerCase( );
          if (ext!== 'xlsx'){

          }else {
            this.files.push(file)
          }
        }
    },
    submitUpload() {
      if (0 === this.files.length) {
        this.$message({
          type: 'info',
          message: '请选择要上传的文件'
        });
        return;
      }
      var formData = new FormData();
      // for (var i = 0 ; i < this.files.length ; i++){
      //   formData.append("file" , this.files[i].row)
      // }
      this.files.forEach((file) => {
        formData.append('file', file.raw)
      })
      this
        .$axios
        .post(this.$baseUrl + 'excel/personalExcel',formData,{headers: {"Content-Type": "multipart/form-data"}})
        .then(res => {
          if(res.data.code === 0){
            this.$message("上传成功")
          }else {
           this.$message.error("上传失败，请重试")
          }

      }
    )
    },
    changeIndex(v) {
      this.setAddAppraise();
      this.pageIndex = v;
      this.getData();
    },
    changeSize(v) {
      this.setAddAppraise();
      this.pageSize = v;
      this.getData();
    },

    }


  }
</script>

<style scoped>

</style>
