package top.xym.service;

import org.springframework.security.core.userdetails.UserDetails;
import top.xym.framework.security.user.UserDetail;

public interface MyUserDetailsService {
    UserDetails getUserDetails(UserDetail userDetail);
}