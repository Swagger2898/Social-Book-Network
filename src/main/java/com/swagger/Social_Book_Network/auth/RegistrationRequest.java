package com.swagger.Social_Book_Network.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message ="FirstName is mandatory")
    @NotBlank(message="Firstname is mandatory")
    private String firstName;
    @NotEmpty(message ="LastName is mandatory")
    @NotBlank(message="Lastname is mandatory")
    private String lastName;
    @Email(message = "email is not formatted")
    @NotEmpty(message ="Email is mandatory")
    @NotBlank(message="Email is mandatory")
    private String email;
    @NotEmpty(message ="Password is mandatory")
    @NotBlank(message="Password is mandatory")
    @Size(min=8, message="password should be more than 8 characters")
    private String password;


}
