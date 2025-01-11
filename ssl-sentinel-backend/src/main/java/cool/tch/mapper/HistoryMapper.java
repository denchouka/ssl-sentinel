package cool.tch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author denchouka
 * @description History表 Mapper接口
 * @date 2025/1/11 19:59
 */
@Mapper
public interface HistoryMapper {

    void addHistory(@Param("taskId") long taskId, @Param("executeTime") String executeTime);
}
