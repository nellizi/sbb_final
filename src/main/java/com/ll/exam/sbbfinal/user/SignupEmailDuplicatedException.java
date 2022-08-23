package com.ll.exam.sbbfinal.user;


public class SignupEmailDuplicatedException extends RuntimeException {
    public SignupEmailDuplicatedException(String message) {
        super(message);
    }
}