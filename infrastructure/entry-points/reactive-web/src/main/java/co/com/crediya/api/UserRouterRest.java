package co.com.crediya.api;

import co.com.crediya.api.constants.ApiConstants;
import co.com.crediya.api.dtos.CreateUserDTO;
import co.com.crediya.api.dtos.UserDTO;
import co.com.crediya.model.user.User;
import co.com.crediya.utils.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Tag(name = "Users", description = "User management APIs")
public class UserRouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = ApiConstants.BASE_PATH + "/users",
                    produces = { "application/json" },
                    method = { RequestMethod.POST },
                    beanClass = UserHandler.class,
                    beanMethod = "getAllUsers",
                    operation = @Operation(
                            operationId = "createUser",
                            summary = "Create a new user",
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(schema = @Schema(implementation = CreateUserDTO.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "successful operation",
                                            content = @Content(schema = @Schema(implementation = UserDTO.class))),
                                    @ApiResponse(responseCode = "400", description = "Bad Request",
                                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
                                    @ApiResponse(responseCode = "409", description = "Conflict - User already exists",
                                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
                            }
                    )
            ),
            @RouterOperation(
                    path = ApiConstants.BASE_PATH + "/users",
                    produces = { "application/json" },
                    method = { RequestMethod.GET },
                    beanClass = UserHandler.class,
                    beanMethod = "getAllUsers",
                    operation = @Operation(
                            operationId = "getAllUsers",
                            summary = "List all users",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "successful operation",
                                            content = @Content(schema = @Schema(implementation = UserDTO.class)))
                            }
                    )
            ),
            @RouterOperation(
                    path = ApiConstants.BASE_PATH + "/users/{id}",
                    produces = { "application/json" },
                    method = { RequestMethod.GET },
                    beanClass = UserHandler.class,
                    beanMethod = "getUserById",
                    operation = @Operation(
                            operationId = "getUserById",
                            summary = "Find user by ID",
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id", description = "User ID")
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "successful operation",
                                            content = @Content(schema = @Schema(implementation = User.class))),
                                    @ApiResponse(responseCode = "404", description = "User not found")
                            }
                    )
            ),
            @RouterOperation(
                    path = ApiConstants.BASE_PATH + "/users/{id}",
                    produces = { "application/json" },
                    method = { RequestMethod.PATCH },
                    beanClass = UserHandler.class,
                    beanMethod = "updateUser",
                    operation = @Operation(
                            operationId = "updateUser",
                            summary = "Update an existing user",
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id", description = "User ID")
                            },
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(schema = @Schema(implementation = CreateUserDTO.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "successful operation",
                                            content = @Content(schema = @Schema(implementation = UserDTO.class))),
                                    @ApiResponse(responseCode = "400", description = "Bad Request",
                                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
                                    @ApiResponse(responseCode = "404", description = "User not found",
                                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
                                    @ApiResponse(responseCode = "409", description = "Conflict - User already exists",
                                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
                            }
                    )
            ),
            @RouterOperation(
                    path = ApiConstants.BASE_PATH + "/users/{id}",
                    produces = { "application/json" },
                    method = { RequestMethod.DELETE },
                    beanClass = UserHandler.class,
                    beanMethod = "deleteUser",
                    operation = @Operation(
                            operationId = "deleteUser",
                            summary = "Delete a user",
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id", description = "User ID")
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "User deleted successfully"),
                                    @ApiResponse(responseCode = "404", description = "User not found",
                                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
        return route(GET(ApiConstants.BASE_PATH + "/users"), userHandler::getAllUsers)
                .andRoute(POST(ApiConstants.BASE_PATH + "/users"), userHandler::createUser)
                .and(route(GET(ApiConstants.BASE_PATH + "/users/{id}"), userHandler::getUserById))
                .and(route(PATCH(ApiConstants.BASE_PATH + "/users/{id}"), userHandler::updateUser))
                .and(route(DELETE(ApiConstants.BASE_PATH + "/users/{id}"), userHandler::deleteUser));
    }
}
