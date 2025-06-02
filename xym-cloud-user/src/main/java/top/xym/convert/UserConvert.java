package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.dto.MobileLoginDTO;
import top.xym.dto.UserDTO;
import top.xym.entity.UserEntity;
import top.xym.framework.security.user.UserDetail;
import top.xym.vo.UserVO;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);
    UserVO convert(UserEntity entity);
    UserEntity convert(UserDTO dto);
    UserVO convert(UserDetail userDetail);
    UserEntity convert(MobileLoginDTO dto);
    UserDetail convertDetail(UserEntity entity);
}