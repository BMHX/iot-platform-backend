package top.xym.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import top.xym.enums.UserStatusEnum;
import top.xym.framework.security.user.UserDetail;
import top.xym.service.MyUserDetailsService;

import java.util.Collections;

@Service
@AllArgsConstructor
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    @Override
    public UserDetails getUserDetails(UserDetail userDetail) {
// 账号不可⽤
        if (userDetail.getStatus() == UserStatusEnum.DISABLE.getValue()) {
            userDetail.setEnabled(false);
        }
// 初始化为空集合，避免后续调⽤ stream() 抛出异常
        userDetail.setAuthoritySet(Collections.emptySet());
        return userDetail;
    }
}