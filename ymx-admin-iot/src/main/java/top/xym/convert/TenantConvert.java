package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import top.xym.dto.TenantDTO;
import top.xym.entity.Tenant;
import top.xym.vo.TenantVO;

import java.util.List;

@Mapper
public interface TenantConvert {
    TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

    Tenant convert(TenantDTO dto);

    TenantVO convert(Tenant entity);

    List<TenantVO> convertList(List<Tenant> list);

    void update(TenantDTO dto, @MappingTarget Tenant entity);
}