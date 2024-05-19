package com.advaya.userservice.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignUpRequestDTO extends GenericRequestDTO{

    private String email;
    private String name;
    private String password;
}
