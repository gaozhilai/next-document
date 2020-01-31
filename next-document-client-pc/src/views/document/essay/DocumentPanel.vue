<template>
  <div id="main-panel" style="height: 100%">
    <el-container>
      <el-aside
        id="layout-aside"
      >
        <div id="tree-top">
          <el-dropdown @command="dispatchCommand">
          <span class="el-dropdown-link">
            新建<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="folder">新建文件夹</el-dropdown-item>
              <el-dropdown-item command="document">新建文档</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <el-tree
          id="tree-list"
          :props="props"
          :load="loadNode"
          lazy
          ref="tree"
          @node-click="getDocumentDetail"
          @node-contextmenu="rightClickShowMenu"
          @node-drag-start="handleDragStart"
          @node-drag-enter="handleDragEnter"
          @node-drag-leave="handleDragLeave"
          @node-drag-over="handleDragOver"
          @node-drag-end="handleDragEnd"
          @node-drop="handleDrop"
          :allow-drop="allowDrop"
          :allow-drag="allowDrag"
        >
          <!--自定义树形组件节点内容-->
          <span class="custom-tree-node" slot-scope="{ node, data }">
                <i v-if="!data.leaf" class="el-icon-folder"></i>
                <i v-if="data.leaf" class="el-icon-tickets"></i>
                <span>{{ node.label }}</span>
              </span>
        </el-tree>
        <!--树形目录右键菜单-->
        <div v-show="menuVisible">
          <ul id="menu" @mouseout="menuVisible = false">
            <li
              tabindex="-1"
              class="menu_item"
              @mouseenter="menuVisible = true"
              @click="dispatchCommand('rename')"
            >
              重命名
            </li>
            <li
              v-show="menuType === 'folder'"
              tabindex="-1"
              class="menu_item"
              @mouseenter="menuVisible = true"
              @click="dispatchCommand('folder')"
            >
              新建文件夹
            </li>
            <li
              v-show="menuType === 'folder'"
              tabindex="-1"
              class="menu_item"
              @mouseenter="menuVisible = true"
              @click="dispatchCommand('document')"
            >
              新建文档
            </li>
          </ul>
        </div>
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
    <!--弹窗-->
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      width="30%"
      :modal-append-to-body="false"
    >
      <el-input v-model="folderOrDocumentName" placeholder="请输入新名称"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="executeCommand">确 定</el-button>
      </span>
    </el-dialog>
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
        projectId: '',
        menuVisible: false,
        menuType: '', // folder | document 用于判断右键菜单显示项数
        dialogVisible: false,
        dialogTitle: '新建文件夹',
        currentNodeData: '', // 当前右键对应节点包含的数据
        currentNode: '', // 当前右键对应节点
        currentCommandType: '', // folder | document | renameFolder | renameDocument 新建/重命名 文档, 文件夹
        folderOrDocumentName: null,
        nodeHead: null,
        resolveHead: null
      }
    },
    mounted() {

    },
    methods: {
      loadNode(node, resolve) {
        console.log("加载时传入节点内容", node);
        let url = '/category/categories/' + this.projectId;
        if (node.level === 0) {
          this.nodeHead = node;
          this.resolveHead = resolve;
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
      },
      rightClickShowMenu: function (event, data, node, element) {
        this.menuVisible = true;
        this.currentNodeData = data;
        this.currentNode = node;
        if (data.leaf) {
          this.menuType = 'document';
        } else {
          this.menuType = 'folder';
        }
        let menu = document.querySelector("#menu");
        /* 菜单定位基于鼠标点击位置 */
        menu.style.left = event.clientX - 20 + "px";
        menu.style.top = event.clientY - 10 + "px";
        console.log("右键被点击的event:", event);
        console.log("右键被点击的object:", data);
        console.log("右键被点击的value:", node);
        console.log("右键被点击的element:", element);
      },
      /**
       *创建文件夹
       * @param data 父节点, 可能不存在
       * @param name 文件夹名称
       */
      createFolder: function (data, name) {
        // 请求后台根据当前节点创建文件夹
        let parentId = 0;
        if (data && data.id) {
          parentId = data.id;
        }
        let param = {
          project_id: this.projectId,
          parent_id: parentId,
          category_name: name
        };
        // console.log('project_id为', this.projectId, 'parentId为', parentId, 'categoryName为', name);
        // console.log('传递参数为', JSON.stringify(param));
        this.$axios.post("/category", param).then(res => {
          successMsg("新建文件夹成功");
          if (parentId === 0) {
            this.refreshRoot();
          } else {
            this.refreshNode(this.currentNode);
          }
          // 执行完毕清除数据
          this.clearData();
        });
      },
      /**
       *创建文档
       * @param data 父节点, 可能不存在
       * @param name 文档名称
       */
      createDocument: function (data, name) {
        // 执行完毕清除数据
        this.clearData();
      },
      /**
       * 重命名操作
       * @param data 要重命名对象的数据
       * @param type document为重命名文档, folder为重命名文件夹
       * @param name 新名称
       */
      rename: function (data, type, name) {
        console.log('传进来的data为', data, '当前节点为', this.currentNodeData);
        console.log("id为", data.id, 'type为', type, 'name为', name);
        let oldName = data.name;
        data.name = name;
        let param;
        let url;
        let id = data.id;
        if (type === 'renameDocument') {
          param = {
            doc_name: name
          };
          url = '/document/';
        }
        if (type === 'renameFolder') {
          param = {
            category_name: name
          };
          url = '/category/';
        }
        this.$axios.patch(url + id, param).then(res => {
          successMsg("重命名成功", "成功");
          // 执行完毕清除数据
          this.clearData();
        }).catch(err => {
          data.name = oldName;
        });
      },
      dispatchCommand: function (command) {
        this.menuVisible = false;
        this.dialogVisible = true;
        if (command === 'folder') {
          this.currentCommandType = 'folder';
          this.dialogTitle = '新建文件夹';
        }
        if (command === 'document') {
          this.currentCommandType = 'document';
          this.dialogTitle = '新建文档';
        }
        if (command === 'rename') {
          this.folderOrDocumentName = this.currentNodeData.name;
          if (this.menuType === 'folder') {
            this.currentCommandType = 'renameFolder';
            this.dialogTitle = '重命名文件夹';
          }
          if (this.menuType === 'document') {
            this.currentCommandType = 'renameDocument';
            this.dialogTitle = '重命名文档';
          }
        }
      },
      executeCommand: function () {
        this.dialogVisible = false;
        if (this.currentCommandType === 'folder') {
          this.createFolder(this.currentNodeData, this.folderOrDocumentName);
        }
        if (this.currentCommandType === 'document') {
          this.createDocument(this.currentNodeData, this.folderOrDocumentName);
        }
        if (this.currentCommandType === 'renameFolder') {
          this.rename(this.currentNodeData, 'renameFolder', this.folderOrDocumentName);
        }
        if (this.currentCommandType === 'renameDocument') {
          this.rename(this.currentNodeData, 'renameDocument', this.folderOrDocumentName);
        }
      },
      cancel: function () {
        this.dialogVisible = false;
        this.clearData();
      },
      clearData: function () {
        /*命令执行完毕, 清除当前节点数据*/
        this.currentNodeData = null;
        this.folderOrDocumentName = null;
        this.currentNode = null;
      },
      refreshNode(parentNode){
        parentNode.loaded = false;
        parentNode.expand();
      },
      refreshRoot: function () {
        if (!this.nodeHead || !this.resolveHead) {
          return;
        }
        this.nodeHead.childNodes = [];
        this.loadNode(this.nodeHead, this.resolveHead);
      },
      handleDragStart(node, ev) {
        console.log('drag start', node);
      },
      handleDragEnter(draggingNode, dropNode, ev) {
        console.log('tree drag enter: ', dropNode.label);
      },
      handleDragLeave(draggingNode, dropNode, ev) {
        console.log('tree drag leave: ', dropNode.label);
      },
      handleDragOver(draggingNode, dropNode, ev) {
        console.log('tree drag over: ', dropNode.label);
      },
      handleDragEnd(draggingNode, dropNode, dropType, ev) {
        console.log('tree drag end: ', dropNode && dropNode.label, dropType);
      },
      handleDrop(draggingNode, dropNode, dropType, ev) {
        console.log('tree drop: ', dropNode.label, dropType);
      },
      allowDrop(draggingNode, dropNode, type) {
        // 允许放到不是叶子的节点内(文件夹)
        if (type !== 'inner') {
          return false;
        }
        return !dropNode.data.leaf;
      },
      allowDrag(draggingNode) {
        return true;
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

  #menu {
    position: fixed;
    display: block;
    z-index: 20000;
    background-color: #fff;
    padding: 5px 0;
    border: 1px solid #ebeef5;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  ul {
    margin: 0;
    padding: 0;
  }

  ul li {
    list-style: none;
    margin: 0;
    padding: 0 15px;
    font-size: 14px;
    line-height: 30px;
    cursor: pointer;
    text-align: left;
  }

  ul li:hover {
    background-color: #ebeef5
  }

  /*#tree-list {*/
  /*  height: 100%;*/
  /*}*/
  /*#tree-title {*/
  /*  height: 100%;*/
  /*}*/
  #tree-top {
    text-align: left;
  }
</style>
