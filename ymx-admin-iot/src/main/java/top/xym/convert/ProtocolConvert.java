package top.xym.convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.entity.Protocol;
import top.xym.dto.ProtocolDTO;
import top.xym.vo.ProtocolVO;
import java.util.List;

/**
 * 协议表转换器
 *
 * @author TraeAI
 */
@Mapper
public interface ProtocolConvert {
    ProtocolConvert INSTANCE = Mappers.getMapper(ProtocolConvert.class);

    Protocol convert(ProtocolDTO dto);

    ProtocolVO convert(Protocol entity);

    List<ProtocolVO> convertList(List<Protocol> list);
}
