package co.com.crediya.r2dbc;

import co.com.crediya.r2dbc.constants.UserQueryConstants;
import co.com.crediya.r2dbc.entities.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserReactiveRepository extends ReactiveCrudRepository<UserEntity, UUID>, ReactiveQueryByExampleExecutor<UserEntity> {

    @Query(UserQueryConstants.FIND_BY_EMAIL)
    Mono<UserEntity> findByEmail(String email);

}
