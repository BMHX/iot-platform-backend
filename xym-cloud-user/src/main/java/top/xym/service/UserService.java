package top.xym.service;

import top.xym.dto.UserDTO;
import top.xym.entity.UserEntity;
import top.xym.framework.mybatis.service.BaseService;
import top.xym.vo.UserVO;

public interface UserService extends BaseService<UserEntity> {
    void save(UserDTO vo);
    void update(UserDTO dto);
    UserVO getByMobile(String mobile);
    UserVO getById(Long id);
}