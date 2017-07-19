package com.cimtest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cim.AdCampaign;
import com.cim.SpringBootWebApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBootWebApplication.class)
@SpringBootTest
public class AdServiceTest {

	private MockMvc mockMvc;
	private AdCampaign newAd = new AdCampaign();
	
	@Autowired
    private WebApplicationContext wac;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
        newAd.setPartner_id("comcast");
        newAd.setDuration(31536000);
        newAd.setAd_content("New Year. New Deals !");
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.SECOND, 31536000);
    	newAd.setExpiredDateTime(cal);
	}
	
	@Test
	public void testGetActive() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/ad/testid").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.partner_id").exists())
		.andExpect(jsonPath("$.duration").exists())
		.andExpect(jsonPath("$.ad_content").exists())
		.andExpect(jsonPath("$.expiredDateTime").exists())
		.andExpect(jsonPath("$.partner_id").value("testid"))
		.andExpect(jsonPath("$.duration").value(31536000))
		.andExpect(jsonPath("$.ad_content").value("New Year. New Deals."))
		.andDo(print());
	}
	
	@Test
	public void testGetActiveContentType() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/ad/testid").accept(MediaType.APPLICATION_JSON));

		result.andExpect(MockMvcResultMatchers.status().isOk())
			  .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8")).andDo(print());
		
	}
	
	@Test
	public void testGetActiveReturn() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/ad/test").accept(MediaType.APPLICATION_JSON));

		result.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
		result.andExpect(MockMvcResultMatchers.header().doesNotExist("Content-Type=[application/json;charset=UTF-8]")).andDo(print());

	}
	
	@Test
	public void testCreateAdCampaign() throws Exception {

		String requestBody = saveRequestJsonString(newAd);

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/ad")
																	 .accept(MediaType.APPLICATION_JSON)
																	 .content(requestBody)
																	 .contentType(MediaType.APPLICATION_JSON)
																	 );

		result.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
		result.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8")).andDo(print());
	
	}
	
    private String saveRequestJsonString(AdCampaign ad) { 
        return "{\n" + 
                "  \"partner_id\": \"" + ad.getPartner_id() + "\",\n" + 
                "  \"duration\": " + ad.getDuration() + ",\n" + 
                "  \"ad_content\": \"" + ad.getAd_content() + "\",\n" + 
                "  \"expiredDateTime\": " + ad.getExpiredDateTime().getTimeInMillis() + 
                "}";
    }
	
}
