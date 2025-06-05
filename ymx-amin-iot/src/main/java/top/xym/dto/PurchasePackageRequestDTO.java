package top.xym.dto;

import lombok.Data;

@Data
public class PurchasePackageRequestDTO {
    private String packageId; // 套餐ID
    private String paymentMethod; // 支付方式
}
