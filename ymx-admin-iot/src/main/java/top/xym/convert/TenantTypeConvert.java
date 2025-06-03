package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import top.xym.dto.TenantTypeDTO;
import top.xym.entity.TenantType;
import top.xym.vo.TenantTypeVO;

import java.util.List;

@Mapper
public interface TenantTypeConvert {
    TenantTypeConvert INSTANCE = Mappers.getMapper(TenantTypeConvert.class);

    TenantType convert(TenantTypeDTO dto);

    TenantTypeVO convert(TenantType entity);

    List<TenantTypeVO> convertList(List<TenantType> list);

    void update(TenantTypeDTO dto, @MappingTarget TenantType entity);
}