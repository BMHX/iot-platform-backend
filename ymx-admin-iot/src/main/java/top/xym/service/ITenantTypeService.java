package top.xym.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.xym.entity.TenantType;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.TenantTypeQuery;
import top.xym.vo.TenantDetailVO;
import top.xym.vo.TenantTypeVO;
import top.xym.dto.TenantTypeDTO;

import java.util.List;

public interface ITenantTypeService extends IService<TenantType> {

    /**
     * 分页查询租户类型列表
     *
     * @param query 查询参数
     * @return 分页结果
     */
    PageResult<TenantTypeVO> page(TenantTypeQuery query);

    /**
     * 获取租户类型详情
     *
     * @param id 类型ID
     * @return 租户类型视图对象
     */
    TenantTypeVO get(Integer id);

    /**
     * 新增租户类型
     *
     * @param dto 数据传输对象
     */
    void save(TenantTypeDTO dto);

    /**
     * 修改租户类型
     *
     * @param dto 数据传输对象
     */
    void update(TenantTypeDTO dto);

    /**
     * 删除租户类型
     *
     * @param ids 类型ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 查询所有租户的详细信息列表（包含类型和扩展属性）
     *
     * @return 租户详细信息列表
     */
    List<TenantDetailVO> listTenantDetails();
}
