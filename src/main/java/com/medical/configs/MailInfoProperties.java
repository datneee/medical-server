package com.medical.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("mail")
public class MailInfoProperties {
    private String username;
    private String password;
}
