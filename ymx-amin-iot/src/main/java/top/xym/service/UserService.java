package top.xym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import top.xym.dto.LoginDto;
import top.xym.dto.UserDto;
import top.xym.dto.UserResponseDTO;
import top.xym.exception.LoginFailedException;
import top.xym.exception.UserRegistrationException;
import top.xym.framework.security.user.UserDetail;
import top.xym.repository.UserRepository;
import top.xym.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import top.xym.entity.UserEntity; // 确保导入 UserEntity
import java.util.Optional; // 确保导入 Optional

@Slf4j
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO register(UserDto userDto) {
        // 检查用户名是否已存在
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserRegistrationException("用户名已存在");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // 设置默认权限为普通用户(permissionId=1)
        userEntity.setPermissionId(2);
        userEntity.setCreatedtime(java.time.LocalDateTime.now()); // 取消此行的注释
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return convertToUserResponseDTO(savedUserEntity); // 需要一个将 UserEntity 转换为 UserResponseDTO 的方法
    }

    // 新增的登录方法，处理认证和Token生成
    public Map<String, Object> loginAndGenerateToken(LoginDto loginDto) {
        UserEntity userEntity = getUserByUsername(loginDto.getUsername());

        // 验证密码
        if (!passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            throw new LoginFailedException("用户名或密码错误");
        }
        log.info("UserService.loginAndGenerateToken - User loaded: ID={}, Username={}", userEntity.getId(), userEntity.getUsername());

        // 生成token
        // 注意：UserDetail 可能需要 User 类型的参数，或者您需要调整 UserDetail 的构造函数以接受 UserEntity
        // 这里假设 UserDetail 可以接受 UserEntity 的属性，或者您有一个转换方法
        UserDetail userDetailForToken = new UserDetail(
            userEntity.getId() != null ? userEntity.getId().longValue() : null, // 将 Integer 转换为 Long，并添加null检查
            userEntity.getUsername(),
            userEntity.getPassword(),
            getAuthoritiesFromUserEntity(userEntity) // 您需要一个方法从 UserEntity 获取权限
        );
        log.info("UserService.loginAndGenerateToken - UserDetail for token: ID={}, Username={}", userDetailForToken.getId(), userDetailForToken.getUsername());
        String token = TokenUtils.generateToken(userDetailForToken);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userEntity.getId());
        response.put("username", userEntity.getUsername());
        response.put("token", token);
        response.put("authorities", getAuthoritiesFromUserEntity(userEntity)); // 同上
        return response;
    }

    // 这个 register 方法的参数类型是 top.lh.User，如果 top.lh.User 和 UserEntity 不同，
    // 你需要将 top.lh.User 转换为 UserEntity 再保存
    // public UserResponseDTO register(User user) { // 假设这个 User 是 top.lh.User
    //     if (userRepository.findByUsername(user.getUsername()).isPresent()) {
    //         throw new UserRegistrationException("用户名已存在");
    //     }
    //     UserEntity userEntity = convertUserToUserEntity(user); // 你需要实现这个转换方法
    //     userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    //     UserEntity savedUserEntity = userRepository.save(userEntity);
    //     return convertToUserResponseDTO(savedUserEntity);
    // }

    public Map<String, Object> loginAndGenerateToken(String username, String password) {
        UserEntity userEntity = getUserByUsername(username);

        // 验证密码
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new LoginFailedException("用户名或密码错误");
        }
        log.info("UserService.loginAndGenerateToken (String, String) - User loaded: ID={}, Username={}", userEntity.getId(), userEntity.getUsername());

        // 生成token
        UserDetail userDetailForToken = new UserDetail(
            userEntity.getId().longValue(), // 将 Integer 转换为 Long
            userEntity.getUsername(),
            userEntity.getPassword(),
            getAuthoritiesFromUserEntity(userEntity) // 您需要一个方法从 UserEntity 获取权限
        );
        log.info("UserService.loginAndGenerateToken (String, String) - UserDetail for token: ID={}, Username={}", userDetailForToken.getId(), userDetailForToken.getUsername());
        String token = TokenUtils.generateToken(userDetailForToken);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "登录成功");
        result.put("token", token);
        return result;
    }


    // 修改 convertToUserResponseDTO 以接受 UserEntity
    private UserResponseDTO convertToUserResponseDTO(UserEntity userEntity) {
        UserResponseDTO dto = new UserResponseDTO();
        if (userEntity.getId() != null) { // 添加 null 检查
            dto.setId(userEntity.getId().longValue()); // 将 Integer 转换为 Long
        } else {
            dto.setId(null); // 或者根据业务逻辑设置一个默认值或抛出异常
        }
        dto.setUsername(userEntity.getUsername());
        if (userEntity.getIdentity() != null) {
            try {
                dto.setIdentity(Integer.parseInt(userEntity.getIdentity()));
            } catch (NumberFormatException e) {
                log.warn("Failed to parse identity '{}' for user '{}' to Integer. Setting to default 0.", userEntity.getIdentity(), userEntity.getUsername(), e);
                dto.setIdentity(0);
            }
        } else {
            dto.setIdentity(0);
        }
        dto.setPermissionId(userEntity.getPermissionId() != null ? userEntity.getPermissionId().longValue() : 1L);
        // dto.setCreatedtime(new Date()); // UserEntity 中的 createdtime 是 LocalDateTime，需要转换
        if (userEntity.getCreatedtime() != null) {
            dto.setCreatedtime(java.sql.Timestamp.valueOf(userEntity.getCreatedtime()));
        } else {
            dto.setCreatedtime(new Date()); // 或者根据业务设置默认值
        }
        return dto;
    }

    // 修改 getUserByUsername 以返回 UserEntity 并处理 Optional
    public UserEntity getUserByUsername(String username) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }
        return userEntityOptional.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = getUserByUsername(username); // 现在返回 UserEntity
        log.info("UserService.loadUserByUsername - User loaded from DB: ID={}, Username={}", userEntity.getId(), userEntity.getUsername());

        UserDetail userDetail = new UserDetail(
                userEntity.getId() != null ? userEntity.getId().longValue() : null, // 将 Integer 转换为 Long，并添加null检查
                userEntity.getUsername(),
                userEntity.getPassword(),
                getAuthoritiesFromUserEntity(userEntity) // 你需要一个方法从 UserEntity 获取权限
        );
        log.info("UserService.loadUserByUsername - Returning UserDetail: ID={}, Username={}", userDetail.getId(), userDetail.getUsername());
        return userDetail;
    }

    // 你需要一个辅助方法将 User (top.lh.User) 转换为 UserEntity
    // 这个方法的具体实现取决于 top.lh.User 的结构
    // private UserEntity convertUserToUserEntity(User user) {
    //     if (user == null) return null;
    //     UserEntity userEntity = new UserEntity();
    //     if (user.getId() != null) { // 添加null检查
    //         userEntity.setId(user.getId().intValue()); // 将 Long 转换为 Integer
    //     }
    //     userEntity.setUsername(user.getUsername());
    //     // userEntity.setPassword(user.getPassword()); // 密码通常在调用方处理加密
    //     userEntity.setIdentity(user.getIdentity()); // 假设 User 有 getIdentity()
    //     if (user.getPermissionId() != null) {
    //         userEntity.setPermissionId(user.getPermissionId()); // 类型匹配，无需转换
    //     }
    //     // userEntity.setCreatedtime(...); // 根据需要设置
    //     return userEntity;
    // }

    // 你需要一个辅助方法从 UserEntity 获取权限信息，用于 UserDetail
    // 这个方法的具体实现取决于你的权限模型
    private java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthoritiesFromUserEntity(UserEntity userEntity) {
        // 示例：如果权限ID直接映射到角色名
        // return java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER_" + userEntity.getPermissionId()));
        // 或者如果你的 UserEntity 有一个 getRoles() 方法返回权限集合
        // return userEntity.getRoles();
        // 这里返回一个空列表作为占位符，你需要根据实际情况实现
        if (userEntity.getPermissionId() == null) {
            return java.util.Collections.emptyList();
        }
        // 假设 permissionId = 1 是普通用户，permissionId = 2 是管理员等
        // 你可能需要一个更复杂的逻辑来映射 permissionId 到 GrantedAuthority
        String role = "ROLE_UNKNOWN";
        if (userEntity.getPermissionId() == 1) {
            role = "ROLE_USER";
        } else if (userEntity.getPermissionId() == 2) {
            role = "ROLE_ADMIN";
        }
        // ... 其他权限映射
        return java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority(role));
    }
}
