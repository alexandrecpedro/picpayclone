package com.picpayclone.converter;

import com.picpayclone.dto.CreditCardDTO;
import com.picpayclone.dto.TransactionDTO;
import com.picpayclone.model.CreditCard;
import com.picpayclone.model.Transaction;
import com.picpayclone.service.IUserService;
import com.picpayclone.utils.UtilCreditCard;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditCardConverter extends BaseConverter<CreditCard, CreditCardDTO> {

    @Autowired
    private IUserService userService;

    @Override
    public CreditCardDTO convertEntityToDTO(CreditCard entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Transaction, TransactionDTO>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(entity, CreditCardDTO.class);
    }

    @Override
    public CreditCard convertDTOToEntity(CreditCardDTO dto) {
        return CreditCard
                .builder()
                .brand(dto.getBrand())
                // avoid storing full creditCard number on database
                // mask it and check encrypted number with creditCard brand
                .number(UtilCreditCard.mask(dto.getNumber()))
                .tokenNumber(dto.getTokenNumber())
                .user(userService.entityConsult(dto.getUser().getLogin()))
                .build();
    }
}
