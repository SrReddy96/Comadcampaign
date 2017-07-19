package com.cim;

import java.util.Calendar;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
		
	}
	
	@Bean
	public AdCampaign setup() {
		
		AdCampaign ad = new AdCampaign();
		ad.setPartner_id("testid");
		ad.setDuration(31536000);
		ad.setAd_content("New Year. New Deals.");
		
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.SECOND, 31536000);
    	ad.setExpiredDateTime(cal);
    	
        List<AdCampaign> listAd = AdDatabase.getListAd();
        listAd.add(ad);
        
        return ad;
	}

}
