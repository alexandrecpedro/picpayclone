package com.picpayclone.service.impl;

import com.picpayclone.converter.TransactionConverter;
import com.picpayclone.dto.TransactionDTO;
import com.picpayclone.model.Transaction;
import com.picpayclone.repository.TransactionRepository;
import com.picpayclone.service.ICreditCardService;
import com.picpayclone.service.ITransactionService;
import com.picpayclone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {

    // Attributes
    @Autowired // default object (does not need an interface!)
    private TransactionConverter transactionConverter;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ICreditCardService creditCardService;

    @Autowired
    private IUserService userService;

    // Methods
    @Override
    public TransactionDTO process(TransactionDTO transactionDTO) {
        // Store transaction info with CreditCard details
        Transaction storedTransaction = save(transactionDTO);
        creditCardService.save(transactionDTO.getCreditCard());
        // VALIDATIONS - Update account balance
        // (A) It should happen on app background (async process)
        // (B) By credit card ? Update the user's PicPay account balance
        userService.updateBalance(storedTransaction, transactionDTO.getIsCreditCard());
        // CONVERT DATA TYPE
        // DTO -> model entity | model entity -> DTO
        return transactionConverter.convertEntityToDTO(storedTransaction);
    }

    private Transaction save(TransactionDTO transactionDTO) {
        Transaction transaction = transactionConverter.convertDTOToEntity(transactionDTO);
        // VALIDATIONS - User info
        userService.validate(transaction.getDestination(), transaction.getOrigin());
        // REPOSITORY - save/store data
        // Spring annotation to implement save method
        return transactionRepository.save(transaction); // returned object type = Transaction
    }

    @Override
    public Page<TransactionDTO> list(Pageable pagination, String loginUser) {
        Page<Transaction> transactions = transactionRepository.findByOrigin_LoginOrDestination_Login(
                loginUser, loginUser, pagination);
        return transactionConverter.convertPageEntityToDTO(transactions);
    }
}
