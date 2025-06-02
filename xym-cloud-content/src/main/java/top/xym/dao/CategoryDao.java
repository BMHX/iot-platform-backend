package top.xym.dao;

import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Category;
import top.xym.framework.mybatis.dao.BaseDao;

@Mapper
public interface CategoryDao extends BaseDao<Category> {
}