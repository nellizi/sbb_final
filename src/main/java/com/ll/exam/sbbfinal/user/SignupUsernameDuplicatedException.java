package com.ll.exam.sbbfinal.user;

public class SignupUsernameDuplicatedException extends RuntimeException {
    public SignupUsernameDuplicatedException(String message) {
        super(message);
    }
}
