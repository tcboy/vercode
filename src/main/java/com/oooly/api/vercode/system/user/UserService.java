package com.oooly.api.vercode.system.user;

import com.google.common.collect.ImmutableMap;
import com.oooly.api.vercode.system.AbstractService;
import org.springframework.stereotype.Service;

/**
 * Created by wangbo on 14-12-29.
 */
@Service
public class UserService extends AbstractService {

    public boolean check(String token, Integer type) {
        int count = sqlSession.selectOne("user.selectCount", ImmutableMap.of("token", token, "type", type));
        return count >= 1;
    }

    public int getUserId(String token) {
        User user = sqlSession.selectOne("user.select", ImmutableMap.of("token", token));
        return user.getId();
    }
}
