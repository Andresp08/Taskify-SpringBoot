package com.management.taskifypro.model.request;

import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.management.taskifypro.model.enums.ERole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(min = 6, max = 40)
    private String confirmPassword;

    private Collection<ERole> roles;

    @Override
    public String toString() {
        return "SignupRequest [username=" + username + ", email=" + email + ", password=" + password
                + ", confirmPassword=" + confirmPassword + ", roles=" + roles + "]";
    }
}
