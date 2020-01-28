<template>
    <div>
      <el-dialog
        title="新建项目"
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
          <el-form-item label="公开项目" :label-width="formLabelWidth">
            <el-radio v-model="form.privateProject" label="1">公开</el-radio>
            <el-radio v-model="form.privateProject" label="2">私有</el-radio>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
      <el-input
        placeholder="请输入要搜索的关键字"
        v-model="keyWord" @change="search">
        <el-select v-model="select" slot="prepend" placeholder="请选择">
          <el-option label="项目id" value="1"></el-option>
          <el-option label="项目名" value="2"></el-option>
          <el-option label="项目描述" value="3"></el-option>
        </el-select>
        <el-button slot="append" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button slot="append" icon="el-icon-search" @click="dialogFormVisible = true">新建</el-button>
      </el-input>
      <div
        class="infinite-list"
        v-infinite-scroll="loadProjectList"
        :infinite-scroll-disabled="stopLoading"
      >
        <el-card
          class="box-card"
          v-for="project of projectList"
          :key="project.id"
        >
          <div slot="header" class="clearfix">
            <span>{{project.projectName}}</span>
            <el-button v-if="shortcut_index" style="float: right; padding: 3px 0" type="text" @click="addShortcut(project.id)">添加</el-button>
            <el-button v-if="!shortcut_index" style="float: right; padding: 3px 0" type="text" @click="gotoDocumentPanel(project.id)">文档</el-button>
          </div>
          <div>
            {{project.description}}
          </div>
        </el-card>
      </div>
      <p v-if="stopLoading">没有更多了</p>
    </div>
</template>

<script>
    import {successMsg} from "../../util/notify";

    export default {
      data() {
        return {
          projectList: [],
          keyWord: "",
          searchType: "",
          select: "2",
          dialogFormVisible: false,
          formLabelWidth: '120px',
          form: {
            projectName: '',
            description: "",
            privateProject: "1"
          },
          modal: true,
          stopLoading: false,
          page: 1,
          size: 10,
          shortcut_index: '', // 要添加快捷方式的下标
        };
      },
      methods: {
        loadProjectList: function () {
          let param = {
            page: this.page,
            size: this.size
          };
          if (this.select == 1) {
            // 项目id
            param.id = this.keyWord;
          }
          if (this.select == 2) {
            // 项目名
            param.project_name = this.keyWord;
          }
          if (this.select == 3) {
            // 项目描述
            param.description = this.keyWord;
          }
          this.$axios.get("/projects", {
            params: param
          }).then(res => {
            let data = res.data.data;
            let pageData = data.page_data;
            pageData.forEach(e => {
              let project = {
                id: e.id,
                projectName: e.project_name,
                description: e.description
              };
              this.projectList.push(project);
            });
            if (data.last_page) {
              this.stopLoading = true;
            } else {
              this.page = this.page + 1;
            }
          });
        },
        search: function () {
          this.projectList = [];
          this.page = 1;
          this.loadProjectList();
        },
        getIndexParam() {
          let index = this.$route.query.index;
          this.shortcut_index = index;
        },
        addShortcut: function (project_id) {
          let param = {
            project_id: project_id,
            shortcut_index: this.shortcut_index
          };
          this.$axios.post("/project_shortcut", param).then(res => {
            successMsg(res.data.msg);
            this.$router.push("/layout/project_panel");
          });
        },
        gotoDocumentPanel: function (projectId) {
          this.$router.push("/layout/document_panel?project_id=" + projectId);
        }
      },
      created() {
        this.getIndexParam();
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
