package top.xym.service;

import top.xym.framework.common.utils.PageResult;
import top.xym.query.PricesQuery;
import top.xym.vo.PricesVO;
import top.xym.dto.PricesDTO;

import java.util.List;

/**
 * 价格套餐表 服务接口
 *
 * @author YourName
 * @since YourDate
 */
public interface PricesService {

    PageResult<PricesVO> page(PricesQuery query);

    void save(PricesDTO dto);

    void update(PricesDTO dto);

    void delete(Integer id);

    PricesVO getById(Integer id);

    List<PricesVO> listAll();
}
