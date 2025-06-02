package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.xym.entity.Devices; // 注意实体类名为 Devices
import top.xym.vo.DeviceVO;
import top.xym.dto.DeviceDTO;

import java.util.List;

/**
 * 设备表 转换器
 *
 * @author YourName
 * @since YourDate
 */
@Mapper
public interface DeviceConvert {

    DeviceConvert INSTANCE = Mappers.getMapper(DeviceConvert.class);

    Devices convert(DeviceDTO dto);

    DeviceVO convert(Devices entity);

    List<DeviceVO> convertList(List<Devices> list);

}