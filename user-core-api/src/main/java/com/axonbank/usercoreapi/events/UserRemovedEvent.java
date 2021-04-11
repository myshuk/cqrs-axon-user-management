package com.axonbank.usercoreapi.events;

import lombok.Data;

@Data
public class UserRemovedEvent {
    private String id;
}
