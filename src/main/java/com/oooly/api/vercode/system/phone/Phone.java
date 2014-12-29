package com.oooly.api.vercode.system.phone;

import java.util.Date;

/**
 * Created by wangbo on 14-12-29.
 */
public class Phone {

    public static final int NEVER_USE = 0;
    /** 从未使用过 */
    public static final int WAIT_CODE = 1;
    /** 等待验证码 */
    public static final int GET_CODE = 2;
    /** 已获得验证码 */
    public static final int USED = 3;
    /** 已经使用过 */

    private Integer id;
    private Integer userId;
    private String number;
    private Integer status;
    private String code;
    private Date createTime;
    private Date useTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
}
