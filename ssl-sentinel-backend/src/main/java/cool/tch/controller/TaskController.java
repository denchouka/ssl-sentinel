package cool.tch.controller;

import cool.tch.common.ResponseResult;
import cool.tch.dto.TaskDto;
import cool.tch.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author denchouka
 * @description 任务Controller层
 * @date 2024/11/29 21:00
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseResult addTask(@Validated @RequestBody TaskDto taskDto) {
        return taskService.addTask(taskDto);
    }
}
