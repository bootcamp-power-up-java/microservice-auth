package co.com.crediya.api.mappers;
import co.com.crediya.api.dtos.CreateUserDTO;
import co.com.crediya.api.dtos.UserDTO;
import co.com.crediya.model.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserDTO toDTO(User user);

    User toModel(UserDTO userDTO);

    User toModel(CreateUserDTO createUserDTO);

    List<UserDTO> toDTOList(List<User> users);

    List<User> toModelList(List<UserDTO> userDTOs);

}
