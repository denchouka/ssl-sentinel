package cool.tch.service.impl;

import cool.tch.common.ResponseResult;
import cool.tch.entity.Task;
import cool.tch.mapper.HistoryMapper;
import cool.tch.service.HistoryService;
import cool.tch.util.DateUtils;
import cool.tch.vo.HistoryShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author denchouka
 * @description History service接口实现
 * @date 2025/1/11 20:08
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public ResponseResult addHistory(Task task) {
        // 添加任务的执行历史
        historyMapper.addHistory(task.getId(), DateUtils.now());
        return ResponseResult.success("添加任务的执行历史成功");
    }

    /**
     * 根据taskId查询任务执行历史
     * @param taskId taskId
     * @return 任务执行历史
     */
    @Override
    public ResponseResult showHistory(Long taskId) {
        List<HistoryShowVO> historyList = historyMapper.listHistory(taskId);
        String message = (historyList.isEmpty()) ? "未查询到任务执行历史" : "查询任务执行历史成功";
        return ResponseResult.success(message, historyList);
    }
}