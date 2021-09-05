package com.example.oddscheck.service;

public class InvalidBetIDException extends RuntimeException {
    public InvalidBetIDException(String message) {
        super(message);
    }
}
