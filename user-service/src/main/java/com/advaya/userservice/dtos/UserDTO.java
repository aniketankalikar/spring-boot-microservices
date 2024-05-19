package com.advaya.userservice.dtos;

import com.advaya.userservice.models.Role;
import com.advaya.userservice.models.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends GenericRequestDTO{

    private String name;
    private String email;
    private List<Role> roles;


    public static UserDTO from(User user)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setRoles(user.getRoles());

        return userDTO;
    }
}
