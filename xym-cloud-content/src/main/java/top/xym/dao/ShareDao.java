package top.xym.dao;

import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Share;
import top.xym.framework.mybatis.dao.BaseDao;

@Mapper
public interface ShareDao extends BaseDao<Share> {
}