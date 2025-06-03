package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.dto.TenantAttributeValueDTO;
import top.xym.entity.TenantAttributeValue;
import top.xym.vo.TenantAttributeValueVO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TenantAttributeValueConvert {
    TenantAttributeValueConvert INSTANCE = Mappers.getMapper(TenantAttributeValueConvert.class);

    TenantAttributeValue convertToEntity(TenantAttributeValueDTO dto);

    TenantAttributeValueVO convertToVO(TenantAttributeValue entity);

    List<TenantAttributeValueVO> convertToVOList(List<TenantAttributeValue> list);
}