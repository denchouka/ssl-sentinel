<template>
  <div class="eventList">
    <div class="title">任务列表</div>
    <el-container>
      <!--头部 检索区域-->
      <el-header>
        <el-row>
          <el-col :span="5">
            域名
            <el-input
              v-model="taskForm.domainName"
              style="width: 240px"
              placeholder="输入域名"
              clearable
            />
          </el-col>
          <el-col :span="5">
            任务状态
            <el-select
              v-model="taskForm.status"
              placeholder="选择任务状态"
              style="width: 240px"
              clearable
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-col>
          <el-col :span="5">
            过期日期
            <el-date-picker
              v-model="taskForm.ddl"
              type="date"
              placeholder="选择过期的日期"
            />
          </el-col>
          <el-col :span="5">
            <el-button type="primary" :loading="loading" @click="toSearch()">检索</el-button>
            <el-button @click="reset()">重置</el-button>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <!--表格区域-->
        <el-table :data="taskData" border stripe="true" style="width: 100%">
          <el-table-column type="index" align="center" :index="indexMethod" label="No" width="50" />
          <el-table-column header-align="center" align="center" prop="domainName" label="域名" width="180" />
          <el-table-column header-align="center" align="center" prop="applicationPlatform" label="申请平台" width="160" />
          <el-table-column header-align="center" align="center" prop="usagePlatform" label="使用平台" width="160" />
          <el-table-column header-align="center" align="center" prop="purpose" label="用途" width="380">
            <template #default="scope">
              <el-tooltip :content="scope.row.purpose" placement="top">
                <div class="multi-line-ellipsis">{{ scope.row.purpose }}</div>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="ddl" label="过期日期" width="105" />
          <el-table-column header-align="center" align="center" prop="date" label="提醒日期" width="105" />
          <el-table-column header-align="center" align="center" prop="email" label="邮箱地址" width="170">
            <template #default="scope">
              <el-tooltip :content="scope.row.email" placement="top">
                <div class="multi-line-ellipsis">{{ scope.row.email }}</div>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="content" label="提醒内容" width="380">
            <template #default="scope">
              <el-tooltip :content="scope.row.content" placement="top">
                <div class="multi-line-ellipsis">{{ scope.row.content }}</div>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="remark" label="备注">
            <template #default="scope">
              <el-tooltip :content="scope.row.remark" placement="top">
                <div class="multi-line-ellipsis">{{ scope.row.remark }}</div>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="status" label="执行状态" width="95">
            <template #default="scope">
              <el-tag v-if="scope.row.status == 1" type="info">未执行</el-tag>
              <el-tag v-else-if="scope.row.status == 2" type="primary">执行中</el-tag>
              <el-tag v-else-if="scope.row.status == 3" type="success">执行完成</el-tag>
              <el-tag v-else type="danger">未知状态</el-tag>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="status" label="操作" width="100">
            <template #default="scope">
              <el-button v-if="scope.row.status == 1" type="warning" plain size="small" @click="toEdit(scope.row.id)">修改</el-button>
              <el-button v-else type="success" plain size="small" @click="toShowHistory(scope.row.id)">执行日志</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--分页-->
        <br>
        <br>
        <el-pagination
          class="pagination"
          background
          layout="prev, pager, next"
          :page-sizes="[10]"
          :page-size="pagination.pageSize"
          :current-page="pagination.pageNum"
          :total="pagination.total"
          @current-change="handleCurrentChange"
          @prev-click="handlePrevClick"
          @next-click="handleNextClick"
        />
      </el-main>
    </el-container>
  </div>

  <!-- 无数据dialog -->
  <el-dialog
    v-model="noDataDialogVisible"
    title="执行日志"
    width="400"
    align-center
    draggable
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :show-close="false"
  >
    <el-empty description="无数据" image="/src/assets/nodata.png"/>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" plain @click="noDataDialogVisible = false">关闭</el-button>
      </div>
    </template>
  </el-dialog>

  <!-- 有数据dialog -->
  <el-dialog
    v-model="dialogFormVisible"
    title="执行日志"
    width="400"
    align-center
    draggable
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :show-close="false"
  >
    <el-table :data="gridData" border stripe="true" height="280" >
      <el-table-column type="index" align="center" :index="indexMethod" label="No" width="50" />
      <el-table-column header-align="center" align="center" prop="taskId" label="任务ID" width="75" />
      <el-table-column header-align="center" align="center" prop="executeTime" label="任务执行时间" width="243" />
    </el-table>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" plain @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </template>
  </el-dialog>

  <!-- 修改任务数据dialog -->
  <el-dialog
    v-model="editDataDialogVisible"
    title="修改任务"
    width="600"
    align-center
    draggable
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :show-close="false"
  >
    <!--修改任务的form start-->
    <el-form
      ref="editTaskFormRef"
      style="max-width: 600px"
      :model="editTaskForm"
      :rules="editFormRules"
      label-width="auto"
      class="form"
      :size="formSize"
      status-icon
    >
      <el-form-item label="域名" prop="domainName">
        <el-input
          v-model="editTaskForm.domainName"
          minlength="4"
          maxlength="20"
          show-word-limit
          placeholder="输入域名"
          clearable
        />
      </el-form-item>
      <el-form-item label="申请平台" prop="applicationPlatform">
        <el-input
          v-model="editTaskForm.applicationPlatform"
          minlength="3"
          maxlength="10"
          show-word-limit
          placeholder="输入申请平台"
          clearable
        />
      </el-form-item>
      <el-form-item label="使用平台" prop="usagePlatform">
        <el-input
          v-model="editTaskForm.usagePlatform"
          minlength="3"
          maxlength="10"
          show-word-limit
          placeholder="输入使用平台"
          clearable
        />
      </el-form-item>
      <el-form-item label="用途" prop="purpose">
        <el-input
          v-model="editTaskForm.purpose"
          :rows="2"
          type="textarea"
          minlength="10"
          maxlength="100"
          show-word-limit
          placeholder="输入用途"
          clearable
        />
      </el-form-item>
      <el-form-item label="过期日期" required>
        <el-col :span="24">
          <el-form-item prop="ddl">
            <el-date-picker
              v-model="editTaskForm.ddl"
              type="date"
              placeholder="选择过期的日期"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="提醒日期" required>
        <el-col :span="24">
          <el-form-item prop="date">
            <el-date-picker
              v-model="editTaskForm.date"
              type="date"
              placeholder="选择从哪天开始提醒"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="邮箱地址" prop="email">
        <el-input
          v-model="editTaskForm.email"
          show-word-limit
          maxlength="50"
          placeholder="输入邮箱地址"
          clearable
        />
      </el-form-item>
      <el-form-item label="提醒内容" prop="content">
        <el-input
          v-model="editTaskForm.content"
          :rows="2"
          type="textarea"
          minlength="10"
          maxlength="100"
          show-word-limit
          placeholder="输入提醒内容"
          clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input
          v-model="editTaskForm.remark"
          :rows="2"
          type="textarea"
          maxlength="100"
          show-word-limit
          placeholder="输入备注"
          clearable
        />
      </el-form-item>
    </el-form>
    <!--修改任务的form end-->
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" plain @click="editDataDialogVisible = false">关闭</el-button>
        <el-button type="primary" @click="saveEditedData(editTaskFormRef)">
          <span v-if="!loading">保存修改</span>
          <span v-else>保存中...</span>
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue'
import { formatDate } from '@/utils/index'
import { taskList, showHistory, selectTask, editTask } from '@/api/index'
import type { ComponentSize, FormInstance, FormRules } from 'element-plus'
import { ElMessage } from 'element-plus'

// 定义loading状态
var loading = ref(false)

// 定义对话框显示状态
var dialogFormVisible = ref(false)
var noDataDialogVisible = ref(false)
var editDataDialogVisible = ref(false)

const options = [
  {
    value: 1,
    label: '未执行',
  },
  {
    value: 2,
    label: '执行中',
  },
  {
    value: 3,
    label: '执行完成',
  }
]

const indexMethod = (index: number) => {
  return index + 1
}

// 表格的数据
var taskData = []
var gridData = []

interface Pagination {
  pageNum: number;
  pageSize: number;
  total: number;
}

// 分页的属性
var pagination = reactive<Pagination>({
  // 页码(默认)
  pageNum: 1,
  // 每页显示数量(默认)
  pageSize: 10,
  // 总条目数
  total: 0
})

// 定义任务表单的数据结构
interface TaskForm {
  domainName: string
  status: string,
  ddl: string
}

// 使用reactive创建一个响应式的任务表单对象，并初始化
const taskForm = reactive<TaskForm>({
  domainName: '',
  status: '',
  ddl: ''
})

// 定义任务表单的数据结构
interface EditTaskForm {
  id: number,
  domainName: string
  applicationPlatform: string,
  usagePlatform: string,
  purpose: string,
  ddl: string
  date: string
  email: string
  content: string
  remark: string
}

// 表单尺寸选项，为默认值
const formSize = ref<ComponentSize>('default')

// 表单实例引用，用于访问表单的方法
const editTaskFormRef = ref<FormInstance>()

// 使用reactive创建一个响应式的任务表单对象，并初始化
var editTaskForm = reactive<EditTaskForm>({
  id: 0,
  domainName: '',
  applicationPlatform: '',
  usagePlatform: '',
  purpose: '',
  ddl: '',
  date: '',
  email: '',
  content: '',
  remark: '',
})

// 验证规则
const editFormRules = reactive<FormRules<EditTaskForm>>({
  domainName: [
    { required: true, message: '请输入域名', trigger: 'blur' },
    { min: 4, max: 20, message: 'Length should be 4 to 20', trigger: 'blur' }
  ],
  applicationPlatform: [
    { required: true, message: '请输入申请平台', trigger: 'blur' },
    { min: 3, max: 10, message: 'Length should be 3 to 10', trigger: 'blur' }
  ],
  usagePlatform: [
    { required: true, message: '请输入使用平台', trigger: 'blur' },
    { min: 3, max: 10, message: 'Length should be 3 to 10', trigger: 'blur' }
  ],
  purpose: [
    { required: true, message: '请输入用途', trigger: 'blur' },
    {  min: 10, max: 100, message: 'Length should be 10 to 100', trigger: 'blur' }
  ],
  ddl: [
    {
      type: 'date',
      required: true,
      message: '请选择过期日期',
      trigger: 'change'
    },
  ],
  date: [
    {
      type: 'date',
      required: true,
      message: '请选择提醒日期',
      trigger: 'change'
    },
  ],
  email: [
    { required: true, message: '请输入邮箱地址', trigger: 'blur' },
    { max: 50, message: 'Length should be less than 50', trigger: 'blur' }
  ],
  content: [
    { required: true, message: '请输入提醒内容', trigger: 'blur' },
    { min: 10, max: 100, message: 'Length should be 10 to 100', trigger: 'blur' }
  ],
  remark: [
    { required: false, message: '请输入备注', trigger: 'blur' },
    {  max: 100, message: 'Length should be less than 100', trigger: 'blur' }
  ]
})

/**
 * 生命周期函数，组件完成初始渲染
 */
onMounted(() => {
  // 无参数查询任务列表(默认是第1页)
  fetchTaskList(1, pagination.pageSize)
})

/**
 * 查询任务列表
 * @param data 请求参数
 */
const fetchTaskList = (pageNum, pageSize) => {
  // 开始loading
  loading.value = true
  const data = {
    pageNum: pageNum,
    pageSize: pageSize,
    domainName: taskForm.domainName,
    status: taskForm.status,
    ddl: formatDate(taskForm.ddl)
  }
  
  taskList(data).then(res => {
    // 表格的数据
    taskData = res.data.list
    // 页码
    pagination.pageNum = res.data.pageNum
    // 总条目数
    pagination.total = res.data.total

    // 查询结束，loading结束
    loading.value = false
  }).catch(() => {
    // loading结束
    loading.value = false
  })
}

/**
 * 检索
 */
const toSearch = () => {
  // 无参数查询任务列表(默认是第1页)
  fetchTaskList(1, pagination.pageSize)
}

/**
 * TaskForm初始状态，重置用
 */
const initTaskFormData = {
  domainName: '',
  status: '',
  ddl: ''
}

/**
 * 重置
 */
const reset = () => {
  // 覆盖原对象
  Object.assign(taskForm, initTaskFormData)
}

// 处理当前页面变化
const handleCurrentChange = (val: number) => {
  fetchTaskList(val, pagination.pageSize)
}

// 上一页点击事件
const handlePrevClick = (val: number) => {
  // 可选：特定逻辑
  fetchTaskList(val - 1, pagination.pageSize)
}

// 下一页点击事件
const handleNextClick = (val: number) => {
  // 可选：特定逻辑
  fetchTaskList(val + 1, pagination.pageSize)
}

/**
 * 修改任务信息
 * @param id 任务id
 */
const toEdit = async (id: number) => {
  // 根据id查询任务数据
  const res = await selectTask(id)
  // 更新数据并
  // 更新数据,使用 Object.assign 保持响应性
  Object.assign(editTaskForm, res.data)
  // 显示对话框
  editDataDialogVisible.value = true
}

/**
 * 查看任务执行历史
 * @param id 任务id
 */
const toShowHistory = (id: number) => {
  showHistory(id).then(res => {
    const data = res.data
    if (data.length == 0) {
      // 无数据
      noDataDialogVisible.value = true
    } else {
      dialogFormVisible.value = true
      gridData = data
    }
  })
}

/**
 * 保存修改后的任务数据
 */
const saveEditedData = async (formEl: FormInstance | undefined) => {
  if (!formEl) return

  await formEl.validate((valid, fields) => {
    if (valid) {
      // addTask
      loading.value = true
      
      const taskDto = {
        id: editTaskForm.id,
        domainName: editTaskForm.domainName,
        applicationPlatform: editTaskForm.applicationPlatform,
        usagePlatform: editTaskForm.usagePlatform,
        purpose: editTaskForm.purpose,
        ddl: formatDate(editTaskForm.ddl),
        date: formatDate(editTaskForm.date),
        email: editTaskForm.email,
        content: editTaskForm.content,
        remark: editTaskForm.remark
      }

      // 发送添加任务请求
      const res = editTask(taskDto)
      loading.value = false
      editDataDialogVisible.value = false
      // 弹框提醒
      ElMessage({
        message: '修改成功',
        type: 'success',
      })
    } else {
      console.log('error submit!', fields)
    }
  })  
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .title {
    margin-bottom: 20px;
    margin-left: 20px;
    color: #1775ef;
    font-size: 16px;
  }

  .pagination {
    display: flex;
    justify-content: flex-end; /* 将子元素推到右边 */
  }

  ::v-deep(.el-table th.el-table__cell) {
    background-color: #edf9f9 !important;
  }

  /* 在全局或 scoped 样式中添加 */
  .multi-line-ellipsis {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2; /* 只显示两行 */
    overflow: hidden;
    text-overflow: ellipsis;
  }
</style>
