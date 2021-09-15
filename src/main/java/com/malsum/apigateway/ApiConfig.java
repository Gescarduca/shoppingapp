package com.malsum.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Function;

@Configuration
public class ApiConfig {


    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(p->p.path("/catalog/**")
                        .uri("lb://catalog-service"))//load balance and use name of app in eureka name server where it will get the location and do lb
                .route(p-> p.path("/sales/**")
                        .uri("lb://shopping-service"))
                .route(p->p.path("/inventory/**")
                        .uri("lb://inventory-service"))
                .build();
    }
}
