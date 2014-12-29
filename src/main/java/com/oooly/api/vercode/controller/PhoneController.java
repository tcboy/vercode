package com.oooly.api.vercode.controller;

import javax.annotation.Resource;

import com.oooly.api.vercode.system.phone.PhoneService;
import com.oooly.api.vercode.common.CodeMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/vercode")
public class PhoneController {

    /**
        成功返回0, 失败返回1..
        多个数据之间用|分隔
        eg: 0|12345
     */

    @Resource
    private PhoneService phoneService;

    /**
     * 添加号码到数据库
     * numbers 包含一个或多个号码, 使用换行或者,隔开
     */
    @RequestMapping(value = "addNumbers", method = RequestMethod.POST)
    @ResponseBody
    public String addNumbers(@RequestParam("numbers") String numbers) {

        return "0";
    }

    /**
     * 跳过号码
     * numbers 包含一个或多个号码, 使用换行或者,隔开
     */
    @RequestMapping(value = "skipNumbers", method = RequestMethod.POST)
    @ResponseBody
    public String skipNumbers(@RequestParam("numbers") String numbers) {

        return "0";
    }

    /**
     * 设置验证码
     * number 一个正在等待验证码的号码
     * code 验证码
     */
    @RequestMapping(value = "setCode", method = RequestMethod.POST)
    @ResponseBody
    public String setCode(@RequestParam("number") String number, @RequestParam("code") String code) {

        return "0";
    }

    /**
     * 获取一个未使用过的号码
     */
    @RequestMapping(value = "getNumber", method = RequestMethod.POST)
    @ResponseBody
    public String getNumber() {

        return "0";
    }

    /**
     * 获取一个号码的验证码
     */
    @RequestMapping(value = "getCode", method = RequestMethod.POST)
    @ResponseBody
    public String getCode(@RequestParam("number") String number) {

        return "0";
    }

    /**
     * 获取待输入验证码的手机号码
     * 结果用|分割
     */
    @RequestMapping(value = "getCodeNumbers", method = RequestMethod.GET)
    @ResponseBody
    public String getCodeNumbers() {

        return new CodeMessage(0, "123", "134").toString();
    }
}