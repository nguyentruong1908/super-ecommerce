package com.example.webshopgateway.config;

import com.example.webshopgateway.config.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("spring-cloud-auth", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://spring-cloud-auth"))

                .route("spring-cloud-product", r -> r.path("/product/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://spring-cloud-product"))

                .route("spring-cloud-order", r -> r.path("/order/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://spring-cloud-order"))

                .route("auth", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth"))
                .build();
    }

}
