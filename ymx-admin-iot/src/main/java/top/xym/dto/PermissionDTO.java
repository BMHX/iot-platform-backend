package top.xym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;

/**
 * 权限表 数据传输对象
 *
 * @author YourName
 * @since YourDate
 */
@Data
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "权限名称不能为空")
    private String name;
}