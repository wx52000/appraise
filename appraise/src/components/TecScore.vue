<template>
  <div id="main" align="center">
    <el-row>
      <!-- 列表 -->
      <el-row style="margin: 0 20px 20px 20px;background-color: #FFF;">
        <el-row>
          <el-row style="margin:0 auto;text-align: center; align-content: center">
            <el-col :span="24" style="line-height: 50px;font-size: 16px;font-weight: bold;color: #666;text-align: center ">
              {{month}}月专业评价汇总表
              <i v-if="pid==1" class="el-icon-document-copy" @click="downExcel"></i>
            </el-col>
          </el-row>
          <el-table border :data="list" style="width:80%">

            <el-table-column prop="department" label="部门">
            </el-table-column>
            <el-table-column prop="technology" label="专业">
            </el-table-column>
            <el-table-column prop="score" label="平均评分">
            </el-table-column>


          </el-table>
        </el-row>
      </el-row>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "TecScore",
  data() {
    return {
      id: "",
      pid: "",
      month: new Date().getMonth() + 1,
      search1: "",
      search2: "",
      search3: "",
      search4: "",
      search5: "",
      pageIndex: 1,
      pageSize: 10,
      list: []

    }
  },
  mounted() {
    this.getLogIn();
    // this.getData();
  },
  methods: {
    search() {
      this.getData();
    },
    reset() {
      this.search1 = "";
      this.search2 = "";
      this.search3 = "";
      this.search4 = "";
      this.search5 = "";
      this.pageIndex = 1;
      this.getData();
    },
    getLogIn() {
      let i = JSON.parse(sessionStorage.getItem("appraise"));
      this.id = i.id;
      this.pid = i.pid
      this.getData();
    },
    getData() {
      this.$axios
        .post(this.$baseUrl + 'tecScore/queryScore', {},{headers: {'id': this.id}})
        .then(res => (this.list = res.data.data))
        .catch(res => (console.log(res)));
    },
    downExcel() {
      this.$message.success("即将开始下载");
      window.location.href = this.$baseUrl + 'tecScore/excel?id=' + this.id;
    },

  }

}
</script>

<style scoped>

</style>
