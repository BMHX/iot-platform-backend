package top.xym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xym.dto.PermissionPackageListResponse;
import top.xym.dto.UserProfileResponse;
import top.xym.service.UserProfileService;
import top.xym.dto.PurchasePackageRequestDTO; // 新增导入
import org.springframework.web.bind.annotation.PostMapping; // 新增导入
import org.springframework.web.bind.annotation.RequestBody; // 新增导入

import java.util.Map;

// 假设需要从请求头或安全上下文中获取用户ID，这里用 "X-User-Id" 演示
// import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api") // 根据您的API路径规划，可以调整
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    /**
     * 获取当前登录用户的个人信息
     * 实际项目中，用户ID通常从Token中解析或通过安全上下文获取
     * 这里为了简化，我们先假设通过路径变量传递，或者您可以修改为从请求头获取
     * 例如: @RequestHeader("X-User-Id") Integer userId
     */
    @GetMapping("/user/profile/{userId}") // 或者 /user/profile 如果用户ID从token获取
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Integer userId) {
        UserProfileResponse userProfile = userProfileService.getUserProfile(userId);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        }
        return ResponseEntity.notFound().build(); // 或者返回更具体的错误信息
    }

    /**
     * 获取所有可用的权限套餐列表
     */
    @GetMapping("/permissions/packages")
    public ResponseEntity<PermissionPackageListResponse> getAvailablePackages() {
        PermissionPackageListResponse packages = userProfileService.getAvailablePackages();
        return ResponseEntity.ok(packages);
    }

    @PostMapping("/user/purchase-package")
    public ResponseEntity<?> purchasePackage(@RequestBody PurchasePackageRequestDTO purchasePackageRequestDTO) {
        // TODO: 实现购买套餐的业务逻辑
        // 1. 验证套餐ID和支付方式的有效性
        // 2. 获取当前用户信息（例如从SecurityContextHolder获取）
        // 3. 调用Service层处理购买逻辑，例如创建订单、扣费等
        // 4. 根据处理结果返回相应的ResponseEntity

        System.out.println("Received packageId: " + purchasePackageRequestDTO.getPackageId());
        System.out.println("Received paymentMethod: " + purchasePackageRequestDTO.getPaymentMethod());

        // 示例：返回成功响应
        // 实际应根据业务逻辑返回，例如：
        // return ResponseEntity.ok("套餐购买成功");
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("套餐ID无效");
        return ResponseEntity.ok().body(Map.of("message", "套餐购买请求已收到，正在处理中", "data", purchasePackageRequestDTO));
    }
}
