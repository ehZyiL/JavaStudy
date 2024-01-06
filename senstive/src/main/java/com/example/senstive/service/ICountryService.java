package com.example.senstive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.senstive.domain.Country;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ehzyil
 * @since 2024-01-04
 */
public interface ICountryService extends IService<Country> {
    Country selectCountryById(int cid);
}
