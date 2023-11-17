package com.example.api_gateway.config;

import com.example.api_gateway.filter.PreGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class RouteConfiguration {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder, PreGatewayFilterFactory preGatewayFilterFactory)
    {
        return routeLocatorBuilder.routes()
                .route( p -> p.path("/twutter/AUTH/**").filters(f -> f
                                .filter(preGatewayFilterFactory.apply(new PreGatewayFilterFactory.Config())))
                        .uri("lb://AUTH-SERVICE"))
                .route( p -> p.path("/twutter/USER/**").filters(f -> f
                                .filter(preGatewayFilterFactory.apply(new PreGatewayFilterFactory.Config())))
                        .uri("lb://USER-SERVICE"))
                .route( p -> p.path("/twutter/SOCIAL-GRAPH/**").filters(f -> f
                                .filter(preGatewayFilterFactory.apply(new PreGatewayFilterFactory.Config())))
                        .uri("lb://SOCIAL-GRAPH-SERVICE"))
                .route( p -> p.path("/twutter/TIME-LINE/**").filters(f -> f
                                .filter(preGatewayFilterFactory.apply(new PreGatewayFilterFactory.Config())))
                        .uri("lb://TIME-LINE-SERVICE"))
                .route( p -> p.path("/twutter/TWEET/**").filters(f -> f
                                .filter(preGatewayFilterFactory.apply(new PreGatewayFilterFactory.Config())))
                        .uri("lb://TWEET-SERVICE"))

                .build();
    }
}
