package com.axonbank.usercoreapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Size(min = 2, message = "username should me at least 2 chars long")
    private String username;
    @Size(min = 8, message= "Password should be at least 8 chars long")
    private String password;
    private List<Role> role;
}
