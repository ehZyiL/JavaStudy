package com.example.multidatasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.multidatasource.domain.Admin;
import com.example.multidatasource.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户登录表 Mapper 接口
 * </p>
 *
 * @author ehzyil
 * @since 2023-12-29
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from admin ")
    List<User> getAllAdmins();
}
