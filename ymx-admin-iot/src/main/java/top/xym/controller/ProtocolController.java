package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.ProtocolDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.ProtocolQuery;
import top.xym.service.ProtocolService;
import top.xym.vo.ProtocolVO;

import java.util.List;

/**
 * 协议管理控制器
 *
 * @author TraeAI
 */
@RestController
@RequestMapping("/admin/protocol")
@Tag(name = "协议管理")
@AllArgsConstructor
public class ProtocolController {

    private final ProtocolService protocolService;

    @GetMapping("/page")
    @Operation(summary = "分页查询协议列表")
    public Result<PageResult<ProtocolVO>> page(ProtocolQuery query) {
        PageResult<ProtocolVO> page = protocolService.getProtocolPage(query);
        return Result.ok(page);
    }

    @GetMapping("/list")
    @Operation(summary = "查询所有协议列表")
    public Result<List<ProtocolVO>> list(ProtocolQuery query) {
        List<ProtocolVO> list = protocolService.getProtocolList(query);
        return Result.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取协议详情")
    public Result<ProtocolVO> get(@PathVariable("id") Integer id) {
        ProtocolVO protocol = protocolService.getProtocolById(id);
        if (protocol == null) {
            return Result.error("协议不存在");
        }
        return Result.ok(protocol);
    }

    @PostMapping
    @Operation(summary = "创建协议")
    public Result<Integer> create(@RequestBody ProtocolDTO protocolDTO) {
        Integer id = protocolService.createProtocol(protocolDTO);
        return Result.ok(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新协议")
    public Result<Void> update(@PathVariable("id") Integer id, @RequestBody ProtocolDTO protocolDTO) {
        protocolService.updateProtocol(id, protocolDTO);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除协议")
    public Result<Void> delete(@PathVariable("id") Integer id) {
        protocolService.deleteProtocol(id);
        return Result.ok();
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除协议")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        protocolService.batchDeleteProtocol(ids);
        return Result.ok();
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "更新协议状态")
    public Result<Void> updateStatus(@PathVariable("id") Integer id, @RequestBody ProtocolDTO protocolDTO) {
        protocolService.updateProtocolStatus(id, protocolDTO.getStatus());
        return Result.ok();
    }

    @PutMapping("/{id}/config")
    @Operation(summary = "保存协议配置")
    public Result<Void> saveConfig(@PathVariable("id") Integer id, @RequestBody ProtocolDTO protocolDTO) {
        protocolService.saveProtocolConfig(id, protocolDTO.getConfig());
        return Result.ok();
    }
}