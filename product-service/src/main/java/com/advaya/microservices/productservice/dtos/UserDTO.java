package com.advaya.microservices.productservice.dtos;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO{

    private String name;
    private String email;
    private List<RoleDTO> roles;




}
