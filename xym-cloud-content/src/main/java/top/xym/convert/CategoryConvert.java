package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.entity.Category;
import top.xym.vo.CategoryVO;

import java.util.List;

@Mapper
public interface CategoryConvert {
    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);
    CategoryVO convert(Category entity);
    List<CategoryVO> convertList(List<Category> list);
}