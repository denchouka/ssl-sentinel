package cool.tch.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author denchouka
 * @description 任务表
 * @date 2024/11/28 22:42
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {

    private static final long serialVersionUID = 2582729940081239201L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Date ddl;

    private Date date;

    private String email;

    private String content;

    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
