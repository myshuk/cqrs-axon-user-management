package com.axonbank.userqueryapi.handlers;

import com.axonbank.usercoreapi.events.UserRegisteredEvent;
import com.axonbank.usercoreapi.events.UserRemovedEvent;
import com.axonbank.usercoreapi.events.UserUpdateEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdateEvent event);
    void on(UserRemovedEvent event);
}
