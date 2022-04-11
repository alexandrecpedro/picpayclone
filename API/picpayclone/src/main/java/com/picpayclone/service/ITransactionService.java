package com.picpayclone.service;

import com.picpayclone.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITransactionService {

    TransactionDTO process(TransactionDTO transactionDTO);

    Page<TransactionDTO> list(Pageable pagination, String user);
}
