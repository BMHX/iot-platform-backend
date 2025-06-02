package top.xym.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * 权限表 值对象
 *
 * @author YourName
 * @since YourDate
 */
@Data
public class PermissionVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;


}
