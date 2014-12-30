package com.oooly.api.vercode.system.phone;

import com.google.common.collect.ImmutableMap;
import com.oooly.api.vercode.system.AbstractService;
import com.oooly.api.vercode.system.user.User;
import com.oooly.api.vercode.system.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangbo on 14-12-29.
 */
@Service
public class PhoneService extends AbstractService {

    @Resource
    private UserService userService;

    public boolean checkToken(String token, String number) {
        int result = sqlSession.selectOne("phone.checkCount", ImmutableMap.of("token", token, "number", number));
        return result >= 1;
    }

    @Transactional
    public int addNumbers(String token, List<String> numList) {
        if (!userService.check(token, User.CARD)) {
            return 0;
        }
        int count = 0;
        int userId = userService.getUserId(token);
        for (String num : numList) {
            Phone phone = new Phone();
            phone.setStatus(Phone.NEVER_USE);
            phone.setNumber(num);
            phone.setUserId(userId);
            phone.setCreateTime(new Date());
            phone.setCode("");
            count += sqlSession.insert("phone.insert", phone);
        }

        return count;
    }

    @Transactional
    public int skipNumbers(String token, List<String> numList) {
        if (!userService.check(token, User.CARD)) {
            return 0;
        }
        int count = 0;
        for (String num : numList) {
            if (!checkToken(token, num)) {
                continue;
            }
            Phone phone = sqlSession.selectOne("phone.selectUpdate", ImmutableMap.of("number", num));
            phone.setStatus(Phone.GET_CODE);
            count += sqlSession.update("phone.update", phone);
        }
        return count;
    }

    @Transactional
    public boolean setCode(String token, String number, String code) {
        if (!userService.check(token, User.CARD)) {
            return false;
        }
        if (!checkToken(token, number)) {
            return false;
        }
        System.out.println("haha");
        Phone phone = sqlSession.selectOne("phone.selectUpdate", ImmutableMap.of("number", number));
        phone.setCode(code);
        phone.setStatus(Phone.GET_CODE);
        sqlSession.update("phone.update", phone);
        return true;
    }

    @Transactional
    public String getNumber(String token) {
        if (!userService.check(token, User.APP)) {
            return "";
        }
        Phone phone = sqlSession.selectOne("phone.selectOne", ImmutableMap.of("status", Phone.NEVER_USE));
        phone.setStatus(Phone.WAIT_CODE);
        sqlSession.update("phone.update", phone);
        return phone.getNumber();
    }

    public Phone getPhone(String token, String number) {
        if (!userService.check(token, User.APP)) {
            return null;
        }
        return sqlSession.selectOne("phone.select", ImmutableMap.of("number", number));
    }

    public List<String> getCodeNumbers(String token) {
        if (!userService.check(token, User.CARD)) {
            return null;
        }
        return sqlSession.selectList("phone.selectNumber", ImmutableMap.of("token", token, "status", Phone.WAIT_CODE));
    }
}
