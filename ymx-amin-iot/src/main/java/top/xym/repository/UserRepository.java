package top.xym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.xym.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // 根据用户名查找用户，用于登录等场景
    Optional<UserEntity> findByUsername(String username);

    // 根据用户ID查找用户，可以用于获取用户信息
    // JpaRepository 默认提供了 findById(ID id) 方法，所以这里不需要额外定义
    // Optional<UserEntity> findById(Integer id);
}

