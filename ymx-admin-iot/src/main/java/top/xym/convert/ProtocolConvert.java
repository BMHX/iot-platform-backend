package top.xym.convert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import top.xym.entity.Protocol;
import top.xym.dto.ProtocolDTO;
import top.xym.vo.ProtocolVO;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

/**
 * 协议表转换器
 *
 * @author TraeAI
 */
@Mapper
public interface ProtocolConvert {
    ProtocolConvert INSTANCE = Mappers.getMapper(ProtocolConvert.class);

    /**
     * DTO转实体
     */
    @Mapping(source = "name", target = "protocolName")
    @Mapping(source = "type", target = "protocolCode")
    Protocol convert(ProtocolDTO dto);

    /**
     * 实体转VO
     */
    @Mapping(source = "protocolName", target = "name")
    @Mapping(source = "protocolCode", target = "type")
    @Mapping(source = "config", target = "config", qualifiedByName = "decompressConfigIfNeeded")
    ProtocolVO convert(Protocol entity);

    /**
     * 实体列表转VO列表
     */
    List<ProtocolVO> convertList(List<Protocol> list);
    
    /**
     * 如果配置被压缩，则解压缩
     */
    @Named("decompressConfigIfNeeded")
    default String decompressConfigIfNeeded(String config) {
        if (config == null || config.isEmpty()) {
            return config;
        }
        
        // 检查是否是压缩的配置
        if (config.startsWith("COMPRESSED:")) {
            try {
                // 提取压缩后的Base64编码字符串
                String compressedBase64 = config.substring("COMPRESSED:".length());
                
                // 解码Base64
                byte[] compressedBytes = Base64.getDecoder().decode(compressedBase64);
                
                // 解压GZIP
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressedBytes))) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = gzipInputStream.read(buffer)) > 0) {
                        byteArrayOutputStream.write(buffer, 0, len);
                    }
                }
                
                // 返回解压后的JSON字符串
                return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException("配置解压失败", e);
            }
        }
        
        // 如果不是压缩的，直接返回
        return config;
    }
}
