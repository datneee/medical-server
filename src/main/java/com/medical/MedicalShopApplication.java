package com.medical;

import com.medical.configs.MailInfoProperties;
import com.medical.configs.StorageProperties;
import com.medical.services.IStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j  
@EnableConfigurationProperties({MailInfoProperties.class, StorageProperties.class, MailInfoProperties.class})
public class MedicalShopApplication {
    public String PORT = System.getenv("PORT");
    public static void main(String[] args) {
        SpringApplication.run(MedicalShopApplication.class, args);
    }

    @Bean
    CommandLineRunner init(IStorageService storageService) {
        return (args -> {
            storageService.init();
        });
    }
}
