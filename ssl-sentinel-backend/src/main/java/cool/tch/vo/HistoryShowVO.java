package cool.tch.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author denchouka
 * @description 任务执行历史显示
 * @date 2025/1/16 23:00
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class HistoryShowVO implements Serializable {

    private static final long serialVersionUID = 7878891192843011126L;

    private Long id;

    private Long taskId;

    private Date executeTime;
}
