package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.entity.Permission;
import top.xym.vo.PermissionVO;
import top.xym.dto.PermissionDTO;

import java.util.List;

/**
 * 权限表 转换器
 *
 * @author YourName
 * @since YourDate
 */
@Mapper
public interface PermissionConvert {

    PermissionConvert INSTANCE = Mappers.getMapper(PermissionConvert.class);

    Permission convert(PermissionDTO dto);

    PermissionVO convert(Permission entity);

    List<PermissionVO> convertList(List<Permission> list);

}
