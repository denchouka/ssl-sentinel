package cool.tch.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author denchouka
 * @description 任务检索结果信息
 * @date 2024/12/25 23:31
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TaskSearchVO implements Serializable {

    private static final long serialVersionUID = 6379448169136101895L;

    private Long id;

    private String domainName;

    private String applicationPlatform;

    private String usagePlatform;

    private String purpose;

    private String ddl;

    private String date;

    private String email;

    private String content;

    private String remark;

    private int status;

}
