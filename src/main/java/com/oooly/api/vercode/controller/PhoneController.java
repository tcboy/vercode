package com.oooly.api.vercode.controller;

import javax.annotation.Resource;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.oooly.api.vercode.system.phone.Phone;
import com.oooly.api.vercode.system.phone.PhoneService;
import com.oooly.api.vercode.common.CodeMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/vercode")
public class PhoneController {

    /**
     * 成功返回0, 失败返回1.. 多个数据之间用|分隔 eg: 0|12345
     */

    private Splitter splitter = Splitter.on(',');

    @Resource
    private PhoneService phoneService;

    /**
     * 添加号码到数据库 numbers 包含一个或多个号码, 使用,隔开
     */
    @RequestMapping(value = "addNumbers", method = RequestMethod.POST)
    @ResponseBody
    public String addNumbers(@RequestParam("token") String token, @RequestParam("numbers") String numbers) {
        List<String> numList = splitter.trimResults().splitToList(numbers);
        int result = phoneService.addNumbers(token, numList);
        if (result > 0) {
            return new CodeMessage(0, String.valueOf(result), "添加成功!").toString();
        }
        return new CodeMessage(1, String.valueOf(result), "添加失败!").toString();
    }

    /**
     * 跳过号码 numbers 包含一个或多个号码, 使用,隔开
     */
    @RequestMapping(value = "skipNumbers", method = RequestMethod.POST)
    @ResponseBody
    public String skipNumbers(@RequestParam("token") String token, @RequestParam("numbers") String numbers) {
        List<String> numList = splitter.trimResults().splitToList(numbers);
        int result = phoneService.skipNumbers(token, numList);
        if (result > 0) {
            return new CodeMessage(0, String.valueOf(result), "跳过成功!").toString();
        }
        return new CodeMessage(1, String.valueOf(result), "跳过失败!").toString();
    }

    /**
     * 设置验证码 number 一个正在等待验证码的号码 code 验证码
     */
    @RequestMapping(value = "setCode", method = RequestMethod.POST)
    @ResponseBody
    public String setCode(@RequestParam("token") String token, @RequestParam("number") String number,
            @RequestParam("code") String code) {
        if (phoneService.setCode(token, number, code)) {
            return new CodeMessage(0, "设置验证码成功!").toString();
        }
        return new CodeMessage(1, "设置验证码失败!").toString();
    }

    /**
     * 获取一个未使用过的号码
     */
    @RequestMapping(value = "getNumber", method = RequestMethod.POST)
    @ResponseBody
    public String getNumber(@RequestParam("token") String token) {
        String number = phoneService.getNumber(token);
        if (!Strings.isNullOrEmpty(number)) {
            return new CodeMessage(0, number, "获取号码成功!").toString();
        }
        return new CodeMessage(1, "", "获取号码失败!").toString();
    }

    /**
     * 获取一个号码的验证码
     */
    @RequestMapping(value = "getCode", method = RequestMethod.GET)
    @ResponseBody
    public String getCode(@RequestParam("token") String token, @RequestParam("number") String number) {
        Phone phone = phoneService.getPhone(token, number);
        if (phone == null) {
            return new CodeMessage(1, "", "", "该号码不存在,或Token错误!").toString();
        }
        if (phone.getStatus() >= Phone.GET_CODE) {
            return new CodeMessage(0, number, String.valueOf(phone.getStatus()), phone.getCode(), "获取验证码成功!").toString();
        }
        return new CodeMessage(1, number, String.valueOf(phone.getStatus()), phone.getCode(), "获取验证码失败!").toString();
    }

    /**
     * 获取待输入验证码的手机号码 结果用|分割
     */
    @RequestMapping(value = "getCodeNumbers", method = RequestMethod.GET)
    @ResponseBody
    public String getCodeNumbers(@RequestParam("token") String token) {
        List<String> numbers = phoneService.getCodeNumbers(token);
        if (numbers != null) {
            return new CodeMessage(0, numbers).toString();
        }
        return new CodeMessage(1).toString();
    }
}