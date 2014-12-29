package com.oooly.api.vercode.system.user;

import java.util.Date;

/**
 * Created by wangbo on 14-12-29.
 */
public class User {

    public static final Integer CARD = 0;
    /** 卡商标识 */
    public static final Integer APP = 1;
    /** APP用户标识 */

    private Integer id;
    private String name;
    private String token;
    private Integer type;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
