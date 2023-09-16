package com.ehzyil.config;


import com.ehzyil.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//实现Security提供的WebSecurityConfigurerAdapter类，就可以改变密码校验的规则了
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    //注入我们在filter目录写好的类
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    //注入Security提供的认证失败的处理器，这个处理器里面的AuthenticationEntryPointImpl实现类，用的不是官方的了，
    //而是用的是我们在handler目录写好的AuthenticationEntryPointImpl实现类
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    //注入Security提供的授权失败的处理器，这个处理器里面的AccessDeniedHandlerImpl实现类，用的不是官方的了，
    //而是用的是我们在handler目录写好的AccessDeniedHandlerImpl实现类
    private AccessDeniedHandler accessDeniedHandler;

    //---------------------------认证过滤器的实现----------------------------------

    @Bean
    //把BCryptPasswordEncoder对象注入Spring容器中，SpringSecurity就会使用该PasswordEncoder来进行密码校验
    //注意也可以注入PasswordEncoder，效果是一样的，因为PasswordEncoder是BCry..的父类
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //---------------------------登录接口的实现----------------------------------

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //由于是前后端分离项目，所以要关闭csrf
                .csrf().disable()
                //由于是前后端分离项目，所以session是失效的，我们就不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //指定让spring security放行登录接口的规则
                .authorizeRequests()
                // 对于登录接口 anonymous表示允许匿名访问
                .antMatchers("/user/login").anonymous()

                //基于配置的的权限控制。指定接口的地址，为HelloController类里面的/configAuth接口，指定权限为system:dept:list
                .antMatchers("/configAuth").hasAuthority("system:dept:list")
                //上一行的hasAuthority方法就是security官方提供的4种权限控制的方法之一

                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //---------------------------认证过滤器的实现----------------------------------

        //把token校验过滤器添加到过滤器链中
        //第一个参数是上面注入的我们在filter目录写好的类，第二个参数表示你想添加到哪个过滤器之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //---------------------------异常处理的相关配置-------------------------------

        http.exceptionHandling()
                //配置认证失败的处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                //配置授权失败的处理器
                .accessDeniedHandler(accessDeniedHandler);
        //---------------------------👇 设置security运行跨域访问 👇------------------

        http.cors();
    }
}