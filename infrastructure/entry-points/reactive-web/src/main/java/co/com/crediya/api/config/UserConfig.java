package co.com.crediya.api.config;

import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final UserRepository userRepository;

//    @Bean
    public UserUseCase userUseCase() {
        return new UserUseCase(userRepository);
    }

}
