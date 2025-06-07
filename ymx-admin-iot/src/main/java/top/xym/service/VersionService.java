package top.xym.service;

import jakarta.servlet.http.HttpServletResponse;
import top.xym.dto.VersionDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.VersionQuery;
import top.xym.vo.VersionVO;

import java.io.IOException;
import java.util.List;

/**
 * 版本管理服务接口
 *
 * @author TraeAI
 */
public interface VersionService {

    /**
     * 分页查询版本列表
     *
     * @param query 查询条件
     * @return 分页结果
     */
    PageResult<VersionVO> getVersionPage(VersionQuery query);

    /**
     * 查询所有版本列表
     *
     * @param query 查询条件
     * @return 版本列表
     */
    List<VersionVO> getVersionList(VersionQuery query);

    /**
     * 根据ID获取版本信息
     *
     * @param id 版本ID
     * @return 版本信息
     */
    VersionVO getVersionById(Integer id);

    /**
     * 创建版本
     *
     * @param versionDTO 版本信息
     * @return 版本ID
     */
    Integer createVersion(VersionDTO versionDTO);

    /**
     * 更新版本
     *
     * @param id 版本ID
     * @param versionDTO 版本信息
     */
    void updateVersion(Integer id, VersionDTO versionDTO);

    /**
     * 删除版本
     *
     * @param id 版本ID
     */
    void deleteVersion(Integer id);

    /**
     * 设置当前激活版本
     *
     * @param id 版本ID
     */
    void setActiveVersion(Integer id);

    /**
     * 生成版本日志
     *
     * @param response HTTP响应
     * @throws IOException IO异常
     */
    void generateVersionLog(HttpServletResponse response) throws IOException;
} 