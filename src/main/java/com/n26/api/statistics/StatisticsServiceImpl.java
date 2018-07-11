package com.n26.api.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.api.dom.Statistics;
import com.n26.api.localcache.Transactioncache;

@Service
public class StatisticsServiceImpl implements StatisticsService{

	@Autowired
	Transactioncache transcache;
	
	@Override
	public Statistics getStatistics() {
		return transcache.getStatistics();
	}

}
