package com.advaya.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User extends BaseModel{

    private String name;
    private String email;
    private String hashedPassword;
    private boolean isEmailVerified;

    @ManyToMany
    private List<Role> roles;

}
