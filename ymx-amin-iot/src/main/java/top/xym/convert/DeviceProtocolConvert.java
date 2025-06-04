package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.dto.DeviceProtocolDTO;
import top.xym.entity.DeviceProtocolEntity;
import top.xym.vo.DeviceProtocolVO;

import java.util.List;

@Mapper
public interface DeviceProtocolConvert {
    DeviceProtocolConvert INSTANCE = Mappers.getMapper(DeviceProtocolConvert.class);

    DeviceProtocolEntity convert(DeviceProtocolDTO dto);

    DeviceProtocolVO convert(DeviceProtocolEntity entity);

    DeviceProtocolEntity convertVO(DeviceProtocolVO vo);

    List<DeviceProtocolVO> convertList(List<DeviceProtocolEntity> list);
}