package com.example.senstive.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.senstive.senstive.ano.SensitiveField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户登录表
 * </p>
 *
 * @author ehzyil
 * @since 2024-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    List<User> users;
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 第三方用户ID
     */
    @SensitiveField(bind = "thirdAccountId")
    private String thirdAccountId;
    /**
     * 用户名
     */
    @SensitiveField(bind = "userName")
    private String userName;
    /**
     * 密码
     */
    @SensitiveField()
    private String password;
    /**
     * 登录方式: 0-微信登录，1-账号密码登录
     */
    @SensitiveField()
    private Integer loginType;
    /**
     * 是否删除
     */
    private Integer deleted;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

}
