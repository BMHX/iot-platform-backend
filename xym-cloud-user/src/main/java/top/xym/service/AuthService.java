package top.xym.service;

import top.xym.dto.AccountLoginDTO;
import top.xym.dto.MobileLoginDTO;
import top.xym.vo.AccountLoginVO;
import top.xym.vo.MobileLoginVO;

public interface AuthService {
    /**
     * 账号密码登录
     *
     * @param login 登录信息
     */
    AccountLoginVO loginByAccount(AccountLoginDTO login);
    /**
     * ⼿机短信登录
     *
     * @param login 登录信息
     */
    MobileLoginVO loginByMobile(MobileLoginDTO login);
    /**
     * 退出登录
     *
     * @param accessToken accessToken
     */
    void logout(String accessToken);
}