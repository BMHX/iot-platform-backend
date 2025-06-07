package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.VersionDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.VersionQuery;
import top.xym.service.VersionService;
import top.xym.vo.VersionVO;

import java.io.IOException;
import java.util.List;

/**
 * 版本管理控制器
 *
 * @author TraeAI
 */
@RestController
@RequestMapping("/admin/version")
@Tag(name = "版本管理")
@AllArgsConstructor
public class VersionController {

    private final VersionService versionService;

    @GetMapping("/page")
    @Operation(summary = "分页查询版本列表")
    public Result<PageResult<VersionVO>> page(VersionQuery query) {
        PageResult<VersionVO> page = versionService.getVersionPage(query);
        return Result.ok(page);
    }

    @GetMapping("/list")
    @Operation(summary = "查询所有版本列表")
    public Result<List<VersionVO>> list(VersionQuery query) {
        List<VersionVO> list = versionService.getVersionList(query);
        return Result.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取版本详情")
    public Result<VersionVO> get(@PathVariable("id") Integer id) {
        VersionVO version = versionService.getVersionById(id);
        if (version == null) {
            return Result.error("版本不存在");
        }
        return Result.ok(version);
    }

    @PostMapping
    @Operation(summary = "创建版本")
    public Result<Integer> create(@RequestBody VersionDTO versionDTO) {
        Integer id = versionService.createVersion(versionDTO);
        return Result.ok(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新版本")
    public Result<Void> update(@PathVariable("id") Integer id, @RequestBody VersionDTO versionDTO) {
        versionService.updateVersion(id, versionDTO);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除版本")
    public Result<Void> delete(@PathVariable("id") Integer id) {
        versionService.deleteVersion(id);
        return Result.ok();
    }

    @PutMapping("/{id}/active")
    @Operation(summary = "设置当前激活版本")
    public Result<Void> setActive(@PathVariable("id") Integer id) {
        versionService.setActiveVersion(id);
        return Result.ok();
    }

    @GetMapping("/log/export")
    @Operation(summary = "导出版本日志")
    public void exportLog(HttpServletResponse response) throws IOException {
        versionService.generateVersionLog(response);
    }
} 