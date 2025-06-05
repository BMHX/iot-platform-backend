package top.xym.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private String identity;

    @Column(name = "permission_id")
    private Integer permissionId;

    private String name;

    private String email;

    private String phone;

    private String status;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;
} 