package cool.tch.mapper;

import cool.tch.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author denchouka
 * @description User表 Mapper接口
 * @date 2024/10/20 22:23
 */
@Mapper
public interface UserMapper {

    User selectUser(@Param("username") String username);
}
