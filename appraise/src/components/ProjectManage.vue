<template>
  <div>
    <el-form :model="form">
      <el-row>
        <!-- 列表 -->
        <el-row style="background-color: #FFF; text-align: center">
          <el-row>
            <el-row style="margin:0 auto;text-align: center; align-content: center">
              <el-col :span="24" style="line-height: 50px;font-size: 16px;font-weight: bold;color: #666;text-align: center ">
                项目管理
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
name: "ProjectManage"
}
</script>

<style scoped>

</style>
