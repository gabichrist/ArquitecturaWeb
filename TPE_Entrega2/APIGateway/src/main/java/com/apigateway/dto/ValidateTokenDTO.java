package com.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.apigateway.enums.Roles;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTokenDTO {

    private boolean isAuthenticated;
    private String username;
    private Roles rol;

}
