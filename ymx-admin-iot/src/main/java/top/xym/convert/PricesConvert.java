package top.xym.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import top.xym.entity.Prices;
import top.xym.vo.PricesVO;
import top.xym.dto.PricesDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 价格套餐表 转换器
 *
 * @author YourName
 * @since YourDate
 */
@Mapper
public interface PricesConvert {

    PricesConvert INSTANCE = Mappers.getMapper(PricesConvert.class);

    @Mapping(target = "price", ignore = true) // price 在 service 中计算
    Prices convert(PricesDTO dto);

    PricesVO convert(Prices entity);

    List<PricesVO> convertList(List<Prices> list);

}