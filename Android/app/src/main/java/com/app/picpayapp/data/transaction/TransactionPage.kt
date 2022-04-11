package com.app.picpayapp.data.transaction

import com.app.picpayapp.data.Pageable
import com.app.picpayapp.data.Sort

data class TransactionPage(
    val content: List<TransactionNetwork>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
)