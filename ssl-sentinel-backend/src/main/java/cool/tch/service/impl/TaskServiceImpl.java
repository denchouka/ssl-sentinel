package cool.tch.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cool.tch.common.ResponseResult;
import cool.tch.dto.TaskDto;
import cool.tch.dto.TaskSearchDto;
import cool.tch.entity.Task;
import cool.tch.enums.TaskStatusEnum;
import cool.tch.mapper.TaskMapper;
import cool.tch.service.TaskService;
import cool.tch.util.BeanCopyUtils;
import cool.tch.util.DateUtils;
import cool.tch.util.MailUtils;
import cool.tch.vo.TaskSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author denchouka
 * @description Task service接口实现
 * @date 2024/11/29 20:58
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 添加任务
     * @param taskDto 任务DTO对象
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addTask(TaskDto taskDto) {
        // 复制对象属性
        Task task = BeanCopyUtils.copyObject(taskDto, Task.class);
        // 默认状态为未执行
        task.setStatus(TaskStatusEnum.NOT_STARTED.getStatus());
        taskMapper.addTask(task);

        return ResponseResult.success("添加任务成功");
    }

    /**
     * 查询任务
     * @param taskSearchDto
     * @return
     */
    @Override
    public ResponseResult list(TaskSearchDto taskSearchDto) {

        // 校验参数：任务状态
        if (taskSearchDto.getStatus() != null) {
            TaskStatusEnum.getByCode(taskSearchDto.getStatus());
        }

        // 设置分页参数
        PageHelper.startPage(taskSearchDto.getPageNum(), taskSearchDto.getPageSize());

        // 执行查询
        List<Task> taskList = taskMapper.list(taskSearchDto.getDomainName(), taskSearchDto.getStatus(), taskSearchDto.getDdl());

        // 使用PageInfo包装查询的结果
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);

        // Task -> TaskSearchVO
        List<TaskSearchVO> searchVOS = new ArrayList<>();
        taskList.forEach(task -> {
            TaskSearchVO searchVO = BeanCopyUtils.copyObject(task, TaskSearchVO.class);
            // 日期格式化
            searchVO.setDate(DateUtils.parseDate(task.getDate()));
            searchVO.setDdl(DateUtils.parseDate(task.getDdl()));
            searchVOS.add(searchVO);
        });

        // 创建一个新的PageInfo实例，保持原有的分页信息，但使用转换后的VO列表
        PageInfo<TaskSearchVO> voPageInfo = new PageInfo<>(searchVOS);
        voPageInfo.setPageNum(pageInfo.getPageNum());
        voPageInfo.setPageSize(pageInfo.getPageSize());
        voPageInfo.setTotal(pageInfo.getTotal());

        return ResponseResult.success(voPageInfo);
    }

    /**
     * 执行任务
     */
    @Override
    public void executeTask() {
        // 查询所有未执行和执行中，且已到提醒日期的任务，按过期时间排序
        List<Task> tasks = taskMapper.executeList(TaskStatusEnum.NOT_STARTED.getStatus(), TaskStatusEnum.IN_PROGRESS.getStatus());
        tasks.forEach(task -> {
            // 发邮件提醒
            MailUtils.send(null, null, null);

            // 修改任务状态
            Date ddl = task.getDdl();
            // 今天 < ddl : 执行中     今天 >= ddl : 执行完成
            int newStatus = DateUtils.isTodayBeforeThanDate(ddl) ? TaskStatusEnum.IN_PROGRESS.getStatus() : TaskStatusEnum.COMPLETED.getStatus();
            // 更新db
            taskMapper.updateTaskStatusById(task.getId(), newStatus);
        });
    }
}
