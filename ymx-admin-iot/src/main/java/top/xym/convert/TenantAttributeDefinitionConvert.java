package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.dto.TenantAttributeDefinitionDTO;
import top.xym.entity.TenantAttributeDefinition;
import top.xym.vo.TenantAttributeDefinitionVO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TenantAttributeDefinitionConvert {
    TenantAttributeDefinitionConvert INSTANCE = Mappers.getMapper(TenantAttributeDefinitionConvert.class);

    TenantAttributeDefinition convertToEntity(TenantAttributeDefinitionDTO dto);

    TenantAttributeDefinitionVO convertToVO(TenantAttributeDefinition entity);

    List<TenantAttributeDefinitionVO> convertToVOList(List<TenantAttributeDefinition> list);
}
