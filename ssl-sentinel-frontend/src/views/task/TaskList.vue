<template>
  <div class="eventList">
    <div class="title">任务列表</div>
    <el-container>
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
            <el-button type="primary" @click="reset()">重置</el-button>
          </el-col>
        </el-row>
      </el-header>
      <el-main>main</el-main>
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
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .title {
    margin-bottom: 20px;
    margin-left: 20px;
    color: #1775ef;
    font-size: 16px;
  }
</style>
