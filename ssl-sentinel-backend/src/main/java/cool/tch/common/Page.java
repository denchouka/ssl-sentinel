package cool.tch.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author denchouka
 * @description 分页的共通参数
 * @date 2025/1/7 21:40
 */
@Data
public class Page implements Serializable {

    private static final long serialVersionUID = -4034673047498691910L;

    /**
     * 页码
     */
    protected int pageNum;

    /**
     *  每页显示数量
     */
    protected int pageSize;
}
