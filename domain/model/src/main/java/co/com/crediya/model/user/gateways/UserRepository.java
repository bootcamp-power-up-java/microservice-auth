package co.com.crediya.model.user.gateways;

import co.com.crediya.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface UserRepository {

    Mono<User> save(User user);

    Mono<User> findById(BigInteger id);

    Mono<User> findByEmail(String email);

    Flux<User> findAll();

    Mono<Void> deleteById(BigInteger id);

}
