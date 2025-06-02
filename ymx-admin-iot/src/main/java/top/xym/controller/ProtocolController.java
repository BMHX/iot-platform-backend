package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.xym.dto.ProtocolDTO;

import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result;
import top.xym.query.ProtocolQuery;
import top.xym.service.ProtocolService;
import top.xym.vo.ProtocolVO;
import java.util.List;

/**
 * 协议管理 Controller
 *
 * @author TraeAI
 */
@RestController
@RequestMapping("/iot/protocol")
@Tag(name = "协议管理")
@AllArgsConstructor
@Validated
public class ProtocolController {

    private final ProtocolService protocolService;

    @GetMapping("/page")
    @Operation(summary = "分页查询协议列表")
    public Result<PageResult<ProtocolVO>> getProtocolPage(@Valid ProtocolQuery query) {
        PageResult<ProtocolVO> pageResult = protocolService.getProtocolPage(query);
        return Result.ok(pageResult);
    }

    @GetMapping("/list")
    @Operation(summary = "查询所有协议列表")
    public Result<List<ProtocolVO>> getProtocolList(@Valid ProtocolQuery query) {
        List<ProtocolVO> list = protocolService.getProtocolList(query);
        return Result.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取协议信息")
    public Result<ProtocolVO> getProtocolById(@PathVariable Integer id) { // 如果实体类ID是Long，这里改为Long
        ProtocolVO protocolVO = protocolService.getProtocolById(id);
        return Result.ok(protocolVO);
    }
}