<template>
    <div>
      <el-dialog
        title="收货地址"
        :visible.sync="dialogFormVisible"
        :modal="modal"
        :append-to-body='true'
      >
        <el-form :model="form">
          <el-form-item label="项目名称" :label-width="formLabelWidth">
            <el-input v-model="form.projectName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="项目描述" :label-width="formLabelWidth">
            <el-input
              type="textarea"
              :rows="3"
              placeholder="请输入项目内容"
              v-model="form.description">
            </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
      <el-input
        placeholder="请输入要搜索的关键字"
        v-model="keyWord">
        <el-select v-model="select" slot="prepend" placeholder="请选择">
          <el-option label="餐厅名" value="1"></el-option>
          <el-option label="订单号" value="2"></el-option>
          <el-option label="用户电话" value="3"></el-option>
        </el-select>
        <el-button slot="append" icon="el-icon-search">搜索</el-button>
        <el-button slot="append" icon="el-icon-search" @click="dialogFormVisible = true">新建</el-button>
      </el-input>
      <div
        class="infinite-list"
        v-infinite-scroll="loadProjectList"
      >
        <el-card
          class="box-card"
          v-for="project of projectList"
          :key="project.id"
        >
          <div slot="header" class="clearfix">
            <span>{{project.projectName}}</span>
            <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
          </div>
          <div>
            {{project.description}}
          </div>
        </el-card>
      </div>
    </div>
</template>

<script>
    export default {
      data() {
        return {
          projectList: [
            {
              id:"1",
              projectName: "个人项目",
              description: "这是一个个人练手项目对应的文档"
            },
            {
              id: "2",
              projectName: "技术总结",
              description: "部门技术总结"
            }
          ],
          keyWord: "",
          searchType: "",
          select: "",
          dialogFormVisible: false,
          formLabelWidth: '120px',
          form: {
            projectName: '',
            description: ""
          },
          modal: true,
        };
      },
      methods: {
        loadProjectList: function () {
          let temp = this.projectList[this.projectList.length - 1];
          temp.id = Number(temp.id) + 1;
          this.projectList.push(temp);
        }
      }
    }
</script>

<style scoped>
  .box-card {
    margin: 5px;
  }
  /deep/ .el-select>.el-input {
    display: block;
    min-width: 150px;
  }
</style>
