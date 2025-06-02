package top.xym.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.UserEntity;
import top.xym.framework.mybatis.dao.BaseDao;

@Mapper
public interface UserDao extends BaseDao<UserEntity> {
    default UserEntity getById(Long id) {
        return this.selectOne(new QueryWrapper<UserEntity>().eq("id", id));
    }
    default UserEntity getByUsername(String username) {
        return this.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
    }
    default UserEntity getByMobile(String mobile) {
        return this.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
    }
}