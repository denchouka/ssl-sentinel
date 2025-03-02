package cool.tch.controller;

import cool.tch.common.ResponseResult;
import cool.tch.service.HistoryService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cool.tch.common.Constant.REQUEST_URL_PREFIX;

/**
 * @author denchouka
 * @description TODO
 * @date 2025/1/16 22:44
 */
@RestController
@RequestMapping(REQUEST_URL_PREFIX + "/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/show")
    public ResponseResult showHistory(@NotNull(message = "任务id不能为空") @RequestParam("id") Long id) {
        return historyService.showHistory(id);
    }
}
