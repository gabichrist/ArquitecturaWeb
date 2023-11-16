package com.usuario.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import enums.Roles;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTokenDTO {

    private boolean isAuthenticated;
    private String username;
    private Roles rol;

}
