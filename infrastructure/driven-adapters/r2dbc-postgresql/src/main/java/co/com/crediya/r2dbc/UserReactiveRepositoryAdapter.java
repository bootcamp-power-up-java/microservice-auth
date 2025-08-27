package co.com.crediya.r2dbc;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.r2dbc.entities.UserEntity;
import co.com.crediya.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        User,
        UserEntity,
        String,
        UserReactiveRepository
> implements UserRepository {
    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public Flux<User> findAll() {
        return repository.findAll()
                .map(entity -> mapper.map(entity, User.class));
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(entity -> mapper.map(entity, User.class));
    }

    @Override
    public Mono<User> update(User user) {
        UserEntity u = mapper.map(user, UserEntity.class);
        u.setId(UUID.fromString(user.getId()));
        return repository.save(u)
                .map(entity -> mapper.map(entity, User.class));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id)
                .then();
    }
}
