package com.example.useraccount.cohort5860.account.service;

import com.example.useraccount.cohort5860.account.dto.UserDto;
import com.example.useraccount.cohort5860.account.dto.UserRegisterDto;
import com.example.useraccount.cohort5860.account.dto.UserRolesDto;
import com.example.useraccount.cohort5860.account.dto.UserUpdateDto;

import java.security.Principal;

public interface UserAccountService {

    UserDto registerUser(UserRegisterDto userRegisterDto);


    UserDto getUser(String login);


    UserDto deleteUser(String login);


    UserDto updateUser(String login, UserUpdateDto userUpdateDto);


    UserRolesDto addRole(String login, String role);


    UserRolesDto removeRole(String login, String role);


    void changePassword(Principal principal, String newPassword);
}
