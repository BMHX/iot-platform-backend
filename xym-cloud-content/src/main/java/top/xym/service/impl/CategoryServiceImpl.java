package top.xym.service.impl;

import org.springframework.stereotype.Service;
import top.xym.dao.CategoryDao;
import top.xym.entity.Category;
import top.xym.framework.mybatis.service.impl.BaseServiceImpl;
import top.xym.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryDao, Category> implements CategoryService {
}