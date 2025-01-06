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
import cool.tch.vo.TaskSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        PageHelper.startPage(2, 1);

        // 执行查询
        List<TaskSearchVO> taskList = taskMapper.list(taskSearchDto.getDomainName(), taskSearchDto.getStatus(), taskSearchDto.getDdl());

        // 使用PageInfo包装查询的结果
        PageInfo<TaskSearchVO> pageInfo = new PageInfo<>(taskList);

        return ResponseResult.success(pageInfo);
    }
}
