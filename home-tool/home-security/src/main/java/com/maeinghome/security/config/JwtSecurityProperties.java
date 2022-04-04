package com.maeinghome.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtSecurityProperties {
    /**
     * Request Headers ： Authorization
     */
    private String header = "Authorization";

    /**
     * 令牌前缀，最后留个空格 Bearer
     */
    private String tokenStartWith = "Bearer ";

    /**
     * Base64对该令牌进行编码
     */
    private String base64Secret = "maeing-home-not-today";

    /**
     * 令牌过期时间 此处单位/毫秒
     */
    private Long tokenValidityInSeconds = 14_400_000L;
}
