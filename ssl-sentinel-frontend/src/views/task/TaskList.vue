<template>
  <div class="eventList">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="5">
            <el-input
              v-model="taskName"
              style="width: 240px"
              placeholder="输入任务名称"
              clearable
            />
          </el-col>
          <el-col :span="5">
            <el-select
              v-model="taskStatus"
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
          <el-col :span="5">4444</el-col>
          <el-col :span="5">
            <el-button type="primary">检索</el-button>
          </el-col>
        </el-row>
      </el-header>
      <el-main>main</el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'eventList',
  data() {
    return {
      taskName: null,
      taskStatus: null,
      options: (
        {
          label: '未执行',
          value: 0
        },
        {
          label: '执行中',
          value: 1
        },
        {
          label: '执行完成',
          value: 2
        }
      )
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
h1 {
  color: black;
}
</style>
