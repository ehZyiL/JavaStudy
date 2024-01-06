package com.example.senstive.controller;


import com.example.senstive.domain.Country;
import com.example.senstive.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ehzyil
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private ICountryService countryService;
    ;

    @RequestMapping("/{id}")
    public Country query(@PathVariable("id") Integer id) {
        return countryService.selectCountryById(id);
    }
}
