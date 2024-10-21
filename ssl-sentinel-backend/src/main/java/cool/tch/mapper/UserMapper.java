package cool.tch.mapper;

import cool.tch.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author denchouka
 * @description User表 Mapper接口
 * @date 2024/10/20 22:23
 */
@Repository
public interface UserMapper {

    User selectNameAndPassword(@Param("username") String username, @Param("password") String password);
}
