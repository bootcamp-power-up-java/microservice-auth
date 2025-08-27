package co.com.crediya.r2dbc.entities;

import co.com.crediya.r2dbc.constants.UserEntityConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(UserEntityConstants.TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column
    private UUID id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String documentIdentity;

    @Column
    private LocalDate birthdate;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private double baseSalary;

    @Column
    private String roleId;

    @CreatedDate()
    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

}
