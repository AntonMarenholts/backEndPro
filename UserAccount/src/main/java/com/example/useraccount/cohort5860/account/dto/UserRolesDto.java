package com.example.useraccount.cohort5860.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;


@Getter
@Setter
@ToString
@Builder
public class UserRolesDto {
    private String login;
    private Set<String> roles;
}
