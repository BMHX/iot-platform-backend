package top.xym.convert;

import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;
import top.xym.dto.AdminDTO;
import top.xym.entity.Admin;
import top.xym.vo.AdminVO;


import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminConvert {
    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    Admin convert(AdminDTO adminDto);

    AdminVO convert(Admin admin);

    List<AdminVO> convertList(List<Admin> list);
}