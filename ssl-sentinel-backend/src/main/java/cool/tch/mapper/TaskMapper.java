package cool.tch.mapper;

import cool.tch.dto.TaskDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author denchouka
 * @description Task表 Mapper接口
 * @date 2024/11/29 20:37
 */
@Mapper
public interface TaskMapper {

    Integer addTask(TaskDto taskDto);
}
