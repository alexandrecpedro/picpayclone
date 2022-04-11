package com.picpayclone.resource;

import com.picpayclone.dto.TransactionDTO;
import com.picpayclone.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionResource extends ResourceBase<TransactionDTO> {

    @Autowired
    private ITransactionService transactionService;

    // POST REQUEST (Store data)
    // RequestBody = the info comes as JSON in request body
    // Valid = the notations should be validated
    @PostMapping
    @CacheEvict(cacheNames = "Transactions", allEntries = true)
    public ResponseEntity<TransactionDTO> save(@RequestBody @Valid TransactionDTO transactionDTO,
                                               UriComponentsBuilder uriBuilder) {
        TransactionDTO returnedTransactionDTO = transactionService.process(transactionDTO);
        String path = "/transactions/{code}";
        return responseCreatedItemWithURI(returnedTransactionDTO, uriBuilder, path,
                returnedTransactionDTO.getCode());
    }

    // GET REQUEST
    @GetMapping
    @Cacheable(cacheNames = "Transactions", key = "#root.method.name")
    public ResponseEntity<Page<TransactionDTO>> list(
            @PageableDefault(page = 0, size = 20) Pageable pagination, @RequestParam String login) {
        Page<TransactionDTO> transactions = transactionService.list(pagination, login);
        return responsePaginatedItemsList(transactions);
    }
}
