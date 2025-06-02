package top.xym.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.xym.framework.common.utils.PageResult;
import top.xym.framework.common.utils.Result; // 假设您有通用的Result类
import top.xym.query.DeviceQuery;
import top.xym.service.DeviceService;
import top.xym.vo.DeviceVO;

import java.util.List;

/**
 * 设备表 Controller
 *
 * @author YourName
 * @since YourDate
 */
@RestController
@RequestMapping("/iot/devices")
@Tag(name = "设备管理")
@AllArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/page")
    @Operation(summary = "分页查询设备信息")
    public Result<PageResult<DeviceVO>> page(DeviceQuery query) {
        PageResult<DeviceVO> pageResult = deviceService.page(query);
        return Result.ok(pageResult);
    }

    @GetMapping("/list")
    @Operation(summary = "查询所有设备信息")
    public Result<List<DeviceVO>> listAll() {
        List<DeviceVO> list = deviceService.listAll();
        return Result.ok(list);
    }

    // 如果需要增删改查，后续可以添加 @PostMapping, @PutMapping, @DeleteMapping, @GetMapping("/{id}") 等端点

}
