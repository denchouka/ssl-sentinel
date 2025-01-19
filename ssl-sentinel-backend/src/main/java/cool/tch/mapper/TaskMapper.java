package cool.tch.mapper;

import cool.tch.entity.Task;
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

    List<Task> list(@Param("domainName") String domainName, @Param("status") Integer status, @Param("ddl") Date ddl);

    List<Task> executeList(@Param("notStarted") int notStarted, @Param("inProgress") int inProgress);

    void updateTaskStatusById(@Param("id") Long id, @Param("status") int status);;

    Task selectById(@Param("id") Long id);

    void editTask(Task task);
}
