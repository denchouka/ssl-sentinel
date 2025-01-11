package cool.tch.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author denchouka
 * @description 历史表
 * @date 2025/1/11 19:55
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("history")
@AllArgsConstructor
@NoArgsConstructor
public class History implements Serializable {

    private static final long serialVersionUID = 3037050207433476746L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long taskId;

    private Date executeTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}
