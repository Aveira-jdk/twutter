package com.example.api_gateway.filter;

import com.example.api_gateway.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class PreGatewayFilterFactory extends AbstractGatewayFilterFactory<PreGatewayFilterFactory.Config> {

    Logger logger = LoggerFactory.getLogger(PreGatewayFilterFactory.class);

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtService jwtService;

    public PreGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            HttpHeaders headers = exchange.getRequest().getHeaders();

            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                if (path.contains("extract-id")){
                    throw new RuntimeException("This endpoint not for Users or Admins");
                }

                String authHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                String role = jwtService.extractAllClaims(authHeader).get("role").toString();
                if (path.contains("role") || path.contains("all-accounts") || path.contains("delete-user")
                        || path.contains("get-by-username") || path.contains("by-id")){
                    if (!role.contains("ROLE_ADMIN"))
                        throw new RuntimeException("This endpoint is for Admins");
                }

                try {
                    jwtService.isTokenValid(authHeader);

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
