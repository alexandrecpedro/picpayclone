package com.picpayclone.service.impl;

import com.picpayclone.converter.CreditCardConverter;
import com.picpayclone.dto.CreditCardDTO;
import com.picpayclone.model.CreditCard;
import com.picpayclone.repository.CreditCardRepository;
import com.picpayclone.service.ICreditCardService;
import com.picpayclone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService implements ICreditCardService {

    // Attributes
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardConverter creditCardConverter;

    @Autowired
    private IUserService userService;

    // Method
    @Override
    public CreditCardDTO save(CreditCardDTO creditCardDTO) {
        CreditCardDTO returnedCreditCard = null;
        if (creditCardDTO.getIsSaved()) {
            CreditCard creditCard = creditCardConverter.convertDTOToEntity(creditCardDTO);
            // VALIDATION - user data
            userService.validate(creditCard.getUser());
            CreditCard storedCreditCard = creditCardRepository.save(creditCard);
            returnedCreditCard = creditCardConverter.convertEntityToDTO(storedCreditCard);
        }
        return returnedCreditCard;
    }
}
