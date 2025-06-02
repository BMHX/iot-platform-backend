package top.xym.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.xym.convert.PricesConvert;
import top.xym.dao.PricesDao;
import top.xym.entity.Prices;
import top.xym.framework.common.utils.PageResult;
import top.xym.query.PricesQuery;
import top.xym.service.PricesService;
import top.xym.vo.PricesVO;
import top.xym.dto.PricesDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 价格套餐表 服务实现
 *
 * @author YourName
 * @since YourDate
 */
@Service
@AllArgsConstructor
public class PricesServiceImpl implements PricesService {

    private final PricesDao pricesDao;
    private static final int PRICE_PER_ITEM = 100;

    @Override
    public PageResult<PricesVO> page(PricesQuery query) {
        IPage<Prices> page = new Page<>(query.getPage(), query.getLimit());
        LambdaQueryWrapper<Prices> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(query.getPermissionName())) {
            wrapper.like(Prices::getPermissionName, query.getPermissionName());
        }
        wrapper.orderByDesc(Prices::getId);
        IPage<Prices> resultPage = pricesDao.selectPage(page, wrapper);
        return new PageResult<>(PricesConvert.INSTANCE.convertList(resultPage.getRecords()), resultPage.getTotal());
    }

    @Override
    public void save(PricesDTO dto) {
        Prices entity = PricesConvert.INSTANCE.convert(dto);
        entity.setPrice(calculatePrice(dto.getCard()));
        pricesDao.insert(entity);
    }

    @Override
    public void update(PricesDTO dto) {
        Prices entity = PricesConvert.INSTANCE.convert(dto);
        entity.setPrice(calculatePrice(dto.getCard()));
        pricesDao.updateById(entity);
    }

    @Override
    public void delete(Integer id) {
        pricesDao.deleteById(id);
    }

    @Override
    public PricesVO getById(Integer id) {
        Prices entity = pricesDao.selectById(id);
        return PricesConvert.INSTANCE.convert(entity);
    }

    @Override
    public List<PricesVO> listAll() {
        List<Prices> list = pricesDao.selectList(null);
        return PricesConvert.INSTANCE.convertList(list);
    }

    /**
     * 根据card内容计算价格
     * card格式为 "{1,2,3,4}"
     */
    private int calculatePrice(String card) {
        if (!StringUtils.hasText(card) || card.length() <= 2) {
            return 0;
        }
        // 移除 "{" 和 "}"
        String numbersPart = card.substring(1, card.length() - 1);
        if (!StringUtils.hasText(numbersPart)){
            return 0;
        }
        // 按逗号分割
        String[] items = numbersPart.split(",");
        return items.length * PRICE_PER_ITEM;
    }
}