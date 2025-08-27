package co.com.crediya.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private String id;
    private String name;
    private String lastName;
    private String documentIdentity;
    private LocalDate birthdate;
    private String email;
    private String password;
    private String phone;
    private String address;
    private float baseSalary;
    private String roleId;

}
