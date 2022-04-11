package com.picpayclone.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@JsonInclude(Include.NON_NULL)
public class TransactionDTO {

    @NotBlank
    private String code;

    @NotNull
    private UserDTO origin;

    @NotNull
    private UserDTO destination;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private Double value;

    private CreditCardDTO creditCard;

    private Boolean isCreditCard = false;
}
