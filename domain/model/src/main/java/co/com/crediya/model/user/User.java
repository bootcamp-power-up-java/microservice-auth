package co.com.crediya.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private UUID id;
    private String name;
    private String lastName;
    private String documentIdentity;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Double baseSalary;
    private String roleId;

}
