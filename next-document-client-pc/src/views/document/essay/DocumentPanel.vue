<template>
    <div id="main-panel" style="height: 100%">
      <el-container>
        <el-aside
          id="layout-aside"
        >
          <el-tree
            :props="props"
            :load="loadNode"
            lazy
            @node-click="getDocumentDetail"
            >
            <!--自定义树形组件节点内容-->
              <span class="custom-tree-node" slot-scope="{ node, data }">
                <i v-if="!data.leaf" class="el-icon-folder"></i>
                <i v-if="data.leaf" class="el-icon-tickets"></i>
                <span>{{ node.label }}</span>
                <span>
                  <el-button
                    type="text"
                    size="mini"
                    @click="() => append(data)">
                    重命名
                  </el-button>
                </span>
                <span v-if="!data.leaf">
                  <el-button
                    type="text"
                    size="mini"
                    @click="() => append(data)">
                    新建文档
                  </el-button>
                </span>
              </span>
          </el-tree>
        </el-aside>
        <el-container direction="vertical">
          <el-header>
            <div v-if="doc.id" id="document-header">
              <div id="document-header-info">
               文档由{{doc.authorName}}创建于{{doc.formatCreatedOn}}, 由{{doc.modifyByName}}修改于{{doc.formatUpdatedOn}}
              </div>
              <div id="document-header-right">
                赞同: 5 ; 反对: 1
              </div>
            </div>
          </el-header>
          <el-main v-if="doc.id" id="document-editor">
            <mavon-editor
              v-if="preview"
              id="md-editor-preview"
              v-model="doc.content"
              :toolbars="{}"
              :subfield="false"
              :boxShadow="false"
              defaultOpen="preview"
            >
              <template slot="left-toolbar-before">
                <button type="button"
                        class="el-icon-edit-outline"
                        aria-hidden="true"
                        :title="`编辑`"
                        @click="togglePreview"
                ></button>
              </template>
            </mavon-editor>
            <mavon-editor
              v-if="!preview"
              id="md-editor"
              v-model="doc.content"
              @save="save"
            >
              <template slot="left-toolbar-after">
                <button type="button"
                        class="el-icon-view"
                        aria-hidden="true"
                        :title="`保存并预览`"
                        @click="saveAndPreview"
                ></button>
              </template>
            </mavon-editor>
          </el-main>
        </el-container>
      </el-container>
    </div>
</template>

<script>
  import {successMsg} from "../../../util/notify";
  import {formatDate} from "../../../util/common";

  export default {
    data() {
      return {
        doc: {
          id: '',
          createdOn: '',
          formatCreatedOn: '',
          updatedOn: '',
          formatUpdatedOn: '',
          docName: '',
          content: '',
          authorName: '',
          modifyByName: ''
        },
        preview: true,
        props: {
          label: 'name',
          isLeaf: 'leaf',
          id: 'id',
        },
        projectId: ''
      }
    },
    mounted() {
      
    },
    methods: {
      loadNode(node, resolve) {
        console.log("加载时传入节点内容", node);
        let url = '/document/categories/' + this.projectId;
        if (node.level === 0) {
          url = url + '/0';
        } else {
          url = url + '/' + node.data.id;
        }
        this.$axios.get(url).then(res => {
          let data = res.data;
          let categories = data.data.categories;
          let documents = data.data.documents;
          let list = [];
          if (categories) {
            categories.forEach(ele => {
              list.push({
                id: ele.id,
                name: ele.category_name
              });
            })
          }
          if (documents) {
            documents.forEach(ele => {
              list.push({
                id: ele.id,
                name: ele.doc_name,
                leaf: true
              });
            });
          }
          return resolve(list);
        });
      },
      togglePreview: function () {
        this.preview = !this.preview;
      },
      save: function () {
        let docId = this.doc.id;
        let docName = this.doc.docName;
        let docContent = this.doc.content;
        this.$axios.patch('/document/' + docId, {
          doc_name: docName,
          doc_content: docContent
        }).then(res => {
          successMsg("您的文档已保存至服务器", "保存成功")
          this.preview = true;
          this.getDocumentDetailById(docId);
        });
      },
      saveAndPreview: function () {
        this.save();
        this.togglePreview();
      },
      getDocumentDetail: function (data) {
        if (!data.leaf) {
          return;
        }
        this.preview = true;
        this.getDocumentDetailById(data.id);
      },
      getDocumentDetailById: function (documentId) {
        this.$axios.get("/document/detail/" + documentId).then(res => {
          let doc = res.data.data;
          this.doc.id = doc.id;
          this.doc.createdOn = doc.created_on;
          this.doc.formatCreatedOn = formatDate(doc.created_on);
          this.doc.updatedOn = doc.updated_on;
          this.doc.formatUpdatedOn = formatDate(doc.updated_on);
          this.doc.docName = doc.doc_name;
          this.doc.content = doc.doc_content;
          this.doc.authorName = doc.author_name;
          this.doc.modifyByName = doc.modify_by_name;
        });
      },
      append: function (data) {
        console.log("传进来的数据", data);
      }
    },
    created() {
      let projectId = this.$route.query.project_id;
      this.projectId = projectId;
    }
  };
</script>

<style scoped>
  #main-panel, .el-container {
    padding: 0px;
    margin: 0px;
    height: 100%;
  }
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
  }
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  #md-editor, #md-editor-preview {
    height: 100%;
  }
  /*#layout-aside {*/
  /*  max-width: 250px;*/
  /*}*/
  #document-editor {
    padding: 2px;
  }
  #document-header {
    text-align: left;
  }
  #document-header-info {
    display: inline-block;
  }
  #document-header-right {
    display: inline-block;
    float: right;
  }
</style>
