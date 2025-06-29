package com.example.useraccount.cohort5860.account.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegisterDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
