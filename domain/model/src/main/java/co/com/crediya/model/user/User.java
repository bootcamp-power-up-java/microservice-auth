package co.com.crediya.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private BigInteger id;
    private String name;
    private String lastName;
    private String documentIdentity;
    private String email;
    private String password;
    private String phone;
    private float baseSalary;
    private BigInteger roleId;

}
