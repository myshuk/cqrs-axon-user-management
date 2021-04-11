package com.axonbank.usercmdapi.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
