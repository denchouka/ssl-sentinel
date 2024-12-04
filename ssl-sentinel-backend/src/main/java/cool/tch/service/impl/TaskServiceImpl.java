package cool.tch.service.impl;

import cool.tch.common.ResponseResult;
import cool.tch.dto.TaskDto;
import cool.tch.mapper.TaskMapper;
import cool.tch.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author denchouka
 * @description TODO
 * @date 2024/11/29 20:58
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public ResponseResult addTask(TaskDto taskDto) {
        taskMapper.addTask(taskDto);
        return ResponseResult.success("添加任务成功");
    }

    @Override
    public ResponseResult list(TaskDto taskDto) {
        return null;
    }
}
