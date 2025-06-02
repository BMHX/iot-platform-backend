package top.xym.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xym.convert.CategoryConvert;
import top.xym.entity.Category;
import top.xym.framework.common.utils.Result;
import top.xym.service.CategoryService;
import top.xym.vo.CategoryVO;

import java.util.List;

@RestController
@RequestMapping("api/category")
@Tag(name = "分类模块")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("list")
    public Result<List<CategoryVO>> getCategoryList() {
        List<Category> categories = categoryService.list();
        List<CategoryVO> list = CategoryConvert.INSTANCE.convertList(categories);
        return Result.ok(list);
    }
}