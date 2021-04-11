package com.axonbank.usercoreapi.events;

import com.axonbank.usercoreapi.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisteredEvent {
    private String id;
    private User user;
}
