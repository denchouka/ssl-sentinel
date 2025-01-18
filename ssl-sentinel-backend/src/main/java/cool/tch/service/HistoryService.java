package cool.tch.service;

import cool.tch.common.ResponseResult;
import cool.tch.entity.Task;

/**
 * @author denchouka
 * @description History service接口
 * @date 2025/1/11 20:06
 */
public interface HistoryService {

    /**
     * 添加任务的执行历史
     * @param task 任务信息
     * @return
     */
    ResponseResult addHistory(Task task);

    /**
     * 根据taskId查询任务执行历史
     * @param taskId taskId
     * @return 任务执行历史
     */
    ResponseResult showHistory(Long taskId);
}
