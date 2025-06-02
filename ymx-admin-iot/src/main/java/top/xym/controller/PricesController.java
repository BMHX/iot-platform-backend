package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.PricesQuery;
import top.xym.service.PricesService;
import top.xym.vo.PricesVO;
import top.xym.dto.PricesDTO;

import java.util.List;

/**
 * 价格套餐表 Controller
 *
 * @author YourName
 * @since YourDate
 */
@RestController
@RequestMapping("/iot/prices")
@Tag(name = "价格套餐管理")
@AllArgsConstructor
public class PricesController {

    private final PricesService pricesService;

    @GetMapping("/page")
    @Operation(summary = "分页查询价格套餐")
    public Result<PageResult<PricesVO>> page(PricesQuery query) {
        PageResult<PricesVO> pageResult = pricesService.page(query);
        return Result.ok(pageResult);
    }

    @PostMapping
    @Operation(summary = "新增价格套餐")
    public Result<?> save(@RequestBody @Valid PricesDTO dto) {
        pricesService.save(dto);
        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改价格套餐")
    public Result<?> update(@RequestBody @Valid PricesDTO dto) {
        pricesService.update(dto);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除价格套餐")
    public Result<?> delete(@PathVariable("id") Integer id) {
        pricesService.delete(id);
        return Result.ok();
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询价格套餐")
    public Result<PricesVO> getById(@PathVariable("id") Integer id) {
        PricesVO vo = pricesService.getById(id);
        return Result.ok(vo);
    }

    @GetMapping("/list")
    @Operation(summary = "查询所有价格套餐")
    public Result<List<PricesVO>> listAll() {
        List<PricesVO> list = pricesService.listAll();
        return Result.ok(list);
    }
}
