package com.io.Ogani.service;

import org.aspectj.bridge.IMessage;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
