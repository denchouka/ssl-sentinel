package cool.tch.task;

import cool.tch.util.TokenUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static cool.tch.common.Constant.TOKEN_REVOKE_CRON;

/**
 * @author denchouka
 * @description 定时任务
 * @date 2025/1/9 20:24
 */
@Component
public class ScheduledTask {

    /**
     * 定时清理手动失效的token
     */
    @Scheduled(cron = TOKEN_REVOKE_CRON)
    public void cleanRevokeToken() {
        TokenUtils.cleanRevokeToken();
    }

}
