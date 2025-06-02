package top.xym.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.xym.convert.UserConvert;
import top.xym.dto.AccountLoginDTO;
import top.xym.dto.MobileLoginDTO;
import top.xym.entity.UserEntity;
import top.xym.framework.common.exception.ServerException;
import top.xym.framework.security.cache.TokenStoreCache;
import top.xym.framework.security.mobile.MobileAuthenticationToken;
import top.xym.framework.security.user.UserDetail;
import top.xym.framework.security.utils.JwtUtil;
import top.xym.service.AuthService;
import top.xym.service.UserService;
import top.xym.vo.AccountLoginVO;
import top.xym.vo.MobileLoginVO;
import top.xym.vo.UserVO;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenStoreCache tokenStoreCache;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AccountLoginVO loginByAccount(AccountLoginDTO login) {
        Authentication authentication;
        try {
// ⽤户认证
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ServerException("⽤户名或密码错误");
        }
// ⽤户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();
// ⽣成 accessToken
        String accessToken = JwtUtil.createToken(user.getId());
// 保存⽤户信息到缓存
        tokenStoreCache.saveUser(accessToken, user);
        AccountLoginVO accountLoginVO = new AccountLoginVO();
        accountLoginVO.setId(user.getId());
        accountLoginVO.setAccessToken(accessToken);
        accountLoginVO.setUsername(user.getUsername());
        return accountLoginVO;
    }
    @Override
    public MobileLoginVO loginByMobile(MobileLoginDTO login) {
        UserVO userVO = userService.getByMobile(login.getMobile());
        if (userVO == null) {
            UserEntity entity = UserConvert.INSTANCE.convert(login);
            entity.setUsername(login.getMobile());
            entity.setPassword(passwordEncoder.encode("123456"));
            entity.setNickname("新⽤户");
            entity.setAvatar("https://mqxu-oss.oss-cn-hangzhou.aliyuncs.co m/avatar/1.jpg");
                    userService.save(entity);
        }
        Authentication authentication;
        try {
// ⽤户认证
            authentication = authenticationManager.authenticate(new MobileAuthenticationToken(login.getMobile(), login.getCode()));
        } catch (BadCredentialsException e) {
            throw new ServerException("⼿机号或验证码错误");
        }
// ⽤户信息
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
// ⽣成 accessToken
        String accessToken = JwtUtil.createToken(userDetail.getId());
// 保存⽤户信息到缓存
        tokenStoreCache.saveUser(accessToken, userDetail);
        MobileLoginVO mobileLoginVO = new MobileLoginVO();
        mobileLoginVO.setId(userDetail.getId());
        mobileLoginVO.setAccessToken(accessToken);
        mobileLoginVO.setMobile(userVO.getMobile());
        return mobileLoginVO;
    }
    @Override
    public void logout(String accessToken) {
// 删除⽤户信息
        tokenStoreCache.deleteUser(accessToken);
    }
}