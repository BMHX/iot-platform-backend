package top.xym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.xym.dto.TenantDTO;
import top.xym.entity.Tenant;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.TenantQuery;
import top.xym.vo.TenantVO;

import java.util.List;

public interface ITenantService extends IService<Tenant> {

    /**
     * 分页查询租户列表
     *
     * @param query 查询参数
     * @return 分页结果
     */
    PageResult<TenantVO> page(TenantQuery query);

    /**
     * 获取租户详情
     *
     * @param id 租户ID
     * @return 租户视图对象
     */
    TenantVO get(Integer id);

    /**
     * 新增租户
     *
     * @param dto 数据传输对象
     */
    void save(TenantDTO dto);

    /**
     * 修改租户
     *
     * @param dto 数据传输对象
     */
    void update(TenantDTO dto);

    /**
     * 删除租户
     *
     * @param ids 租户ID列表
     */
    void delete(List<Integer> ids);
}