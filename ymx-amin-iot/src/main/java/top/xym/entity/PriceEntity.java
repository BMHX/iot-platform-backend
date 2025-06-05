package top.xym.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "prices") // 假设表名为 prices，请根据实际情况修改
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "permission_name")
    private String permissionName;

    private Integer price;

    // 存储类似 "{1,2,3,4}" 的字符串
    private String card;

    // Getters and Setters, Constructors, etc. (Lombok @Data handles this)
}
