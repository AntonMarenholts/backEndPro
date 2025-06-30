package com.example.useraccount.cohort5860.account.controller;


import com.example.useraccount.cohort5860.account.dto.UserDto;
import com.example.useraccount.cohort5860.account.dto.UserRegisterDto;
import com.example.useraccount.cohort5860.account.dto.UserRolesDto;
import com.example.useraccount.cohort5860.account.dto.UserUpdateDto;
import com.example.useraccount.cohort5860.account.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController  {
    private final UserAccountService userAccountService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        UserDto createdUser = userAccountService.registerUser(userRegisterDto);
        return ResponseEntity.ok(createdUser);
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> login(Principal principal) {
        UserDto user = userAccountService.getUser(principal.getName());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<UserDto> getUser(@PathVariable("user") String login) {
        UserDto user = userAccountService.getUser(login);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{user}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("user") String login) {
        UserDto deletedUser = userAccountService.deleteUser(login);
        return ResponseEntity.ok(deletedUser);
    }

    @PatchMapping("/user/{user}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("user") String login, @RequestBody UserUpdateDto userUpdateDto) {
        UserDto updatedUser = userAccountService.updateUser(login, userUpdateDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/user/{user}/role/{role}")
    public ResponseEntity<UserRolesDto> addRole(@PathVariable String user, @PathVariable String role) {
        UserRolesDto updatedRoles = userAccountService.addRole(user, role);
        return ResponseEntity.ok(updatedRoles);
    }

    @DeleteMapping("/user/{user}/role/{role}")
    public ResponseEntity<UserRolesDto> deleteRole(@PathVariable String user, @PathVariable String role) {
        UserRolesDto updatedRoles = userAccountService.removeRole(user, role);
        return ResponseEntity.ok(updatedRoles);
    }


    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestHeader("X-Password") String newPassword, Principal principal) {
        userAccountService.changePassword(principal, newPassword);
    }

}
