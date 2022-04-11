package com.picpayclone.service;

import com.picpayclone.dto.UserDTO;
import com.picpayclone.model.Transaction;
import com.picpayclone.model.User;

import java.util.List;

public interface IUserService {

    User entityConsult(String login);

    UserDTO consult(String login);

    void updateBalance(Transaction storedTransaction, Boolean isCreditCard);

    void validate(User... users);

    List<UserDTO> list(String login);
}
