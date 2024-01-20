package com.management.taskifypro.model.response;

import java.util.List;
import java.util.Set;

import com.management.taskifypro.model.entity.Role;
import com.management.taskifypro.model.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private Long id;
    private String type = "Bearer";
    private String token;
    private String username;
    private String email;
    private Set<Role> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, Set<Role> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String username, Long id, String email, Set<Role> roles, String accessToken) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.token = accessToken;
        this.username = username;
    }

    public JwtResponse(String tokenType, String accessToken, String email) {
        this.type = tokenType;
        this.token = accessToken;
        this.email = email;
    }

}
