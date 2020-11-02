<template>
  <div>
    <el-form >
      <el-row>
        <!-- 列表 -->
        <el-row style="background-color: #FFF; text-align: center">
          <el-row>
            <el-row style="margin-left:-10px;text-align: center; align-content: center;">
              <el-col :span="24" style="line-height: 50px;font-size: 16px;font-weight: bold;color: #666;text-align: center ">
                项目管理
                <i class="el-icon-document-copy" @click="downExcel"></i>
              </el-col>
            </el-row>
            <el-row style="margin-left:-10px; align-content: center;">
              <el-col  align="right">
                <el-button style="margin-right: 50px"  type="primary" @click="handleAddPro">新增项目</el-button>
                <el-button style="margin-right: 50px"  type="primary" @click="visible = true">项目导入</el-button>
              </el-col>
            </el-row>

            <el-table border :data="list" class="el-table" :row-class-name="tableRowClassName"
                      :header-cell-style="{background:'#F5F5F5'}" >
              <el-table-column type="expand">
                <template  slot-scope="scope">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="创建人：">
                      <span>{{scope.row.principal}}</span>
                    </el-form-item>
                    <el-form-item label="设总：">
                      <span>{{scope.row.general}}</span>
                    </el-form-item>
                    <el-form-item label="主设人：">
                      <span>{{scope.row.principal}}</span>
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
                align="center"  min-width="8%" >
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
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
      <el-container>
        <el-header style="background: #B3C0D1 ;color: #333; text-align: left;height:40px;padding-top: 10px" >
          <el-row>
            <el-col :span="8">项目管理</el-col>
            <el-col :span="16" align="right">
                <el-button type="success" icon="el-icon-check" size="mini" circle @click="confirmProject"></el-button>

            </el-col>
          </el-row>
        </el-header>
        <el-form :model="project">
          <el-main>
            <el-form-item label="项目名称" style="width: 80%">
              <el-input type="text"  v-model="project.name" style="width: 80%"></el-input>
            </el-form-item>
            <el-row>
              <el-col :span="12">
                <el-form-item label="设总" style="text-align: left">
                  <el-select v-model="project.general" :filterable="true"  :remote="true" multiple
                             :remote-method="remoteMethod"
                             :loading="loading" placeholder="请选择">
                    <el-option
                      v-for="item in general"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="相关专业" style="text-align: left">
                  <el-cascader :props="props" :options="options" v-model="project.tec"
                               style="margin-left: 20px"  :filterable="true"></el-cascader>
                </el-form-item>
              </el-col>
            </el-row>
            <el-button type="primary" size="mini" @click="updProject" round style="margin-left: 80%">确 定</el-button>
          </el-main>
        </el-form>
        <el-header style="background: #B3C0D1 ;color: #333; text-align: left;height:40px;padding-top: 10px" >
          <el-row>
            <el-col :span="8">卷册管理</el-col>
            <el-col :span="16" align="right">
              <el-button type="primary" size="mini"   @click="handleAdd" plain>新增卷册</el-button>
            </el-col>
          </el-row>
        </el-header>
        <el-form :model="volume">
          <el-main>
                <el-form-item label="卷册号" style="width: 80%; ">
                  <el-input type="text"  v-model="volume.number" style="width: 80%;margin-left: 15px"></el-input>
                </el-form-item>
                <el-form-item label="卷册名称" style="width: 80%">
                  <el-input type="text"  v-model="volume.name" style="width: 80%"></el-input>
                </el-form-item>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="卷册等级" >
                      <el-input type="number" inline  v-model="volume.grade" style="width: 50%;margin-right: 26px"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="专业" >
                      <el-select v-model="volume.tec" placeholder="请选择" @change="getUserByTec" style="width: 50%;margin-left: 15px">
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
                      <el-select v-model="volume.designer" filterable  placeholder="请选择"  style="width: 50%;margin-left: -15px">
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
                      <el-select v-model="volume.checker"  filterable placeholder="请选择"  style="width: 50%">
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
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="计划出版时间">
                      <el-date-picker
                        v-model="volume.planned_publication_date"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%;margin-left:  -60px">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="实际出版时间">
                      <el-date-picker
                        v-model="volume.actual_publication_date"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%;margin-left:  -45px">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="接收对外专业资料时间" style="margin-left: -15px" >
                      <el-date-picker
                        v-model="volume.professional_date"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="对外提资时间">
                      <el-date-picker
                        v-model="volume.withdrawal_date"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%;margin-left:  -45px">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="出手日期" >
                      <el-date-picker
                        v-model="volume.shot_date"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%;margin-left:  -30px">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="完成日期">
                      <el-date-picker
                        v-model="volume.complete_time"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%;margin-left:  -15px">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-button type="primary" size="mini" @click="updVolume"  round style="margin-left: 80%">确 定</el-button>
          </el-main>
        </el-form>
      </el-container>
    </el-dialog>
    <el-dialog
      title="新增页面"
      :visible.sync="addVolume"
      width="60%" >
      <el-container>
        <el-header style="background: #B3C0D1 ;color: #333; text-align: left;height:40px;padding-top: 10px" >
          <el-row>
            <el-col :span="8">卷册新增</el-col>
          </el-row>
        </el-header>
        <el-form :model="newVolume" ref="newVolume">
          <el-main>
            <el-carousel height="500px" :autoplay=false>
              <el-carousel-item>
                <el-form-item label="卷册号" style="width: 80%; " prop="number">
                  <el-input type="text"  v-model="newVolume.number" style="width: 80%;margin-left: 15px"></el-input>
                </el-form-item>
                <el-form-item label="卷册名称" style="width: 80%">
                  <el-input type="text"  v-model="newVolume.name" style="width: 80%"></el-input>
                </el-form-item>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="卷册等级" prop="grade">
                      <el-input type="number" inline  v-model="newVolume.grade" style="width: 50%;margin-right: 26px"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="专业" >
                      <el-select v-model="newVolume.tec" placeholder="请选择"  style="width: 50%;margin-left: 15px">
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
                  <el-col :span="12" >
                    <el-form-item label="设计人" prop="designer">
                      <el-select v-model="newVolume.designer" filterable  placeholder="请选择"  style="width: 50%;margin-left: -15px">
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
                    <el-form-item label="互校人" prop="checker">
                      <el-select v-model="newVolume.checker"  filterable placeholder="请选择"  style="width: 50%">
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
                <el-row>
                  <el-col :span="12" >
                    <el-form-item label="计划出版时间" prop="planned_publication_date">
                      <el-date-picker
                        v-model="newVolume.planned_publication_date"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%;margin-left:  -60px">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="对外提资时间" prop="withdrawal_date">
                      <el-date-picker
                        v-model="newVolume.withdrawal_date"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%;margin-left:  -45px">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="接收对外专业资料时间" style="margin-left: -15px" prop="professional_date" >
                      <el-date-picker
                        v-model="newVolume.professional_date"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-button type="primary" size="mini" @click="addVolumeMethod"  round style="margin-left: 80%">确 定</el-button>
              </el-carousel-item>
            </el-carousel>
          </el-main>
        </el-form>
      </el-container>
    </el-dialog>
  <el-dialog title="Excel导入"
             :visible.sync="visible"
             width="40%"
             style="padding:0;" @close="visible = false">
    <el-row style="text-align: center">
      <el-col >
        <span>项目上传</span>
        <el-upload
          style="margin-top: 10px"
          class="upload-demo"
          ref="upload"
          :action="this.$baseUrl + 'excel/project'"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :file-list="fileList"
          :on-success="handle_success">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传excel文件</div>
        </el-upload>
      </el-col>
    </el-row>
  </el-dialog>
    <el-dialog
      title="新增"
      :visible.sync="addProject"
      width="60%">
      <el-container>
        <el-header style="background: #B3C0D1 ;color: #333; text-align: left;height:40px;padding-top: 10px" >
          <el-row>
            <el-col :span="8">项目新增</el-col>
          </el-row>
        </el-header>
        <el-form :model="newProject" ref="newProject">
          <el-main>
            <el-form-item label="项目名称" style="width: 80%">
              <el-input type="text"  v-model="newProject.name" style="width: 80%"></el-input>
            </el-form-item>
            <el-row>
              <el-col :span="12">
                <el-form-item label="设总" style="text-align: left">
                  <el-select v-model="newProject.general" :filterable="true"  :remote="true" multiple
                             :remote-method="remoteMethod"
                             :loading="loading" placeholder="请选择">
                    <el-option
                      v-for="item in general"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="相关专业" style="text-align: left">
                  <el-cascader :props="props" :options="options" v-model="newProject.tec"
                               style="margin-left: 20px"  :filterable="true" @change="addProjectTec"></el-cascader>
                </el-form-item>
              </el-col>
            </el-row>
          </el-main>
        <el-header style="background: #B3C0D1 ;color: #333; text-align: left;height:40px;padding-top: 10px" >
          <el-row>
            <el-col :span="8">卷册新增</el-col>
          </el-row>
        </el-header>
          <el-main>
            <div v-for="(item,index) in newProject.volumes" :key="index">
              <el-divider content-position="left">新增卷册{{index+1}}</el-divider>
                <el-form-item label="卷册号" :prop="'volumes.'+index+'.number'" style="width: 80%; ">
                  <el-input type="text"  v-model="item.number" style="width: 80%;margin-left: 15px"></el-input>
                </el-form-item>
                <el-form-item label="卷册名称" :prop="'volumes.'+index+'.name'" style="width: 80%">
                  <el-input type="text"  v-model="item.name" style="width: 80%"></el-input>
                </el-form-item>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="卷册等级" :prop="'volumes.'+index+'.grade'">
                      <el-input type="number" inline  v-model="item.grade" style="width: 50%;margin-right: 26px"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="专业" :prop="'volumes.'+index+'.tec'">
                      <el-select v-model="item.tec" placeholder="请选择"  style="width: 50%;margin-left: 15px"  @change="getUserByTec">
                        <el-option
                          v-for="item in tecList"
                          :key="item.id"
                          :label="item.name"
                          :value="item.id">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item label="设计人" :prop="'volumes.'+index+'.designer'" >
                      <el-select v-model="item.designer" filterable  placeholder="请选择"  style="width: 50%;margin-left: -15px">
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
                    <el-form-item label="互校人" :prop="'volumes.'+index+'.checker'">
                      <el-select v-model="item.checker"  filterable placeholder="请选择"  style="width: 50%">
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
              <el-row>
                <el-col :span="12">
                  <el-form-item label="计划出版时间" :prop="'volumes.'+index+'.plannedPublicationDate'">
                    <el-date-picker
                      v-model="item.plannedPublicationDate"
                      type="date"
                      format="yyyy-MM-dd"
                      value-format="timestamp"
                      placeholder="选择日期" style="width: 50%;margin-left:  -60px">
                    </el-date-picker>
                  </el-form-item>
                </el-col>
                  <el-col :span="12">
                    <el-form-item label="对外提资时间" :prop="'volumes.'+index+'.withdrawalDate'">
                      <el-date-picker
                        v-model="item.withdrawalDate"
                        type="date"
                        format="yyyy-MM-dd"
                        value-format="timestamp"
                        placeholder="选择日期" style="width: 50%;margin-left:  -45px">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="接收对外专业资料时间"  :prop="'volumes.'+index+'.professionalDate'" >
                    <el-date-picker
                      v-model="item.professionalDate"
                      type="date"
                      format="yyyy-MM-dd"
                      value-format="timestamp"
                      placeholder="选择日期" style="width: 50%">
                    </el-date-picker>
                  </el-form-item>
                </el-col>
                <el-button type="danger" v-if="index !==0" size="mini" icon="el-icon-delete" circle
                style="margin-left: 30px" @click="deleteItem"></el-button>
              </el-row>

            </div>
            <el-row>
              <el-button size="mini" @click="addItem">新增卷册</el-button>
              <el-button type="primary" size="mini" @click="addProjectMethod"  round  >确 定</el-button>
            </el-row>
          </el-main>
        </el-form>
      </el-container>
    </el-dialog>
    <el-dialog
      title="新增临时人员"
      :visible.sync="temporaryDialog"
      width="50%" style="text-align: center">
      <template>
        <el-table
          :data="temporaryPersonal"
          style="width: 100%">
          <el-table-column
            prop="name"
            label="姓名"
            min-width="40" >
          </el-table-column>
          <el-table-column
            prop="username"
            label="临时用户名"
          min-width="60%">
          </el-table-column>
        </el-table>
      </template>
      <el-button type="primary" @click="temporaryDialog = false"   >确 定</el-button>
  </el-dialog>
  </div>
</template>

<script>
export default {
  "name": "AdminProject",
  "data"() {
    return {
      id: "",
      pid: "",
      tid : "",
      list: [],
      project: {
      },
      volume: {},
      tecList: [],
      userList: [],
      listIndex: 0,
      meshRow: 0,
      dialogVisible: false,
      visible : false,
      options: [],
      fileList : [],
      props: {
        checkStrictly: true,
        multiple: true,
        value: 'id',
        label: 'name',
        children: 'list'
      },
      state: [
        {
          value: 0,
          label: "未完成"
        },
        {
          value: 1,
          label: "已完成"
        }
      ],
      general : [],
      loading : false,
      newVolume : {},
      addVolume : false,
      new_pid: null,
      newProject : {
        volumes : []
      },
      addProject : false,
      addTec : [],
      temporaryDialog : false,
      temporaryPersonal :[],
    }
  },
  mounted() {
    this.getLogIn();
  },
  methods: {
    "getLogIn"() {
      let i = JSON.parse(sessionStorage.getItem("appraise"));
      this.id = i.id;
      this.pid = i.pid;
      this.tid = i.tid;
      this.getData();
    },
    "getData"() {
      this.$axios
        .post(this.$baseUrl + 'project/queryByAdmin', {
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
        if (!isNaN(v)) {
          let date = new Date(Number(v));
          let year = date.getFullYear();
          let month = date.getMonth() + 1;
          let day = date.getDate();
          return year + '-' + month + '-' + day;
        }else
          return v;
      } else
        return "";
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
    confirmProject(){
      this.$confirm('确认将当前项目状态置于已完成', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(() =>{
        this.$axios
          .post(this.$baseUrl + 'project/updState', {}, {"headers": {'id': this.new_pid}}
          )
          .then(res => {
            if (res.data.code === 0){
              this.$message("操作成功")
            }
          })
          .catch(res => (console.log(res)));
      }).catch()
    },
    tableRowClassName({row, rowIndex}) {
      //把每一行的索引放进row
      row.index = rowIndex;
    },
    "handleEdit"(v, w) {
      this.new_pid = w.pid;
      this.$axios
        .post(this.$baseUrl + 'project/queryById', {}, {"headers": {'id': w.pid}}
        )
        .then(res => {
          this.project = res.data.data
        })
        .catch(res => (console.log(res)));
      this.$axios
        .post(this.$baseUrl + 'volume/queryById', {}, {"headers": {'id': w.vid}}
        )
        .then(res => {
          this.volume = res.data.data
        })
        .catch(res => (console.log(res)));
      this.dialogVisible = true;
      this.$axios
        .post(this.$baseUrl + 'technology/queryToSelected')
        .then(res => {
          this.options = res.data.data
        })
        .catch(res => (console.log(res)));
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
          "projectTec": projectTec,
          "general" : this.project.general,
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
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          this.$axios
            .post(this.$baseUrl + 'user/queryByName', {
              "name" : query
              }
            )
            .then(res => {
              if (res.data.data != null) {
                this.general = res.data.data
              }
          });
          this.loading = false;
        }, 200);
      } else {
        this.general = [];
      }
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
    handleAdd(){
      this.newVolume = {}
      this.addVolume = true;
    },
    addVolumeMethod(){
      this.addVisible = true;
      this.$axios
        .post(this.$baseUrl + 'volume/add',{
          "pid" : this.new_pid,
          "number" : this.newVolume.number,
          "name" : this.newVolume.name,
          "grade" : this.newVolume.grade,
          "tecId" : this.newVolume.tec,
          "plannedPublicationDate" : this.newVolume.planned_publication_date,
          "professionalDate" : this.newVolume.professional_date,
          "withdrawalDate" : this.newVolume.withdrawal_date,
          "designer" : this.newVolume.designer,
          "checker" : this.newVolume.checker,
          "creator" : this.id,
        })
        .then(res => {if(res.data.code ===0)
          this.$message("操作成功")
        else this.$message.error("操作失败，请重试")})
        .catch(res => (console.log(res)));
    },
    handleAddPro(){
      this.$axios
        .post(this.$baseUrl + 'technology/queryToSelected')
        .then(res => {
          this.options = res.data.data
        })
        .catch(res => (console.log(res)));
      this.newProject = {
        volumes: [{
          number: "",
          name: '',
          grade:'',
          tec:'' ,
          designer: '',
          checker: '',
          professionalDate : '',
          withdrawalDate : '',
          plannedPublicationDate : ''
          }
        ]
      }
      this.addProject = true;
    },
    addProjectMethod(){
      let projectUsers = [];
      let projectTec = [];
      for (let i = 0; i < this.newProject.tec.length; i++) {
        console.log(this.newProject.tec[i])
        projectTec.push(this.newProject.tec[i][0])
        projectUsers.push(this.newProject.tec[i][1])
      }
      console.log(this.newProject)
      this.$axios
        .post(this.$baseUrl + 'project/add',{

          "name" : this.newProject.name,
          "creator" : this.id,
          "creatorTime" : new Date().getTime(),
          "general" : this.newProject.general,
          "projectTec" : projectTec,
          "projectUsers" : projectUsers,
          "volumes" : this.newProject.volumes
        })
        .then(res => {if(res.data.code === 0)
          this.$message("操作成功")
        else this.$message.error("操作失败，请重试")})
        .catch(res => (console.log(res)));
    },
    addItem() {
        this.newProject.volumes.push({
        number : '',
        name: '',
        grade:'',
        tec:'' ,
        designer: '',
        checker: '',
        professionalDate : '',
        withdrawalDate : '',
        plannedPublicationDate : ''
      });
    },
    deleteItem (item, index) {
      this.newProject.volumes.splice(index, 1)
    },
    downExcel() {
      this.$message.success("即将开始下载");
      window.location.href = this.$baseUrl + 'project/projectExcel';
    },
    addProjectTec(v){
      for (let i = 0 ; i < v.length ; i++){
        this.addTec.push(v[i][0])
      }
      this.$axios
        .post(this.$baseUrl + 'technology/queryById',{
          projectTec : this.addTec
        })
        .then(res => {
          this.tecList = res.data.data;
          this.$forceUpdate();
        })
        .catch(res => (console.log(res)));

    },
    getUserByTec(v){
      this.$axios
        .post(this.$baseUrl + 'user/queryByTec', {}, {"headers": {'id': v}}
        )
        .then(res => {
          this.userList = res.data.data;
          this.$forceUpdate();
        })
        .catch(res => (console.log(res)));
    },
    handle_success(v){
      console.log(v);
      this.temporaryPersonal = v.data;
      this.$nextTick(function () {
        this.temporaryDialog = true;
      })
      console.log(this.temporaryPersonal+ "aaaa")
    }

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
  width: 100% ;
  horiz-align: center;
  left: 10%;
  font-size: 12px;
  word-wrap: break-word;
  margin-left: -10%;
}


</style>
