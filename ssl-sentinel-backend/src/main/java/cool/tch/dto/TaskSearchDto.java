package cool.tch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author denchouka
 * @description 任务列表检索参数
 * @date 2024/12/25 23:37
 */
@Data
public class TaskSearchDto implements Serializable {

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date ddl;
}
