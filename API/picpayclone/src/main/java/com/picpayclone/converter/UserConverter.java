package com.picpayclone.converter;

import com.picpayclone.dto.UserDTO;
import com.picpayclone.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConverter<User, UserDTO> {

    @Override
    public UserDTO convertEntityToDTO(User entity) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(entity, UserDTO.class);
    }

    @Override
    public User convertDTOToEntity(UserDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<UserDTO, User>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper.map(dto, User.class);
    }
}
