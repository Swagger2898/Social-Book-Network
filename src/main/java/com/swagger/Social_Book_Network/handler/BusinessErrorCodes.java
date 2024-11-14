package com.swagger.Social_Book_Network.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;


public enum BusinessErrorCodes {

    NO_CODE(0,HttpStatus.NOT_IMPLEMENTED,"No code"),

    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "CURRENT PASSWORD IS INCORRECT"),


    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "NEW PASSWORD DOES NOT MATCH"),

    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "USER ACCOUNT IS LOCKED"),

    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "LOGIN AND / OR PASSWORD IS INCORRECT"),

    BAD_CREDENTIALS(304,HttpStatus.FORBIDDEN,"User account is Locked");

@Getter
private final int code;
@Getter
private final String description;
@Getter
private final HttpStatus httpStatus;

BusinessErrorCodes(int code , HttpStatus httpStatus,String description){
    this.code = code;
    this.httpStatus=httpStatus;
    this.description=description;
}


}
