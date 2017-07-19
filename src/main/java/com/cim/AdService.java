package com.cim;

import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdService {

	// Based on partner_id to get active ad campaign
    @RequestMapping(method = RequestMethod.GET, value = "/ad/{partner_id}")
    AdCampaign getActive(@PathVariable("partner_id") String partnerID) {
    	
    	if (partnerID!=null) {
        	List<AdCampaign> listAd = AdDatabase.getListAd();
        	for( AdCampaign ad : listAd ) {
        		if (partnerID.equalsIgnoreCase(ad.getPartner_id())) {
        			if (ad.getExpiredDateTime().after(Calendar.getInstance())) {
        				return ad;
        			} else {
        				continue;
        			}
        		}
        	}
    	}
    	
        return null;
    }
    
    // Retrieve all ad campaigns
    @RequestMapping(method = RequestMethod.GET, value = "/getallad")
    List<AdCampaign> getAllAd() {
    	
    	List<AdCampaign> listAd = AdDatabase.getListAd();
    	
        return listAd;
    }
    
    // Create a new ad campaign
    @RequestMapping(value = "/ad", method = RequestMethod.POST)
    public String createAdCampaign(@RequestBody AdCampaign ad) 
    {

        List<AdCampaign> listAd = AdDatabase.getListAd();
        listAd.add(ad);

        return "success";
    }
}
