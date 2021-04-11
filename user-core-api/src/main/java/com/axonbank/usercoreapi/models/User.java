package com.axonbank.usercoreapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotEmpty(message = "FirstName Cannot be empty")
    private String firstName;
    @NotEmpty(message = "LastName cannot be empty")
    private String lastName;
    @Email(message = "Enter a valid email")
    private String emailAddress;
    @NotNull
    private Account account;
}
