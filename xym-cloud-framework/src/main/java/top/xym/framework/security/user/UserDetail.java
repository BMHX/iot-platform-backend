package top.xym.framework.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登录用户信息
 *
 * @author mqxu
 */
@Data
public class UserDetail implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private Integer gender;
    private String email;
    private String mobile;
    private Integer status;
    private LocalDateTime createTime;

    // 新增构造函数以匹配 JwtAuthenticationFilter.java 中的调用
    public UserDetail(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password; // 注意：在 JwtAuthenticationFilter 中传递的是 null
        if (authorities != null) {
            this.authoritySet = authorities.stream()
                                          .map(GrantedAuthority::getAuthority)
                                          .collect(Collectors.toSet());
        } else {
            this.authoritySet = Set.of(); // 或者根据业务逻辑设置为一个空集合
        }
        // 根据需要初始化其他字段的默认值
    }

    /**
     * 帐户是否过期
     */
    private boolean isAccountNonExpired = true;
    /**
     * 帐户是否被锁定
     */
    private boolean isAccountNonLocked = true;
    /**
     * 密码是否过期
     */
    private boolean isCredentialsNonExpired = true;
    /**
     * 帐户是否可用
     */
    private boolean isEnabled = true;
    /**
     * 拥有权限集合
     */
    private Set<String> authoritySet;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritySet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}