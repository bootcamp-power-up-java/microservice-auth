package co.com.crediya.api;

import co.com.crediya.api.constants.ApiConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
        return route(GET(ApiConstants.API_BASE_PATH + "/users"), userHandler::getAllUsers)
                .andRoute(POST(ApiConstants.API_BASE_PATH + "/users"), userHandler::createUser)
                .and(route(GET(ApiConstants.API_BASE_PATH + "/users/{id}"), userHandler::getUserById))
                .and(route(PATCH(ApiConstants.API_BASE_PATH + "/users/{id}"), userHandler::updateUser));
    }
}
