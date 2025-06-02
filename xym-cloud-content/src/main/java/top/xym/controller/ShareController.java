package top.xym.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xym.convert.ShareConvert;
import top.xym.entity.Share;
import top.xym.framework.common.utils.Result;
import top.xym.service.ShareService;
import top.xym.vo.ShareVO;

import java.util.List;

@RestController
@RequestMapping("api/content")
@Tag(name = "内容模块")
@AllArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @GetMapping("shares")
    public Result<List<ShareVO>> getShareList() {
        List<Share> shares = shareService.list();
        List<ShareVO> list = ShareConvert.INSTANCE.convertList(shares);
        return Result.ok(list);
    }
}