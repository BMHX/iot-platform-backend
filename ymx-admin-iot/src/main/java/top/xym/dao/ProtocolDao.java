package top.xym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.xym.entity.Protocol;

/**
 * 协议表 Mapper 接口
 *
 * @author TraeAI
 */
@Mapper
public interface ProtocolDao extends BaseMapper<Protocol> {

}
