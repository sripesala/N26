package com.n26.api;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.api.dom.Statistics;
import com.n26.api.dom.Transaction;
import com.n26.api.statistics.StatisticsService;
import com.n26.api.transaction.TransactionService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	StatisticsService statisticsService;

	/**
	 * Every Time a new transaction happened, a new record will be added.
	 * @param tran
	 * @return
	 */
	
	@PostMapping("/transactions")
	public ResponseEntity<?> addTrasaction(@RequestBody Transaction tran) {
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
		System.out.println(Timestamp.valueOf(localDateTime).getTime());

		return transactionService.addTrasaction(tran);
	}
	
	/**
	 * returns the statistic based on the transactions which happened in the last 60 seconds
	 * @return
	 */
	@GetMapping("/statistics")
	public Statistics getStatistics() {
		return statisticsService.getStatistics();
	}
	
	
	
}
