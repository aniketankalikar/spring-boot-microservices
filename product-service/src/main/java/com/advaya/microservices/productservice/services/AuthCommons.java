package com.advaya.microservices.productservice.services;

import com.advaya.microservices.productservice.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {

    private RestTemplate restTemplate;
    public AuthCommons(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }


    public UserDTO validateToken(String token)
    {
        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:8086/users/validate/"+token, UserDTO.class);
        if(responseEntity == null)
        {
            return null;
        }

        return responseEntity.getBody();
    }
}
