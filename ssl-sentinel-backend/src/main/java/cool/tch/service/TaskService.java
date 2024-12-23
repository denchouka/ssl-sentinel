package cool.tch.service;

import cool.tch.common.ResponseResult;
import cool.tch.dto.TaskDto;

/**
 * @author denchouka
 * @description Task service接口
 * @date 2024/11/29 20:57
 */
public interface TaskService {

    /**
     * 添加任务
     * @param taskDto
     * @return
     */
    ResponseResult addTask(TaskDto taskDto);

    /**
     * 查询任务
     * @param taskDto
     * @return
     */
    ResponseResult list(TaskDto taskDto);
}
