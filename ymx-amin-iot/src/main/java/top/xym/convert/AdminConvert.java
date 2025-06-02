package top.xym.convert;
import org.mapstruct.Mapper;
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

    AdminEntity convert(AdminDTO dto);

    AdminEntity convert(AdminUpdateDTO dto);

    AdminInfoVO convertToInfoVO(AdminEntity entity);

    AdminVO convertToVO(AdminEntity entity);

    List<AdminVO> convertToVOList(List<AdminEntity> list);
}
