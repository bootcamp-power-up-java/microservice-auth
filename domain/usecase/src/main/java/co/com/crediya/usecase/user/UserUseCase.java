package co.com.crediya.usecase.user;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        User cleanedUser = cleanUserData(user);
        validateUserData(cleanedUser);
        return userRepository.findByEmail(cleanedUser.getEmail())
                .flatMap(existingUser -> Mono.error(
                        new IllegalArgumentException(UserErrorMessages.EMAIL_ALREADY_REGISTERED.getMessage())
                )).switchIfEmpty(Mono.defer(() -> userRepository.save(cleanedUser)))
                .cast(User.class);
    }

    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Mono<Void> deleteUserById(String id) {
        return userRepository.deleteById(id);
    }

    private User cleanUserData(User user) {
        user.setName(StringUtils.capitalizeEachWord(user.getName()));
        user.setLastName(StringUtils.capitalizeEachWord(user.getLastName()));
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().trim().toLowerCase());
        }
        return user;
    }

    private void validateUserData(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException(UserErrorMessages.NAMES_LAST_NAMES_REQUIRED.getMessage());
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException(UserErrorMessages.EMAIL_REQUIRED.getMessage());
        }
        if (!user.getEmail().matches(UserConstraints.EMAIL_REGEX)) {
            throw new IllegalArgumentException(UserErrorMessages.INVALID_EMAIL_FORMAT.getMessage());
        }
        if (user.getBaseSalary() < UserConstraints.MIN_SALARY || user.getBaseSalary() > UserConstraints.MAX_SALARY) {
            throw new IllegalArgumentException(UserErrorMessages.getSalaryOutOfRangeMessage());
        }
    }

}
