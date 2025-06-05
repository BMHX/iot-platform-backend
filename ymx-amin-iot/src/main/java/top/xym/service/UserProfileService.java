package top.xym.service;

import top.xym.dto.PermissionPackageListResponse;
import top.xym.dto.UserProfileResponse;

public interface UserProfileService {

    /**
     * 根据用户ID获取用户个人信息，包括当前套餐信息
     * @param userId 用户ID
     * @return 用户个人信息及套餐
     */
    UserProfileResponse getUserProfile(Integer userId);

    /**
     * 获取所有可用的权限套餐列表
     * @return 套餐列表
     */
    PermissionPackageListResponse getAvailablePackages();
}
