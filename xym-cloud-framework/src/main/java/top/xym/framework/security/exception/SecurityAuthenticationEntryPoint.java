package top.xym.framework.security.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import top.xym.framework.common.exception.ErrorCode;
import top.xym.framework.common.utils.HttpContextUtils;
import top.xym.framework.common.utils.JsonUtils;
import top.xym.framework.common.utils.Result;

import java.io.IOException;

/**
 * 匿名用户(token不存在、错误)，异常处理器
 *
 * @author mqxu
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

        response.getWriter().print(JsonUtils.toJsonString(Result.error(ErrorCode.UNAUTHORIZED)));
    }
}