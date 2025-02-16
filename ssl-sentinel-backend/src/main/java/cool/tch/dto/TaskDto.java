package cool.tch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cool.tch.annotations.BeforeThanTargetDate;
import cool.tch.annotations.FutureOrPresentDay;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

import static cool.tch.common.Constant.FORMATTER_DATE;
import static cool.tch.common.Constant.ZONEID_SHANGHAI;


/**
 * @author denchouka
 * @description 任务Dto
 * @date 2024/11/29 20:07
 */
@Data
@BeforeThanTargetDate(message = "提醒日期必须早于过期日期", checkedField = "date", targetFields = {"ddl"})
public class TaskDto implements Serializable {

    private static final long serialVersionUID = 3490539871833448081L;

    private Long id;

    @NotBlank(message = "域名不能为空")
    @Length(min = 4, max = 20, message = "域名至少3位，最大10位")
    private String domainName;

    @NotBlank(message = "申请平台不能为空")
    @Length(min = 3, max = 10, message = "申请平台至少3位，最大10位")
    private String applicationPlatform;

    @NotBlank(message = "使用平台不能为空")
    @Length(min = 3, max = 10, message = "使用平台至少3位，最大10位")
    private String usagePlatform;

    @NotBlank(message = "用途")
    @Length(min = 10, max = 100, message = "用途至少10位，最大100位")
    private String purpose;

    @NotNull
    @JsonFormat(pattern = FORMATTER_DATE, timezone = ZONEID_SHANGHAI)
    @FutureOrPresentDay(message = "过期日期必须是以后或现在的日期")
    private Date ddl;

    @NotNull
    @JsonFormat(pattern = FORMATTER_DATE, timezone = ZONEID_SHANGHAI)
    @FutureOrPresentDay(message = "提醒日期必须是以后或现在的日期")
    private Date date;

    @Email(message = "邮箱地址不合法")
    @NotBlank(message = "邮箱地址不能为空")
    @Length(max = 50, message = "邮箱地址最大50位")
    private String email;

    @NotBlank(message = "提醒内容不能为空")
    @Length(min = 10, max = 100, message = "提醒内容至少3位，最大10位")
    private String content;

    @Length(max = 100, message = "提醒内容最大10位")
    private String remark;
}
