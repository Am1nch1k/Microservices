package com.sber.parking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories
@OpenAPIDefinition(
        info = @Info(
                title = "Parking Service API",
                version = "1.0",
                description = "API для управления парковочными сессиями"
        )
)
public class ParkingApplication {

    @Value("${swagger.swagger-url}")
    private String swaggerUrl;
    @Value("${spring.mvc.servlet.path}")
    private String contextPath;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ParkingApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        log.info("Parking Service started successfully");
        log.info("Swagger UI: http://localhost:8080" + contextPath + swaggerUrl);
    }
}
