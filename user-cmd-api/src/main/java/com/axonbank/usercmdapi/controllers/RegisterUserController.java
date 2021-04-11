package com.axonbank.usercmdapi.controllers;

import com.axonbank.usercmdapi.commands.RegisterUserCommand;
import com.axonbank.usercmdapi.dto.RegisterUserResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/registerUser")
public class RegisterUserController {
    private final CommandGateway commandGateway;

    @Autowired
    RegisterUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(
            @Valid @RequestBody RegisterUserCommand registerUserCommand) {
        registerUserCommand.setId(UUID.randomUUID().toString());
        try {
            commandGateway.send(registerUserCommand);
            return new ResponseEntity<>(new RegisterUserResponse("Success is User Creation"),
                    HttpStatus.OK);
        }catch (Exception e ) {
            var safeErrorMsg = "Error While processing register User with id: "
                    + registerUserCommand.getId();
            System.out.println(e.toString());
            return new ResponseEntity<>(new RegisterUserResponse(safeErrorMsg),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
