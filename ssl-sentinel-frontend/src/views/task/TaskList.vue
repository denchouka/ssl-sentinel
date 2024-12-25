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
          <el-table-column type="index" align="center" :index="indexMethod" width="50" />
          <el-table-column header-align="center" align="center" prop="domainName" label="域名" width="195" />
          <el-table-column header-align="center" align="center" prop="applicationPlatform" label="申请平台" width="170" />
          <el-table-column header-align="center" align="center" prop="usagePlatform" label="使用平台" width="170" />
          <el-table-column header-align="center" align="center" prop="purpose" label="用途" width="390">
            <template #default="scope">
              <el-tooltip :content="scope.row.purpose" placement="top">
                <div class="multi-line-ellipsis">{{ scope.row.purpose }}</div>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="ddl" label="过期日期" width="110" />
          <el-table-column header-align="center" align="center" prop="date" label="提醒日期" width="110" />
          <el-table-column header-align="center" align="center" prop="email" label="邮箱地址" width="180">
            <template #default="scope">
              <el-tooltip :content="scope.row.email" placement="top">
                <div class="multi-line-ellipsis">{{ scope.row.email }}</div>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="content" label="提醒内容" width="390">
            <template #default="scope">
              <el-tooltip :content="scope.row.content" placement="top">
                <div class="multi-line-ellipsis">{{ scope.row.content }}</div>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="remark" label="备注" width="390">
            <template #default="scope">
              <el-tooltip :content="scope.row.remark" placement="top">
                <div class="multi-line-ellipsis">{{ scope.row.remark }}</div>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" prop="status" label="执行状态">
            <template #default="scope">
              <el-tag v-if="scope.row.status == 1" type="primary">执行中</el-tag>
              <el-tag v-else-if="scope.row.status == 2" type="success">执行完成</el-tag>
              <el-tag v-else type="info">未执行</el-tag>
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
          :current-page="pagination.currentPage"
          :total="pagination.total"
          @current-change="handleCurrentChange"
          @prev-click="handlePrevClick"
          @next-click="handleNextClick"
        />
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { formatDate } from '@/utils/index'
import { taskList } from '@/api/index'

// 定义loading状态
var loading = ref(false)

const options = [
  {
    value: 0,
    label: '未执行',
  },
  {
    value: 1,
    label: '执行中',
  },
  {
    value: 2,
    label: '执行完成',
  }
]

const indexMethod = (index: number) => {
  return index + 1
}

// 表格的数据
const taskData = [
  {
    domainName: '12345678901234567890',
    applicationPlatform: '阿里云阿里云阿里云阿',
    usagePlatform: '腾讯云腾讯云腾讯云腾',
    purpose: '用途用途用途用途用途',
    ddl: '2016-05-01',
    date: '2016-05-03',
    email: '12345678901234567890123456789012345678901234567890',
    content: '提醒内容提',
    remark: '备注备注备注备注备注',
    status: 2
  }
]

interface Pagination {
  currentPage: number;
  pageSize: number;
  total: number;
}

// 分页的属性
const pagination = reactive<Pagination>({
  // 总条目数
  total: 50,
  // 定义可选的每页显示数量
  pageSize: 10,
  // 当前页数
  currentPage: 2
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

/**
 * 检索
 */
const toSearch = () => {
  console.log('检索')
  loading.value = true
  const searchTaskDto = {
    domainName: taskForm.domainName,
    status: taskForm.status,
    ddl: formatDate(taskForm.ddl)
  }
  console.log(searchTaskDto)
  taskList(searchTaskDto).then(res => {
    // 获取查询的结果


    loading.value = false
  }).catch(() => {
    loading.value = false
  })
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

// 模拟数据获取函数
const fetchData = async () => {
  // 在这里实现实际的数据获取逻辑
  console.log('Fetching data...')
}

// 处理当前页面变化
const handleCurrentChange = (val: number) => {
  console.log(`处理当前页面变化, 目标页: ${val}`)
  pagination.currentPage = val
  fetchData()
}

// 上一页点击事件
const handlePrevClick = (val: number) => {
  console.log(`上一页, 当前页: ${val}`)
  // 可选：特定逻辑
}

// 下一页点击事件
const handleNextClick = (val: number) => {
  console.log(`下一页, 当前页: ${val}`)
  // 可选：特定逻辑
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
