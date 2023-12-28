package com.example.webshopgateway.config.filter;

import com.example.webshopgateway.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Value("${auth.endpoints.user}")
    String urlUser;

    @Value("${auth.endpoints.admin}")
    String urlAdmin;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

//        final List<String> apiEndpointsNoAuth = List.of("/auth");
//        final List<String> apiEndpointsAdmin = List.of("/order", "/product");
//
//        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpointsNoAuth.stream()
//                .noneMatch(uri -> r.getURI().getPath().contains(uri));
//        Predicate<ServerHttpRequest> isApiSecuredAdmin = r -> apiEndpointsAdmin.stream()
//                .anyMatch(uri -> r.getURI().getPath().contains(uri));

//        if (isApiSecured.test(request)) {
//            UserEntity user;
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", );
//            HttpEntity requestEntity = new HttpEntity(headers);

//            if (isApiSecuredAdmin.test(request)) {
//                try {
//                    user = restTemplate.exchange(urlAdmin, HttpMethod.GET, requestEntity, UserEntity.class).getBody();
//                } catch (Exception e) {
//                    ServerHttpResponse response = exchange.getResponse();
//                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                    return response.setComplete();
//                }
//            } else {
//                user =  restTemplate.exchange(urlUser, HttpMethod.GET, requestEntity, UserEntity.class).getBody();
//            }
//        }
        var headers = request.getHeaders().getOrEmpty("Authorization");
        if(!headers.isEmpty()) {
            exchange.getRequest().mutate()
                    .header("Authorization", headers.get(0))
                    .build();
        }

        return chain.filter(exchange);
    }

}
