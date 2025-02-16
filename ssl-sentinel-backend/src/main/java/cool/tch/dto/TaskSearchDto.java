package cool.tch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cool.tch.common.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static cool.tch.common.Constant.FORMATTER_DATE;
import static cool.tch.common.Constant.ZONEID_SHANGHAI;

/**
 * @author denchouka
 * @description 任务列表检索参数
 * @date 2024/12/25 23:37
 */
@Data
public class TaskSearchDto extends Page implements Serializable {

    private static final long serialVersionUID = 3253421122061335306L;

    /**
     * 域名
     */
    private String domainName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 过期时间
     */
    @JsonFormat(pattern = FORMATTER_DATE, timezone = ZONEID_SHANGHAI)
    private Date ddl;
}
