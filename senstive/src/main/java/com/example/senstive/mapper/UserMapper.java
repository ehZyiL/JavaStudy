package com.example.senstive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.senstive.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户登录表 Mapper 接口
 * </p>
 *
 * @author ehzyil
 * @since 2024-01-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user")
    List<User> getAllUsers();

    @Insert("  INSERT INTO user (id,third_account_id, user_name, password, login_type, deleted, create_time, update_time)\n" +
            "        VALUES (#{id},#{thirdAccountId}, #{userName}, #{password}, #{loginType}, #{deleted}, #{createTime}, #{updateTime})\n")
    int insertU(User user);
//    @Select("select * from user where id =#{id}")
//    User getUser(Integer id);

    User getUser(Integer id);
}
