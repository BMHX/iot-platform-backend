package top.xym.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xym.dto.PackageInfo;
import top.xym.dto.PermissionPackageListResponse;
import top.xym.dto.UserProfileResponse;
import top.xym.entity.PriceEntity;
import top.xym.entity.UserEntity;
import top.xym.repository.PriceRepository;
import top.xym.repository.UserRepository;
import top.xym.service.UserProfileService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public UserProfileResponse getUserProfile(Integer userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            // 可以抛出自定义异常或返回null/错误提示
            return null;
        }
        UserEntity userEntity = userOptional.get();
        UserProfileResponse response = new UserProfileResponse();
        BeanUtils.copyProperties(userEntity, response); // 复制基础属性
        response.setUserId(userEntity.getId().longValue()); // 手动转换并设置

        // 根据用户的 permission_id 查询套餐信息
        if (userEntity.getPermissionId() != null) {
            Optional<PriceEntity> priceOptional = priceRepository.findById(userEntity.getPermissionId());
            if (priceOptional.isPresent()) {
                PriceEntity currentPackage = priceOptional.get();
                PackageInfo packageInfo = new PackageInfo();
                packageInfo.setId(currentPackage.getId());
                packageInfo.setName(currentPackage.getPermissionName());
                packageInfo.setPrice(currentPackage.getPrice());
                // 解析 card 字段，假设格式为 "{id1,id2,id3}"
                packageInfo.setPermissions(parsePermissionIds(currentPackage.getCard()));
                response.setCurrentPackage(packageInfo);
            }
        }
        return response;
    }

    @Override
    public PermissionPackageListResponse getAvailablePackages() {
        List<PriceEntity> allPrices = priceRepository.findAll();
        List<PackageInfo> packageInfos = allPrices.stream().map(priceEntity -> {
            PackageInfo packageInfo = new PackageInfo();
            packageInfo.setId(priceEntity.getId());
            packageInfo.setName(priceEntity.getPermissionName());
            packageInfo.setPrice(priceEntity.getPrice());
            packageInfo.setPermissions(parsePermissionIds(priceEntity.getCard()));
            return packageInfo;
        }).collect(Collectors.toList());

        PermissionPackageListResponse response = new PermissionPackageListResponse();
        response.setPackages(packageInfos);
        return response;
    }

    // Helper method to parse permission IDs from card string like "{1,2,3}"
    private List<Integer> parsePermissionIds(String cardString) {
        if (cardString == null || cardString.isEmpty() || !cardString.startsWith("{") || !cardString.endsWith("}")) {
            return Collections.emptyList();
        }
        String idsPart = cardString.substring(1, cardString.length() - 1);
        if (idsPart.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(idsPart.split(","))
                     .map(String::trim)
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }
}
