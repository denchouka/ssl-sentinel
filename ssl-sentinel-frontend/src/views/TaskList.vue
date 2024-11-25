<template>
  <div class="eventList">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="5">
            <el-input
              v-model="eventName"
              style="width: 240px"
              placeholder="输入任务名称"
              clearable
            />
          </el-col>
          <el-col :span="5">
            <el-select
              v-model="value"
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
          <el-col :span="5">333</el-col>
          <el-col :span="5">4444</el-col>
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
      eventName: null
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
h1 {
  color: black;
}
</style>
