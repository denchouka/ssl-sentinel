package cool.tch.mapper;

import cool.tch.entity.Task;
import cool.tch.vo.TaskSearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author denchouka
 * @description Task表 Mapper接口
 * @date 2024/11/29 20:37
 */
@Mapper
public interface TaskMapper {

    void addTask(Task task);

    List<TaskSearchVO> list(@Param("domainName") String domainName, @Param("status") Integer status, @Param("ddl") Date ddl);
}
