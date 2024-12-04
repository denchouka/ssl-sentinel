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
      <el-form-item label="名称" prop="name">
        <el-input v-model="taskForm.name" minlength="3" maxlength="10" show-word-limit clearable/>
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
        <el-input v-model="taskForm.email" show-word-limit maxlength="50" clearable/>
      </el-form-item>
      <el-form-item label="提醒内容" prop="content">
        <el-input v-model="taskForm.content" :rows="2" type="textarea" minlength="10" maxlength="100" show-word-limit clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="taskForm.remark" :rows="2" type="textarea" minlength="10" maxlength="100" show-word-limit clearable/>
      </el-form-item>


      <el-form-item class="button">
        <el-button type="primary" @click="submitForm(taskFormRef)">
          添加
        </el-button>
        <el-button @click="resetForm(taskFormRef)">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { ComponentSize, FormInstance, FormRules } from 'element-plus'
import { addTask } from '@/api/index'
import { formatDate } from '@/utils/index'

interface TaskForm {
  name: string
  ddl: string
  date: string
  email: string
  content: string
  remark: string
}

const formSize = ref<ComponentSize>('default')
const taskFormRef = ref<FormInstance>()
const taskForm = reactive<TaskForm>({
  name: '',
  ddl: '',
  date: '',
  email: '',
  content: '',
  remark: '',
})

const rules = reactive<FormRules<TaskForm>>({
  name: [
    { required: true, message: 'Please input name', trigger: 'blur' },
    { min: 3, max: 10, message: 'Length should be 3 to 10', trigger: 'blur' },
  ],
  ddl: [
    {
      type: 'date',
      required: true,
      message: 'Please pick a date',
      trigger: 'change',
    },
  ],
  date: [
    {
      type: 'date',
      required: true,
      message: 'Please pick a date',
      trigger: 'change',
    },
  ],
  email: [
    { required: true, message: 'Please input email', trigger: 'blur' },
    { max: 50, message: 'Length should be less than 50', trigger: 'blur' },
  ],
  content: [
    { required: true, message: 'Please input content', trigger: 'blur' },
    { min: 10, max: 100, message: 'Length should be 10 to 100', trigger: 'blur' },
  ],
  remark: [
    { required: false, message: 'Please input remark', trigger: 'blur' },
    {  max: 100, message: 'Length should be less than 100', trigger: 'blur' },
  ],
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
      console.log(taskForm)
      
      const taskDto = {
        name: taskForm.name,
        ddl: formatDate(taskForm.ddl),
        date: formatDate(taskForm.date),
        email: taskForm.email,
        content: taskForm.content,
        remark: taskForm.remark
      }
      console.log(taskDto)
      addTask(taskDto).then(() => {
        console.log('添加任务成功!')
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
