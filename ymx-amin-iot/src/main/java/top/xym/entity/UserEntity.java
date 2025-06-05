package top.xym.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "admin")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private LocalDateTime createdtime;

    private String identity;

    @Column(name = "permission_id")
    private Integer permissionId;

    // Getters and Setters, Constructors, etc. (Lombok @Data handles this)
}
