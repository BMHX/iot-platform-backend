package top.xym.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.xym.convert.UserConvert;
import top.xym.dao.UserDao;
import top.xym.entity.UserEntity;
import top.xym.service.MyUserDetailsService;

// 账号登录
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MyUserDetailsService myUserDetailsService;
    private final UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.getByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("⽤户名或密码错误");
        }
        return myUserDetailsService.getUserDetails(UserConvert.INSTANCE.convertDetail(userEntity));
    }
}