package com.advaya.userservice.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LogoutRequestDTO extends GenericRequestDTO{

    private String token;
}
