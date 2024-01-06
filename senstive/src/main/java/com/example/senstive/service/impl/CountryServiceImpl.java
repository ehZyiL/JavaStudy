package com.example.senstive.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.senstive.domain.Country;
import com.example.senstive.mapper.CountryMapper;
import com.example.senstive.service.ICountryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ehzyil
 * @since 2024-01-04
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements ICountryService {

    @Override
    public Country selectCountryById(int cid) {
        return getBaseMapper().selectCountryById(cid);
    }
}
