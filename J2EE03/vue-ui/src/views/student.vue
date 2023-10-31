<template>
  <el-card class="box-card">
    <!-- Dialog 对话框 弹出新增和修改表单 -->
    <el-row>
      <el-col span="8">
        <el-input v-model="searchQuery" placeholder="请输入想要查询的学生信息" size="120" name="input">
          <template slot="append">
            <el-button size="mini" type="primary" @click="search">查询</el-button>
          </template>
        </el-input>
      </el-col>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <el-button size="mini" type="primary" @click="add">新增</el-button>

      <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form" :rules="rules" ref="form">
          <el-form-item label="id:" hidden>
            <el-input v-model="form.id"></el-input>
          </el-form-item>
          <el-form-item label="学号:" prop="number">
            <el-input v-model="form.number" placeholder="请输入学号" style="width:80%"></el-input>
          </el-form-item>

          <el-form-item label="头像:">
            <el-upload
              class="avatar-uploader"
              action="/api/upload"
              :headers="token"
              name="image"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="form.image" :src="form.image" class="avatar"/>
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>

          <el-form-item label="姓名:" prop="name">
            <el-input v-model="form.name" placeholder="请输入姓名" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="班级:" prop="classNumber">
            <el-input v-model.number="form.classNumber" placeholder="请输入班级" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="电话号码:" prop="phoneNumber">
            <el-input v-model="form.phoneNumber" placeholder="请输入电话号码" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="邮箱:" prop="email">
            <el-input v-model="form.email" placeholder="请输入邮箱" style="width:80%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">提 交</el-button>
        </div>
      </el-dialog>
    </el-row>

    <!-- 表格 -->
    <el-table
      ref="singleTable"
      :data="tableData"
      style="width: 100%">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        property="id"
        label="ID"
        width="100"
        align="center">
      </el-table-column>
      <el-table-column
        property="img"
        label="头像"
        width="150"
        align="center">
      </el-table-column>
      <el-table-column
        property="number"
        label="学号"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column
        property="name"
        label="姓名"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column
        property="classNumber"
        label="班级"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column
        property="phoneNumber"
        label="电话号码"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column
        property="email"
        label="邮箱"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="edit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="remove(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-col span="4">
      <el-input v-model="page" placeholder="输入页码"  name="input1"></el-input>
      <el-input v-model="pageSize" placeholder="输入每页条数" name="input2"></el-input>
    </el-col>
    <br>&nbsp;
    <el-button size="mini" type="primary" @click="prev">上一页</el-button>
    <el-button size="mini" type="primary" @click="show">显示</el-button>
    <el-button size="mini" type="primary" @click="next">下一页</el-button>

  </el-card>
</template>

<script>
import axios from "axios";

export default {
  name: "student",
  data() {
    return {
      background: true,
      title: '',
      currentRow: null,
      dialogFormVisible: false,
      form: {},
      tableData: [],
      searchQuery:'',
      page:'',
      pageSize:'',
      rules: {
        number: [{required: true, message: '请输入学号', trigger: 'blur'}],
        image:[],
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        classNumber: [{required: true, message: '请输入班级', trigger: 'blur'}],
        phoneNumber: [{required: true, message: '请输入电话号码', trigger: 'blur'}],
        email: [{required: true, message: '请输入邮箱', trigger: 'blur'}]
      }
    }
  },
  methods: {
    //图片相关
    handleAvatarSuccess(res, file) {
      this.form.image = res.data;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    //分页相关
    show(){
      axios.get('http://localhost:9090/student/show/' + this.page + "/" + this.pageSize, {
        params: {
          size: this.page,
          pageSize : this.pageSize
        }
      })
        .then(response => {
          this.tableData = response.data; // 假设响应数据是一个数组
        })
        .catch(error => {
          console.error(error);
        });
    },
    prev(){
      this.page--;
    },
    next(){
      this.page++;
    },
    // 表单重置初始化
    reset() {
      this.form = {
        id: null,
        number: null,
        name: null,
        classNumber: null,
        phoneNumber: null,
        email: null
      }
    },

    // 增
    add() {
      this.reset()
      this.dialogFormVisible = true
      this.title = "新增学生数据"
    },

    // 删
    remove(index, row) {
      console.log(row.id)
      this.$axios({
        method: 'post',
        url: 'http://localhost:9090/student/remove/' + row.id,
      }).then((response) => {
        this.$message({
          message: '删除成功!',
          type: 'success'
        });
        this.getList();
      }).catch((error) => {
      })
    },

    // 改
    edit(index, row) {
      this.reset()
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogFormVisible = true
      this.title = "修改学生数据"
    },

    //显示所有数据
    getList() {
      this.$axios.get('http://localhost:9090/student/info'
      ).then((response) => {
        this.tableData = response.data
      }).catch((error) => {
      })
    },

    search(){
      axios.get('http://localhost:9090/student/get/' + this.searchQuery, {
        params: {
          query: this.searchQuery
        }
      })
        .then(response => {
          this.tableData = response.data; // 假设响应数据是一个数组
        })
        .catch(error => {
          console.error(error);
        });
    },
    // 你的其他方法

    //提交按钮
    submit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.id == null) {
            this.$axios({
              method: 'post',
              data: this.form,
              url: 'http://localhost:9090/student/add',
            }).then((response) => {
              this.$message({
                message: '新增成功!',
                type: 'success'
              });
              this.dialogFormVisible = false
              this.getList();
            }).catch((error) => {
            })
          } else {
            this.$axios({
              method: 'post',
              data: this.form,
              url: 'http://localhost:9090/student/edit',
            }).then((response) => {
              this.$message({
                message: '修改成功!',
                type: 'success'
              });
              this.getList();
              this.dialogFormVisible = false
            }).catch((error) => {
            })
          }
        } else {
          return false;
        }
      })
    }
  },
  mounted() {
    this.getList();
  }
}
</script>

<style scoped>
</style>
