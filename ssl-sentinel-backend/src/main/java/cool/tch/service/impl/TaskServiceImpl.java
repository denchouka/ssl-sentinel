package cool.tch.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cool.tch.common.ResponseResult;
import cool.tch.dto.TaskDto;
import cool.tch.dto.TaskSearchDto;
import cool.tch.entity.Task;
import cool.tch.enums.TaskStatusEnum;
import cool.tch.mapper.TaskMapper;
import cool.tch.service.HistoryService;
import cool.tch.service.TaskService;
import cool.tch.util.BeanCopyUtils;
import cool.tch.util.DateUtils;
import cool.tch.util.MailUtils;
import cool.tch.vo.TaskSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cool.tch.common.Constant.TASK_MAIL_SUBJECT;

/**
 * @author denchouka
 * @description Task service接口实现
 * @date 2024/11/29 20:58
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private HistoryService historyService;

    /**
     * 添加任务
     * @param taskDto 任务DTO对象
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult addTask(TaskDto taskDto) {
        // 根据域名和过期日期判断是否存在
        int count = taskMapper.selectCount(taskDto.getDomainName(), taskDto.getDdl());
        Assert.isTrue(count == 0, "当前添加任务已存在，请确认后再添加");

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
     * 执行任务（发送邮件并更新db）
     */
    @Override
    public void executeTask() {
        // 查询所有未执行和执行中，且已到提醒日期的任务，按过期时间排序
        List<Task> tasks = taskMapper.executeList(TaskStatusEnum.NOT_STARTED.getStatus(), TaskStatusEnum.IN_PROGRESS.getStatus());
        tasks.forEach(task -> {

            // 修改任务状态
            Date ddl = task.getDdl();
            if (DateUtils.isTodayBeforeThanDate(ddl)) {
                //  今天 < ddl : 执行完成     只更新db。任务结束。
                taskMapper.updateTaskStatusById(task.getId(), TaskStatusEnum.COMPLETED.getStatus());
            } else {
                // 今天 >= ddl : 执行中
                // 更新db
                taskMapper.updateTaskStatusById(task.getId(), TaskStatusEnum.IN_PROGRESS.getStatus());

                // 插入新的执行历史
                historyService.addHistory(task);

                // 发邮件提醒
                mailUtils.send(task.getEmail(), TASK_MAIL_SUBJECT, task);
            }
        });
    }

    /**
     * 根据任务id查询任务数据
     * @param id 任务id
     * @return 任务数据
     */
    @Override
    public ResponseResult selectOnebyId(Long id) {
        Task task = taskMapper.selectById(id);
        // 因为是修改数据的查询，task肯定是有值的
        TaskSearchVO searchVO = BeanCopyUtils.copyObject(task, TaskSearchVO.class);
        searchVO.setDate(DateUtils.parseDate(task.getDate()));
        searchVO.setDdl(DateUtils.parseDate(task.getDdl()));

        return ResponseResult.success(searchVO);
    }

    /**
     * 修改任务
     * @param taskDto
     * @return
     */
    @Override
    public ResponseResult editTask(TaskDto taskDto) {
        // 复制对象属性
        Task task = BeanCopyUtils.copyObject(taskDto, Task.class);
        taskMapper.editTask(task);
        return ResponseResult.success("修改任务成功");
    }

}
