package co.com.crediya.usecase.user;

import co.com.crediya.model.user.User;
import co.com.crediya.model.user.gateways.UserRepository;
import co.com.crediya.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Mono<User> findUserById(UUID id) {
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
                        new IllegalArgumentException(UserErrorMessages.getEmailAlreadyRegisteredMessage())
                )).switchIfEmpty(Mono.defer(() -> userRepository.save(cleanedUser)))
                .cast(User.class);
    }

    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> updateUser(User user) {
        if (user.getId() == null) {
            return Mono.error(new IllegalArgumentException(UserErrorMessages.getIdRequiredForUpdateMessage()));
        }
        return userRepository.findById(user.getId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException(UserErrorMessages.getUserNotFoundMessage())))
                .flatMap(existingUser -> {
                    User cleanedUser = cleanUserData(user);
                    if (user.getName() != null) {
                        existingUser.setName(cleanedUser.getName());
                    }
                    if (user.getLastName() != null) {
                        existingUser.setLastName(cleanedUser.getLastName());
                    }
                    if (user.getDocumentIdentity() != null) {
                        existingUser.setDocumentIdentity(cleanedUser.getDocumentIdentity());
                    }
                    if (user.getBirthdate() != null) {
                        existingUser.setBirthdate(cleanedUser.getBirthdate());
                    }
                    if (user.getBaseSalary() != null) {
                        existingUser.setBaseSalary(cleanedUser.getBaseSalary());
                    }
                    validateUserData(existingUser);
                    return userRepository.update(existingUser);
                });
    }


    public Mono<Void> deleteUserById(UUID id) {
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
            throw new IllegalArgumentException(UserErrorMessages.getNamesLastNamesRequiredMessage());
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException(UserErrorMessages.getNamesLastNamesRequiredMessage());
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException(UserErrorMessages.getEmailRequiredMessage());
        }
        if (!user.getEmail().matches(UserConstraints.EMAIL_REGEX)) {
            throw new IllegalArgumentException(UserErrorMessages.getInvalidEmailFormatMessage());
        }
        if (user.getBaseSalary() == null) {
            throw new IllegalArgumentException(UserErrorMessages.getSalaryRequiredMessage());
        }
        if (user.getBaseSalary() < UserConstraints.MIN_SALARY || user.getBaseSalary() > UserConstraints.MAX_SALARY) {
            throw new IllegalArgumentException(UserErrorMessages.getSalaryOutOfRangeMessage());
        }
    }

}
