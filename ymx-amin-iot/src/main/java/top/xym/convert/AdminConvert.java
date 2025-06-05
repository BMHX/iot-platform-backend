package top.xym.convert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import top.xym.dto.AdminDTO;
import top.xym.dto.AdminUpdateDTO;
import top.xym.entity.AdminEntity;
import top.xym.vo.AdminInfoVO;
import top.xym.vo.AdminVO;

import java.util.List;

@Mapper
public interface AdminConvert {
    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "createdTime", source = "createdTime")
    @Mapping(target = "identity", source = "identity")
    @Mapping(target = "permissionId", source = "permissionId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "lastLoginTime", source = "lastLoginTime")
    AdminEntity convert(AdminDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "newPassword")
    @Mapping(target = "identity", source = "identity")
    @Mapping(target = "permissionId", source = "permissionId")
    AdminEntity convert(AdminUpdateDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "createdTime", source = "createdTime")
    @Mapping(target = "identity", source = "identity")
    @Mapping(target = "permissionId", source = "permissionId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "lastLoginTime", source = "lastLoginTime")
    AdminInfoVO convertToInfoVO(AdminEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "createdTime", source = "createdTime")
    @Mapping(target = "identity", source = "identity")
    @Mapping(target = "permissionId", source = "permissionId")
    AdminVO convertToVO(AdminEntity entity);

    List<AdminVO> convertToVOList(List<AdminEntity> list);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "createdTime", source = "createdTime")
    @Mapping(target = "identity", source = "identity")
    @Mapping(target = "permissionId", source = "permissionId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "lastLoginTime", source = "lastLoginTime")
    AdminDTO convertToDTO(AdminEntity entity);
}
