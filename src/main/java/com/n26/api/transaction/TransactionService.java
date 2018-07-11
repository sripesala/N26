package com.n26.api.transaction;

import org.springframework.http.ResponseEntity;

import com.n26.api.dom.Transaction;

public interface TransactionService {

	ResponseEntity<?> addTrasaction(Transaction tran);

}
