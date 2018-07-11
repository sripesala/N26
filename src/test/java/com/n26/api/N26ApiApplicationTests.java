package com.n26.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.api.dom.Transaction;
import com.n26.api.statistics.StatisticsService;
import com.n26.api.transaction.TransactionService;

@RunWith(SpringRunner.class)
//@SpringBootTest
@ContextConfiguration(classes=ApiController.class)
@WebMvcTest(ApiController.class)
public class N26ApiApplicationTests {
	
	@Autowired
    private MockMvc mvc;

	@MockBean
	private TransactionService transactionService;
	
	@MockBean
	private StatisticsService statisticsService;

	
	@Test
	public void testTransactions() {
		Transaction tran = new Transaction();
		tran.setAmount(12.3);
		tran.setTimestamp(new Long("147819220400"));
		try {
			mvc.perform(post("/api/transactions")
					.contentType(MediaType.APPLICATION_JSON)
			        .content(asJsonString(tran))).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStatistics() {		
		try {
			this.mvc.perform(get("/api/statistics"))
				.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
	
	
}
