package com.axonbank.usercmdapi.commands;

import com.axonbank.usercoreapi.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RegisterUserCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "No User Details were supplied")
    @Valid
    private User user;
}
