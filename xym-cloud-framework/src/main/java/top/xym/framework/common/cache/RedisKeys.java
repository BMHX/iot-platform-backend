package top.xym.framework.common.cache;

/**
 * Redis Key 管理
 *
 * @author moqi
 */
public class RedisKeys {

    /**
     * 验证码Key
     */
    public static String getCaptchaKey(String key) {
        return "api:captcha:" + key;
    }

    /**
     * accessToken Key
     */
    public static String getAccessTokenKey(String accessToken) {
        return "api:token:" + accessToken;
    }

}