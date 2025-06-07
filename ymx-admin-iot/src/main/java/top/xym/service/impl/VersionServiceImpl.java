package top.xym.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.xym.dto.VersionDTO;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.VersionQuery;
import top.xym.service.VersionService;
import top.xym.vo.VersionVO;

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

    @Override
    public PageResult<VersionVO> getVersionPage(VersionQuery query) {
        // 模拟数据，实际项目中应该从数据库查询
        List<VersionVO> list = getMockVersionList();
        
        // 过滤
        if (query.getVersion() != null && !query.getVersion().isEmpty()) {
            list = list.stream()
                    .filter(v -> v.getVersion().contains(query.getVersion()))
                    .toList();
        }
        
        if (query.getType() != null && !query.getType().isEmpty()) {
            list = list.stream()
                    .filter(v -> v.getType().equals(query.getType()))
                    .toList();
        }
        
        if (query.getIsActive() != null) {
            list = list.stream()
                    .filter(v -> v.getIsActive().equals(query.getIsActive()))
                    .toList();
        }
        
        // 分页
        int total = list.size();
        int page = query.getPage() != null ? query.getPage() : 1;
        int limit = query.getLimit() != null ? query.getLimit() : 10;
        int start = (page - 1) * limit;
        int end = Math.min(start + limit, total);
        
        List<VersionVO> pageList = start < total ? list.subList(start, end) : new ArrayList<>();
        
        return new PageResult<>(pageList, total);
    }

    @Override
    public List<VersionVO> getVersionList(VersionQuery query) {
        // 模拟数据，实际项目中应该从数据库查询
        List<VersionVO> list = getMockVersionList();
        
        // 过滤
        if (query.getVersion() != null && !query.getVersion().isEmpty()) {
            list = list.stream()
                    .filter(v -> v.getVersion().contains(query.getVersion()))
                    .toList();
        }
        
        if (query.getType() != null && !query.getType().isEmpty()) {
            list = list.stream()
                    .filter(v -> v.getType().equals(query.getType()))
                    .toList();
        }
        
        if (query.getIsActive() != null) {
            list = list.stream()
                    .filter(v -> v.getIsActive().equals(query.getIsActive()))
                    .toList();
        }
        
        return list;
    }

    @Override
    public VersionVO getVersionById(Integer id) {
        // 模拟数据，实际项目中应该从数据库查询
        return getMockVersionList().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Integer createVersion(VersionDTO versionDTO) {
        // 模拟创建，实际项目中应该保存到数据库
        // 如果设置为激活版本，需要将其他版本设置为非激活
        if (Boolean.TRUE.equals(versionDTO.getIsActive())) {
            // 在实际项目中，这里应该更新数据库中的其他版本为非激活
        }
        
        // 模拟返回ID
        return 100;
    }

    @Override
    public void updateVersion(Integer id, VersionDTO versionDTO) {
        // 模拟更新，实际项目中应该更新数据库
        // 如果设置为激活版本，需要将其他版本设置为非激活
        if (Boolean.TRUE.equals(versionDTO.getIsActive())) {
            // 在实际项目中，这里应该更新数据库中的其他版本为非激活
        }
    }

    @Override
    public void deleteVersion(Integer id) {
        // 模拟删除，实际项目中应该从数据库删除
        // 需要验证是否为当前激活版本，如果是则不能删除
        VersionVO version = getVersionById(id);
        if (version != null && Boolean.TRUE.equals(version.getIsActive())) {
            throw new RuntimeException("当前激活版本不能删除");
        }
    }

    @Override
    public void setActiveVersion(Integer id) {
        // 模拟设置激活版本，实际项目中应该更新数据库
        // 先将所有版本设置为非激活，然后将指定版本设置为激活
    }

    @Override
    public void generateVersionLog(HttpServletResponse response) throws IOException {
        // 获取所有版本信息
        List<VersionVO> versionList = getMockVersionList();
        
        // 生成Markdown格式的版本日志
        StringBuilder sb = new StringBuilder();
        sb.append("# 版本更新日志\n\n");
        
        for (VersionVO version : versionList) {
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
    
    /**
     * 获取模拟版本列表数据
     */
    private List<VersionVO> getMockVersionList() {
        List<VersionVO> list = new ArrayList<>();
        
        VersionVO v1 = new VersionVO();
        v1.setId(1);
        v1.setVersion("1.0.0");
        v1.setType("stable");
        v1.setDescription("首个正式版本");
        v1.setUpdateContent("## 新特性\n- 基础功能实现\n- 设备管理\n- 数据分析\n- 告警功能");
        v1.setPublishTime(LocalDateTime.now().minusDays(30));
        v1.setCreateTime(LocalDateTime.now().minusDays(35));
        v1.setUpdateTime(LocalDateTime.now().minusDays(30));
        v1.setPublisher("管理员");
        v1.setIsActive(true);
        list.add(v1);
        
        VersionVO v2 = new VersionVO();
        v2.setId(2);
        v2.setVersion("0.9.0");
        v2.setType("beta");
        v2.setDescription("公测版本");
        v2.setUpdateContent("## 新特性\n- 基础功能实现\n- 设备管理\n- 数据分析");
        v2.setPublishTime(LocalDateTime.now().minusDays(60));
        v2.setCreateTime(LocalDateTime.now().minusDays(65));
        v2.setUpdateTime(LocalDateTime.now().minusDays(60));
        v2.setPublisher("系统管理员");
        v2.setIsActive(false);
        list.add(v2);
        
        VersionVO v3 = new VersionVO();
        v3.setId(3);
        v3.setVersion("0.8.5");
        v3.setType("beta");
        v3.setDescription("测试版更新");
        v3.setUpdateContent("## 修复\n- 修复了设备连接问题\n- 优化了数据展示");
        v3.setPublishTime(LocalDateTime.now().minusDays(90));
        v3.setCreateTime(LocalDateTime.now().minusDays(95));
        v3.setUpdateTime(LocalDateTime.now().minusDays(90));
        v3.setPublisher("系统管理员");
        v3.setIsActive(false);
        list.add(v3);
        
        VersionVO v4 = new VersionVO();
        v4.setId(4);
        v4.setVersion("0.8.0");
        v4.setType("alpha");
        v4.setDescription("内部测试版");
        v4.setUpdateContent("## 内测功能\n- 设备管理基础实现\n- 简单数据展示");
        v4.setPublishTime(LocalDateTime.now().minusDays(120));
        v4.setCreateTime(LocalDateTime.now().minusDays(125));
        v4.setUpdateTime(LocalDateTime.now().minusDays(120));
        v4.setPublisher("系统管理员");
        v4.setIsActive(false);
        list.add(v4);
        
        VersionVO v5 = new VersionVO();
        v5.setId(5);
        v5.setVersion("0.7.0");
        v5.setType("alpha");
        v5.setDescription("初始开发版本");
        v5.setUpdateContent("## 初始开发\n- 项目初始化\n- 基础框架搭建");
        v5.setPublishTime(LocalDateTime.now().minusDays(150));
        v5.setCreateTime(LocalDateTime.now().minusDays(155));
        v5.setUpdateTime(LocalDateTime.now().minusDays(150));
        v5.setPublisher("系统管理员");
        v5.setIsActive(false);
        list.add(v5);
        
        return list;
    }
} 