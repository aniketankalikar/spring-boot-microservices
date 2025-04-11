package com.advaya.userservice.security.models;

import com.advaya.userservice.models.Role;
import com.advaya.userservice.models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
@Data
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonLocked;

    private boolean enabled;

    private List<CustomGrantedAuthority> authorities = new ArrayList<>();


    public CustomUserDetails(User user)
    {
        this.username= user.getEmail();
        this.password= user.getHashedPassword();
        this.accountNonExpired = true;
        this.accountNonLocked=true;
        this.credentialsNonLocked=true;
        this.enabled=true;

        //In the granted Authority we need to add Roles

        for(Role role : user.getRoles())
        {
            authorities.add(new CustomGrantedAuthority(role));
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}