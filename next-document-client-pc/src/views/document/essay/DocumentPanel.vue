<template>
    <div id="main-panel" style="height: 100%">
      <el-container>
        <el-aside
          id="layout-aside"
        >
          <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
        </el-aside>
        <el-container direction="vertical">
          <el-main>
            <mavon-editor
              v-if="preview"
              id="md-editor-preview"
              v-model="content"
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
              v-model="content"
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
  import {success} from "../../../util/notify";

  export default {
    data() {
      return {
        content: "",
        preview: true,
        data: [{
          label: '一级 1',
          children: [{
            label: '二级 1-1',
            children: [{
              label: '三级 1-1-1'
            }]
          }]
        }, {
          label: '一级 2',
          children: [{
            label: '二级 2-1',
            children: [{
              label: '三级 2-1-1'
            }]
          }, {
            label: '二级 2-2',
            children: [{
              label: '三级 2-2-1'
            }]
          }]
        }, {
          label: '一级 3',
          children: [{
            label: '二级 3-1',
            children: [{
              label: '三级 3-1-1'
            }]
          }, {
            label: '二级 3-2',
            children: [{
              label: '三级 3-2-1'
            }]
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    mounted() {
      
    },
    methods: {
      handleNodeClick(data) {
        console.log(data);
      },
      togglePreview: function () {
        this.preview = !this.preview;
      },
      save: function () {
        success("您的文档已保存至服务器", "保存成功")
      },
      saveAndPreview: function () {
        this.save();
        this.togglePreview();
      }
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
    line-height: 200px;
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
  #layout-aside {
    max-width: 250px;
  }
</style>
