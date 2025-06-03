package top.xym.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.xym.entity.TenantType;
import top.xym.vo.TenantDetailVO;

import java.util.List;

@Mapper
public interface TenantTypeMapper extends BaseMapper<TenantType> {
    @Select("SELECT " +
            "t.id AS id, " +
            "t.name AS name, " +
            "t.address AS address, " +
            "t.contact_person AS contact_person, " +
            "t.contact_phone AS contact_phone, " +
            "DATE_FORMAT(t.create_time, '%Y-%m-%d %H:%i:%s') AS create_time, " +
            "t.status AS status, " +
            "tt.code AS type_code, " +
            "JSON_ARRAYAGG( " +
            "    JSON_OBJECT( " +
            "        'code', tad.code, " +
            "        'name', tad.name, " +
            "        'value', tav.value " +
            "    ) " +
            ") AS attributes " +
            "FROM tenants t " +
            "JOIN tenant_types tt ON t.type_id = tt.id " +
            "LEFT JOIN tenant_attribute_definitions tad ON tad.type_id = tt.id " +
            "LEFT JOIN tenant_attribute_values tav ON tav.attr_id = tad.id AND tav.tenant_id = t.id " +
            "GROUP BY t.id, t.name, t.address, t.contact_person, t.contact_phone, t.create_time, t.status, tt.code")
    List<TenantDetailVO> listTenantDetails();
}