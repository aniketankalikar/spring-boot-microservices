package com.advaya.userservice.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Role extends BaseModel
{
    private String value;

}
