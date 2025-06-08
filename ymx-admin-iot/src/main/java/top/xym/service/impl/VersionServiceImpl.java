package top.xym.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.xym.convert.VersionConvert;
import top.xym.dao.VersionDao;
import top.xym.dto.VersionDTO;
import top.xym.entity.Version;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.VersionQuery;
import top.xym.service.VersionService;
import top.xym.vo.VersionVO;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 版本管理服务实现类
 *
 * @author TraeAI
 */
@Service
@AllArgsConstructor
public class VersionServiceImpl implements VersionService {

    private final VersionDao versionDao;

    @Override
    public PageResult<VersionVO> getVersionPage(VersionQuery query) {
        Page<Version> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<Version> wrapper = buildQueryWrapper(query);
        versionDao.selectPage(page, wrapper);
        return new PageResult<>(VersionConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    @Override
    public List<VersionVO> getVersionList(VersionQuery query) {
        LambdaQueryWrapper<Version> wrapper = buildQueryWrapper(query);
        List<Version> versions = versionDao.selectList(wrapper);
        return VersionConvert.INSTANCE.convertList(versions);
    }

    @Override
    public VersionVO getVersionById(Integer id) {
        Version version = versionDao.selectById(id);
        return version != null ? VersionConvert.INSTANCE.convert(version) : null;
    }

    private LambdaQueryWrapper<Version> buildQueryWrapper(VersionQuery query) {
        LambdaQueryWrapper<Version> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.hasText(query.getVersion())) {
            wrapper.like(Version::getVersion, query.getVersion());
        }
        if (StringUtils.hasText(query.getType())) {
            wrapper.eq(Version::getType, query.getType());
        }
        if (query.getIsActive() != null) {
            wrapper.eq(Version::getIsActive, query.getIsActive());
        }
        // 默认排序
        wrapper.orderByDesc(Version::getId);
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createVersion(VersionDTO versionDTO) {
        // 如果设置为激活版本，需要将其他版本设置为非激活
        if (Boolean.TRUE.equals(versionDTO.getIsActive())) {
            deactivateAllVersions();
        }
        
        Version version = VersionConvert.INSTANCE.convert(versionDTO);
        versionDao.insert(version);
        return version.getId().intValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateVersion(Integer id, VersionDTO versionDTO) {
        Version version = versionDao.selectById(id);
        if (version == null) {
            throw new RuntimeException("版本不存在");
        }
        
        // 如果设置为激活版本，需要将其他版本设置为非激活
        if (Boolean.TRUE.equals(versionDTO.getIsActive()) && !Boolean.TRUE.equals(version.getIsActive())) {
            deactivateAllVersions();
        }
        
        Version updateVersion = VersionConvert.INSTANCE.convert(versionDTO);
        updateVersion.setId(Long.valueOf(id));
        
        versionDao.updateById(updateVersion);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteVersion(Integer id) {
        Version version = versionDao.selectById(id);
        if (version == null) {
            throw new RuntimeException("版本不存在");
        }
        
        // 验证是否为当前激活版本，如果是则不能删除
        if (Boolean.TRUE.equals(version.getIsActive())) {
            throw new RuntimeException("当前激活版本不能删除");
        }
        
        versionDao.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setActiveVersion(Integer id) {
        Version version = versionDao.selectById(id);
        if (version == null) {
            throw new RuntimeException("版本不存在");
        }
        
        // 先将所有版本设置为非激活
        deactivateAllVersions();
        
        // 将指定版本设置为激活
        Version updateVersion = new Version();
        updateVersion.setId(Long.valueOf(id));
        updateVersion.setIsActive(true);
        
        versionDao.updateById(updateVersion);
    }

    /**
     * 将所有版本设置为非激活
     */
    private void deactivateAllVersions() {
        LambdaQueryWrapper<Version> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Version::getIsActive, true);
        
        List<Version> activeVersions = versionDao.selectList(wrapper);
        for (Version version : activeVersions) {
            version.setIsActive(false);
            versionDao.updateById(version);
        }
    }

    @Override
    public void generateVersionLog(HttpServletResponse response) throws IOException {
        // 获取所有版本信息，按发布时间降序排序
        LambdaQueryWrapper<Version> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByDesc(Version::getPublishTime);
        List<Version> versionList = versionDao.selectList(wrapper);
        List<VersionVO> versionVOList = VersionConvert.INSTANCE.convertList(versionList);
        
        // 生成Markdown格式的版本日志
        StringBuilder sb = new StringBuilder();
        sb.append("# 版本更新日志\n\n");
        
        for (VersionVO version : versionVOList) {
            sb.append("## ").append(version.getVersion()).append(" (")
              .append(version.getType().equals("stable") ? "正式版" : 
                     version.getType().equals("beta") ? "测试版" : "内测版")
              .append(")\n\n");
            
            sb.append("发布时间: ").append(version.getPublishTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
              .append("\n\n");
            
            sb.append("发布人: ").append(version.getPublisher()).append("\n\n");
            
            sb.append("描述: ").append(version.getDescription()).append("\n\n");
            
            sb.append(version.getUpdateContent()).append("\n\n");
            
            sb.append("---\n\n");
        }
        
        // 设置响应头
        response.setContentType("text/markdown");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=version-changelog.md");
        
        // 输出到响应流
        try (OutputStream os = response.getOutputStream()) {
            os.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();
        }
    }
} 