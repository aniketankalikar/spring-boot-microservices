package com.advaya.userservice.security.models;

import com.advaya.userservice.models.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public CustomGrantedAuthority(Role role)
    {
        this.authority = role.getValue();
    }
    @Override
    public String getAuthority() {
        return null;
    }
}
