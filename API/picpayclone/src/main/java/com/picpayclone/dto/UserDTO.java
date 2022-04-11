package com.picpayclone.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDTO {

    @NotBlank
    private String login;

    private String password;

    private String email;

    private String fullName;

    private String cpf;

    private LocalDate birthday;

    private String telephoneNumber;

    private Double balance;

}
