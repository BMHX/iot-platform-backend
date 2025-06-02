package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.dto.DeviceDTO;
import top.xym.entity.Device;
import top.xym.vo.DeviceVO;

import java.util.List;

@Mapper
public interface DeviceConvert {
    DeviceConvert INSTANCE = Mappers.getMapper(DeviceConvert.class);
    Device convert(DeviceDTO deviceDto);
    DeviceVO convert(Device device);
    List<DeviceVO> convertList(List<Device> list);
}