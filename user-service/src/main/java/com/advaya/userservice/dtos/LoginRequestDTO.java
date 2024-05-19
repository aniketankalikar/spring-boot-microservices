package com.advaya.userservice.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestDTO extends GenericRequestDTO{

    private String email;

    private String password;
}
