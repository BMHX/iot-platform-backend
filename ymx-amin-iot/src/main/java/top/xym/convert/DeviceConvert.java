package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.entity.DeviceEntity;
import top.xym.dto.DeviceDTO;
import top.xym.vo.DeviceVO;

import java.util.List;

@Mapper
public interface DeviceConvert {
    DeviceConvert INSTANCE = Mappers.getMapper(DeviceConvert.class);

    DeviceEntity convert(DeviceDTO dto);

    DeviceVO convert(DeviceEntity entity);

    List<DeviceVO> convertList(List<DeviceEntity> list);

    DeviceEntity convertVO(DeviceVO vo);
}