package com.axonbank.usercmdapi.aggregates;

import com.axonbank.usercmdapi.commands.RegisterUserCommand;
import com.axonbank.usercmdapi.commands.RemoveUserCommand;
import com.axonbank.usercmdapi.commands.UpdateUserCommand;
import com.axonbank.usercmdapi.security.PasswordEncoder;
import com.axonbank.usercmdapi.security.PasswordEncoderImpl;
import com.axonbank.usercoreapi.events.UserRegisteredEvent;
import com.axonbank.usercoreapi.events.UserRemovedEvent;
import com.axonbank.usercoreapi.events.UserUpdateEvent;
import com.axonbank.usercoreapi.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;
    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @Autowired
    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        var newUser = command.getUser();
        newUser.setId(command.getId());
        var password = newUser.getAccount().getPassword();
        passwordEncoder= new PasswordEncoderImpl();
        var hashPassword =passwordEncoder.hashPassword(password);
        newUser.getAccount().setPassword(hashPassword);

        var event = UserRegisteredEvent.builder()
                .id(command.getId())
                .user(newUser)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        var password = updatedUser.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);

        var event = UserUpdateEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var event = new UserRemovedEvent();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventHandler
    public void on(UserUpdateEvent event) {
        this.user = event.getUser();
    }

    @EventHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }



}
