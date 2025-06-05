package top.xym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xym.dto.LoginDto;
import top.xym.dto.LoginRequestDTO;
import top.xym.dto.UserResponseDTO;
import top.xym.dto.UserDto; // 添加对 UserDto 的导入
import top.xym.framework.common.utils.Result;
import top.xym.exception.LoginFailedException;
import top.xym.exception.UserRegistrationException;
import top.xym.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequestDTO loginRequestDTO) { // 使用 LoginRequestDTO
        try {
            Map<String, Object> loginResult = userService.loginAndGenerateToken(
                    loginRequestDTO.getUsername(),
                    loginRequestDTO.getPassword()
            );
            return Result.ok(loginResult);
        } catch (LoginFailedException e) {
            throw e;
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto) { // 将 User 修改为 UserDto
        try {
            // 注意：userService.register 方法现在应该接受 UserDto 类型的参数
            // 如果 userService.register 期望的是 UserEntity，那么您需要在这里或在服务层进行转换
            // 假设 userService.register 已经更新为接受 UserDto
            UserResponseDTO registeredUserDTO = userService.register(userDto);
            return Result.ok(registeredUserDTO);
        } catch (UserRegistrationException e) {
            throw e;
        }
    }
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            logger.info("登录请求 - 用户名: {}", loginDto.getUsername());

            Map<String, Object> loginResult = userService.loginAndGenerateToken(loginDto);
            String token = (String) loginResult.get("token");

            logger.info("用户认证成功 - 用户ID: {}, 用户名: {}", loginResult.get("userId"), loginResult.get("username"));
            logger.debug("生成的JWT令牌: {}", token);

            return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token)
                .body(loginResult);

        } catch (Exception e) {
            logger.error("登录失败 - 用户名: {}, 原因: {}", loginDto.getUsername(), e.getMessage());
            return ResponseEntity.status(401)
                .body(Map.of(
                    "status", "error",
                    "message", "用户名或密码错误", // 这里的错误消息可以更具体，或者由Service层返回
                    "timestamp", new Date()
                ));
        }
    }
}
