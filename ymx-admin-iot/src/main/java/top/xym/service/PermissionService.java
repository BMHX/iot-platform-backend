package top.xym.service;

import top.xym.framework.common.utils.PageResult;
import top.xym.query.PermissionQuery;
import top.xym.vo.PermissionVO;
import top.xym.dto.PermissionDTO;

import java.util.List;

/**
 * 权限表 服务接口
 *
 * @author YourName
 * @since YourDate
 */
public interface PermissionService {

    /**
     * 分页查询
     *
     * @param query 查询参数
     * @return 分页结果
     */
    PageResult<PermissionVO> page(PermissionQuery query);

    /**
     * 新增
     *
     * @param dto 数据传输对象
     */
    void save(PermissionDTO dto);

    /**
     * 修改
     *
     * @param dto 数据传输对象
     */
    void update(PermissionDTO dto);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(Long id);

    /**
     * 根据ID查询
     *
     * @param id 主键
     * @return 值对象
     */
    PermissionVO getById(Long id);

    /**
     * 查询全部
     *
     * @return 值对象列表
     */
    List<PermissionVO> listAll();
}
