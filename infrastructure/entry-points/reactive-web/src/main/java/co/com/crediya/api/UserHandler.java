package co.com.crediya.api;

import co.com.crediya.api.dtos.CreateUserDTO;
import co.com.crediya.api.dtos.UpdateUserDTO;
import co.com.crediya.api.dtos.UserDTO;
import co.com.crediya.api.mappers.UserDTOMapper;
import co.com.crediya.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserUseCase userUseCase;
    private final UserDTOMapper userMapper;

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return userUseCase.findUserById(id)
                .map(userMapper::toDTO)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(dto))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateUserDTO.class)
                .map(userMapper::toModel)
                .flatMap(userUseCase::createUser)
                .map(userMapper::toDTO)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(dto))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userUseCase.findAllUsers().map(userMapper::toDTO), UserDTO.class);
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(UpdateUserDTO.class)
                .map(user -> userMapper.toModel(id, user))
                .flatMap(userUseCase::updateUser)
                .map(userMapper::toDTO)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(dto))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
