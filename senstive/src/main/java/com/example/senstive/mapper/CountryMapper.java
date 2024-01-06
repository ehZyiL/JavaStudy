package com.example.senstive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.senstive.domain.Country;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ehzyil
 * @since 2024-01-04
 */
@Mapper
public interface CountryMapper extends BaseMapper<Country> {
    Country selectCountryById(int cid);
}
