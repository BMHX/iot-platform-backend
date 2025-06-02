package top.xym.convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.entity.Share;
import top.xym.vo.ShareVO;

import java.util.List;

@Mapper
public interface ShareConvert {
    ShareConvert INSTANCE = Mappers.getMapper(ShareConvert.class);

    ShareVO convert(Share entity);

    List<ShareVO> convertList(List<Share> list);
}