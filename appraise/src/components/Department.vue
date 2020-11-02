<template>
  <div>
    <el-row style="margin:0 auto;text-align: center; align-content: center">
      <el-col :span="24" style="line-height: 50px;font-size: 16px;font-weight: bold;color: #666;text-align: center ">
        中国能建部门管理
      </el-col>
    </el-row>
    <el-row style="text-align: left;height: 100%;padding-left: 10%">
      <el-tabs tab-position="left" v-model="value"
               type="card" editable @tab-click="getTec(value)"
      @tab-add="visible = true" @tab-remove="delDep(value)">
        <el-tab-pane
          v-for="(item, index) in department"
          :key="item.id"
          :label="item.name"
          :name="item.id.toString()" >
          <el-tabs tab-position="left"  v-model="tecValue"
                   type="card" editable  @tab-add="tecAdd(value)" @tab-remove="delTec(tecValue)">
            <el-tab-pane
              v-for="tec in technology"
              :key="tec.id"
              :label="tec.name"
              :name="tec.id.toString()">

            </el-tab-pane>
          </el-tabs>
        </el-tab-pane>
      </el-tabs>
    </el-row>
  <el-dialog
    title="部门新增"
    :visible.sync="visible"
    width="30%">
    <el-form id="addD">
      <el-row>
        <el-col >部门名称<el-input v-model="addD.name" style="width: 50%" ></el-input></el-col>
      </el-row>
    </el-form>
    <el-row style="margin-top: 10px">
      <el-button @click="visible = false">取 消</el-button>
      <el-button type="primary" @click="addDep">确 定</el-button>
    </el-row>
  </el-dialog>
    <el-dialog
      title="专业新增"
      :visible.sync="tecVisible"
      width="30%">
      <el-form id="addT">
        <el-row>
          <el-col >专业名称<el-input v-model="addT.name" style="width: 50%" ></el-input></el-col>
        </el-row>
      </el-form>
      <el-row style="margin-top: 10px">
        <el-button @click="tecVisible = false">取 消</el-button>
        <el-button type="primary" @click="addTec()">确 定</el-button>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
export default {
  inject:['reload'],
  name: "Department",
  data(){
    return{
      value:"",
      tecValue: "",
      department: [],
      technology: [],
      visible : false,
      tecVisible : false,
      addD: {},
      addT: {},
    }
  },
  mounted() {
    this.getLogIn();
  },
  methods: {
    search() {
      this.getData();
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
    },
    getTec(v){
      this.$axios
        .post(this.$baseUrl + 'technology/query', {},{headers:{"id" : v}})
        .then(res => (this.technology = res.data.data))
        .catch(res => (console.log(res)));
    },
    addDep(){
      this.$axios
        .post(this.$baseUrl + 'department/add', {
          name:this.addD.name
        })
        .then()
        .catch(res => (console.log(res)));
      this.reload();
    },
    delDep(v){
      this.$axios
        .post(this.$baseUrl + 'department/del', {},{headers:{"id" : v}})
        .then()
        .catch(res => (console.log(res)));
      this.reload();
    },
    addTec(){
      this.$axios
        .post(this.$baseUrl + 'technology/add', this.addT)
        .then()
        .catch(res => (console.log(res)));
      this.reload();
    },
    delTec(v){
      this.$axios
        .post(this.$baseUrl + 'technology/del', {},{headers:{"id" : v}})
        .then()
        .catch(res => (console.log(res)));
      this.reload();
    },
    tecAdd(v){
      this.tecVisible = true;
      this.addT.did = v;
    }
  }
}
</script>

<style scoped>

</style>
