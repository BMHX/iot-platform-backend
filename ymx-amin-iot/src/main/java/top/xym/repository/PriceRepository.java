package top.xym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.xym.entity.PriceEntity;

import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {
    // JpaRepository 默认提供了 findAll() 方法来获取所有套餐信息
    // List<PriceEntity> findAll();

    // 如果需要根据特定条件查找套餐，可以在这里添加方法
    // 例如：List<PriceEntity> findByPriceLessThan(Integer price);
}
