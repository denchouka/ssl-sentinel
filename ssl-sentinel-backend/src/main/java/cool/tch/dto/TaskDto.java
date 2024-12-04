package cool.tch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author denchouka
 * @description 任务Dto
 * @date 2024/11/29 20:07
 */
@Data
public class TaskDto implements Serializable {

    private static final long serialVersionUID = 3490539871833448081L;

    @NotBlank(message = "名称不能为空")
    @Length(min = 3, max = 10, message = "名称至少3位，最大10位")
    private String name;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @FutureOrPresent(message = "过期日期必须是以后或现在的日期")
    private Date ddl;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @FutureOrPresent(message = "提醒日期必须是以后或现在的日期")
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
