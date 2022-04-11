package com.picpayclone.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.picpayclone.enums.CardBrand;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(Include.NON_NULL) // send only non-nullable fields on JSON file
public class CreditCardDTO {

    @NotBlank
    private CardBrand brand;

    // This sensitive info should not be stored on app database
    @NotBlank
    private String securityCode;

    @NotBlank
    private String expirationDate;

    @NotBlank
    private String cardholderName;

    private String number;

    private String tokenNumber;

    // This sensitive info will not be stored on database by default. It could be requested by the app
    @NotNull
    private UserDTO user;

    private Boolean isSaved = false;
}
