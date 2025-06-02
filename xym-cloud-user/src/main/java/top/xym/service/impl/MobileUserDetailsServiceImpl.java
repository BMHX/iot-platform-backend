package top.xym.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.xym.convert.UserConvert;
import top.xym.dao.UserDao;
import top.xym.entity.UserEntity;
import top.xym.framework.security.mobile.MobileUserDetailsService;
import top.xym.service.MyUserDetailsService;

@Service
@AllArgsConstructor
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {
    private final MyUserDetailsService myUserDetailsService;
    private final UserDao sysUserDao;
    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        UserEntity userEntity = sysUserDao.getByMobile(mobile);
        if (userEntity == null) {
            throw new UsernameNotFoundException("⼿机号或验证码错误");
        }
        return myUserDetailsService.getUserDetails(UserConvert.INSTANCE.convertDetail(userEntity));
    }
}