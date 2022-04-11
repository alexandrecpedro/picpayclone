package com.picpayclone.service.impl;

import com.picpayclone.constants.ValidationMessage;
import com.picpayclone.converter.UserConverter;
import com.picpayclone.dto.UserDTO;
import com.picpayclone.exception.BusinessException;
import com.picpayclone.model.Transaction;
import com.picpayclone.model.User;
import com.picpayclone.repository.UserRepository;
import com.picpayclone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    // Attributes
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    // Methods
    @Override
    @Transactional
    public User entityConsult(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Async("asyncExecutor")
    @Transactional
    public void updateBalance(Transaction storedTransaction, Boolean isCreditCard) {
        decrementBalance(storedTransaction, isCreditCard);
        increaseBalance(storedTransaction);
    }

    // Involves communication with the acquirer corporation API
    private void increaseBalance(Transaction storedTransaction) {
        userRepository.updateIncreaseBalance(storedTransaction.getDestination().getLogin(),
                storedTransaction.getValue());
    }

    private void decrementBalance(Transaction storedTransaction, Boolean isCreditCard) {
        if (!isCreditCard) {
            userRepository.updateDecrementBalance(storedTransaction.getOrigin().getLogin(),
                    storedTransaction.getValue());
        }
    }

    @Override
    // (User origin, User destination) => (User... users)
    public void validate(User... users) {
        Arrays.asList(users).stream().forEach(user -> {
            if (user == null) {
                throw new BusinessException(ValidationMessage.ERROR_NONEXISTENT_USER);
            }
        });
    }

    @Override
    public UserDTO consult(String login) {
        User user = entityConsult(login);
        return userConverter.convertEntityToDTO(user);
    }

    @Override
    public List<UserDTO> list(String login) {
        List<User> users = userRepository.findAll();
        List<User> filteredUsers = users.stream().filter(user -> !user.getLogin().equals(login))
                .collect(Collectors.toList());
        return userConverter.convertEntitiesToDTOs(filteredUsers);
    }
}
