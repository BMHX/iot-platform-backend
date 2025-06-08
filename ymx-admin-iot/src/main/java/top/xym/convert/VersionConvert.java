package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.entity.Version;
import top.xym.vo.VersionVO;
import top.xym.dto.VersionDTO;

import java.util.List;

/**
 * 版本表 转换器
 *
 * @author TraeAI
 * @since 2024-07-30
 */
@Mapper
public interface VersionConvert {

    VersionConvert INSTANCE = Mappers.getMapper(VersionConvert.class);

    Version convert(VersionDTO dto);

    VersionVO convert(Version entity);

    List<VersionVO> convertList(List<Version> list);
} 