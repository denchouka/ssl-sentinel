<template>
  <div class="addEvent">
    <div class="title">添加任务</div>
    <el-form
      ref="taskFormRef"
      style="max-width: 600px"
      :model="taskForm"
      :rules="rules"
      label-width="auto"
      class="form"
      :size="formSize"
      status-icon
    >
      <el-form-item label="域名" prop="domainName">
        <el-input
          v-model="taskForm.domainName"
          minlength="3"
          maxlength="10"
          show-word-limit
          placeholder="输入域名"
          clearable
        />
      </el-form-item>
      <el-form-item label="申请平台" prop="applicationPlatform">
        <el-input
          v-model="taskForm.applicationPlatform"
          minlength="3"
          maxlength="10"
          show-word-limit
          placeholder="输入申请平台"
          clearable
        />
      </el-form-item>
      <el-form-item label="使用平台" prop="usagePlatform">
        <el-input
          v-model="taskForm.usagePlatform"
          minlength="3"
          maxlength="10"
          show-word-limit
          placeholder="输入使用平台"
          clearable
        />
      </el-form-item>
      <el-form-item label="用途" prop="purpose">
        <el-input
          v-model="taskForm.purpose"
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
              v-model="taskForm.ddl"
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
              v-model="taskForm.date"
              type="date"
              placeholder="选择从哪天开始提醒"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="邮箱地址" prop="email">
        <el-input
          v-model="taskForm.email"
          show-word-limit
          maxlength="50"
          placeholder="输入邮箱地址"
          clearable
        />
      </el-form-item>
      <el-form-item label="提醒内容" prop="content">
        <el-input
          v-model="taskForm.content"
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
          v-model="taskForm.remark"
          :rows="2"
          type="textarea"
          maxlength="100"
          show-word-limit
          placeholder="输入备注"
          clearable
        />
      </el-form-item>


      <el-form-item class="button">
        <el-button
          type="primary"
          :loading="loading"
          @click="submitForm(taskFormRef)"
        >
          <span v-if="!loading">添 加</span>
          <span v-else>添 加 中...</span>
        </el-button>
        <el-button @click="resetForm(taskFormRef)">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { ComponentSize, FormInstance, FormRules } from 'element-plus'
import { ElMessageBox } from 'element-plus'
import { addTask } from '@/api/index'
import { formatDate } from '@/utils/index'
import { useRouter } from 'vue-router'

// 定义loading状态
var loading = ref(false)

// 获取路由器实例
const router = useRouter()

// 定义任务表单的数据结构
interface TaskForm {
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
const taskFormRef = ref<FormInstance>()

// 使用reactive创建一个响应式的任务表单对象，并初始化
const taskForm = reactive<TaskForm>({
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
const rules = reactive<FormRules<TaskForm>>({
  domainName: [
    { required: true, message: '请输入域名', trigger: 'blur' },
    { min: 3, max: 10, message: 'Length should be 3 to 10', trigger: 'blur' }
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
 * 添加任务
 * @param formEl 
 */
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return

  await formEl.validate((valid, fields) => {
    if (valid) {
      // addTask
      loading.value = true
      
      const taskDto = {
        domainName: taskForm.domainName,
        applicationPlatform: taskForm.applicationPlatform,
        usagePlatform: taskForm.usagePlatform,
        purpose: taskForm.purpose,
        ddl: formatDate(taskForm.ddl),
        date: formatDate(taskForm.date),
        email: taskForm.email,
        content: taskForm.content,
        remark: taskForm.remark
      }
      // 发送添加任务请求
      addTask(taskDto).then(() => {
        loading.value = false
        // 弹框提醒
        ElMessageBox.confirm(
          '添加任务成功',
          '提示',
          {
            confirmButtonText: '查看已添加任务',
            cancelButtonText: '继续添加',
            type: 'success',
            draggable: true
          }
        )
          .then(() => {
            // 查看已添加任务,跳转到任务列表
            router.push('/taskList')
          })
          .catch(() => {
            // 继续添加，重置表单
            resetForm(taskFormRef.value)
          })
      })
    } else {
      console.log('error submit!', fields)
    }
  })
}

/**
 * 
 * @param formEl 重置表单
 */
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .title {
    margin-bottom: 20px;
    margin-left: 20px;
    color: #1775ef;
    font-size: 16px;
  }

  .form {
    margin: 0 auto; /* 左右margin设为auto */
  }

  .button {
    display: grid;
  }
</style>
