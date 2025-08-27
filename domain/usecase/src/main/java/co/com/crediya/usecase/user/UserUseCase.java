package co.com.crediya.usecase.user;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Mono<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<User> createUser(User user) {
        // TODO: Add validation logic here
        return userRepository.save(user);
    }

    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Mono<Void> deleteUserById(String id) {
        return userRepository.deleteById(id);
    }

}
