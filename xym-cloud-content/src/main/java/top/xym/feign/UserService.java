package top.xym.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xym.framework.common.utils.Result;
import top.xym.vo.UserVO;

@FeignClient(name = "xym-cloud-user")
public interface UserService {
    @GetMapping(value = "/api/user/getUserById")
    Result<UserVO> getUserById(@RequestParam ("id") Long id);
}
