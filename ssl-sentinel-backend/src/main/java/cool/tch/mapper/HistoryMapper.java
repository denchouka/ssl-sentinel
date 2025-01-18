package cool.tch.mapper;

import cool.tch.vo.HistoryShowVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author denchouka
 * @description History表 Mapper接口
 * @date 2025/1/11 19:59
 */
@Mapper
public interface HistoryMapper {

    void addHistory(@Param("taskId") long taskId, @Param("executeTime") String executeTime);

    List<HistoryShowVO> listHistory(@Param("taskId") long taskId);
}
