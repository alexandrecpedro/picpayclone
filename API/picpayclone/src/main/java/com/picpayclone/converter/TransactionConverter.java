package com.picpayclone.converter;

import com.picpayclone.dto.TransactionDTO;
import com.picpayclone.model.Transaction;
import com.picpayclone.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component // class that is not a service, a source or a repository (persistence
public class TransactionConverter extends BaseConverter<Transaction, TransactionDTO> {

    @Autowired
    private IUserService userService;

    @Override
    public TransactionDTO convertEntityToDTO(Transaction entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Transaction, TransactionDTO>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(entity, TransactionDTO.class);
    }

    @SuppressWarnings("unchecked")
    public Page<TransactionDTO> convertPageEntityToDTO(Page<Transaction> entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Page<Transaction>, Page<TransactionDTO>>() {
            @Override
            protected void configure() {
            }
        });

        return modelMapper.map(entity, Page.class);
    }

    @Override
    public Transaction convertDTOToEntity(TransactionDTO dto) {
        // Needs a fine-tuning = validate origin and destination users (if they exists)
        // builder = instantiates an object by passing attributes with comma
        // [e.g. Transaction.builder.code(dto.getCode()) ] instead of setting the attribute
        return Transaction.builder()
                .code(dto.getCode())
                .dateTime(dto.getDateTime())
                .value(dto.getValue())
                .destination(userService.entityConsult(dto.getDestination().getLogin()))
                .origin(userService.entityConsult(dto.getOrigin().getLogin()))
                .build();
    }
}
