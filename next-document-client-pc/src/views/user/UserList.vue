<template>
    <div id="user-manager">
      <el-dialog
        title="新增用户"
        :visible.sync="dialogFormVisible"
        :modal="modal"
        :append-to-body='true'
      >
        <el-form :model="form">
          <el-form-item label="登录名称" :label-width="formLabelWidth">
            <el-input v-model="form.loginName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="用户昵称" :label-width="formLabelWidth">
            <el-input v-model="form.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" :label-width="formLabelWidth">
            <el-radio-group v-model="form.sex">
              <el-radio :label="0">保密</el-radio>
              <el-radio :label="1">男</el-radio>
              <el-radio :label="2">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="密码" :label-width="formLabelWidth">
            <el-input v-model="form.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话" :label-width="formLabelWidth">
            <el-input v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" :label-width="formLabelWidth">
            <el-input v-model="form.email" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
      <el-container
        id="user-manager-main-container"
      >
        <el-header>
          <el-input
            placeholder="请输入要搜索的关键字"
            v-model="keyWord">
            <el-select v-model="select" slot="prepend" placeholder="全局模糊搜索">
              <el-option label="用户昵称" value="1"></el-option>
              <el-option label="登录名" value="2"></el-option>
              <el-option label="用户电话" value="3"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search">搜索</el-button>
            <el-button slot="append" icon="el-icon-search" @click="dialogFormVisible = true">新建</el-button>
          </el-input>
        </el-header>
        <el-main
          id="user-manager-main"
        >
          <div
            id="user-manager-table-container"
            class="infinite-list"
          >
            <el-table
              id="user-manager-table"
              :data="tableData"
              height="100%">
              <el-table-column
                fixed
                prop="date"
                label="日期"
              >
              </el-table-column>
              <el-table-column
                prop="name"
                label="姓名"
              >
              </el-table-column>
              <el-table-column
                prop="province"
                label="省份"
              >
              </el-table-column>
              <el-table-column
                prop="city"
                label="市区"
              >
              </el-table-column>
              <el-table-column
                prop="address"
                label="地址"
              >
              </el-table-column>
              <el-table-column
                prop="zip"
                label="邮编"
              >
              </el-table-column>
              <el-table-column
                fixed="right"
                label="操作"
                width="220px"
              >
                <template slot-scope="scope">
                  <el-button
                    @click.native.prevent="deleteRow(scope.$index, tableData)"
                    size="small"
                  >
                    冻结
                  </el-button>
                  <el-button
                    size="small"
                  >
                    编辑
                  </el-button>
                  <el-button
                    size="small"
                  >
                    删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-main>
        <el-footer>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="100"
            layout="total, sizes, prev, pager, next, jumper"
            :total="400">
          </el-pagination>
        </el-footer>
      </el-container>
    </div>
</template>

<script>
    export default {
      data() {
        return {
          tableData: [],
          keyWord: "",
          searchType: "",
          select: "",
          dialogFormVisible: false,
          formLabelWidth: '120px',
          form: {
            loginName: '',
            nickname: "",
            password: "",
            phone: "",
            email: "",
            sex: 0,
          },
          modal: true,
          fit: true,
        };
      },
      methods: {
        loadUserList: function () {
          let user = {
            date: '2016-05-03',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          };
          this.tableData.push(user);
        }
      },
      created() {
        let user = {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        };
        for (let i = 0; i < 10; i++) {
          this.tableData.push(user);
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
  #user-manager-table-container {
    height: 100%;
  }
  /deep/ .el-main {
    height: 100%;
  }
  #user-manager {
    height: 100%;
  }
  #user-manager-main-container {
    height: 100%;
  }
  #user-manager-main {
    height: 100%;
    padding-top: 0px;
  }
</style>
