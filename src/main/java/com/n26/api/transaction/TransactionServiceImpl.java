package com.n26.api.transaction;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.n26.api.dom.Transaction;
import com.n26.api.localcache.Transactioncache;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	Transactioncache transcache;
	
	@Override
	public ResponseEntity<?> addTrasaction(Transaction tran) {
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
		System.out.println(Timestamp.valueOf(localDateTime).getTime());
		LocalDateTime localDateTimeb4min = localDateTime.minusSeconds(60);
		System.out.println(Timestamp.valueOf(localDateTimeb4min).getTime());
		transcache.setTransaction(tran);
		if(new Timestamp(tran.getTimestamp()).before(Timestamp.valueOf(localDateTimeb4min))) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}else {
			
		}
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
	
	
	
	
}
