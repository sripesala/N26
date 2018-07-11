package com.n26.api.localcache;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.n26.api.dom.Statistics;
import com.n26.api.dom.Transaction;

@Component
public class Transactioncache {
	
	private static ArrayList<Transaction> translist = new ArrayList<>();
	
	public void setTransaction(Transaction trans) {
		synchronized (translist) {
			translist.add(trans);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Transaction> getTransactionList(){
		return (ArrayList<Transaction>) translist.clone();
	}
	
	public Statistics getStatistics() {
		if(translist==null) {
			return new Statistics();
		}
		List<Double> transminlist = translist.stream().filter((trans)->{return transwithinmin(trans);}).
										map((trans)->trans.getAmount()).collect(Collectors.toList());
		
		DoubleSummaryStatistics stat = transminlist.stream().mapToDouble((tran)->tran).summaryStatistics();
		Statistics statistics = new Statistics();
		statistics.setAvg(stat.getAverage());
		statistics.setCount(stat.getCount());
		statistics.setMax(stat.getMax());
		statistics.setMin(stat.getMin());
		statistics.setSum(stat.getSum());
		return statistics;
	}
	
	public Boolean transwithinmin(Transaction tran) {
		LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
		LocalDateTime localDateTimeb4min= localDateTime.minusSeconds(60);
		System.out.println(new Timestamp(tran.getTimestamp()) +" "+Timestamp.valueOf(localDateTimeb4min));
		if(new Timestamp(tran.getTimestamp()).before(Timestamp.valueOf(localDateTimeb4min))) {
			return false;
		}	
		return true;
	}
}
