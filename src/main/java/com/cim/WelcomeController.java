package com.cim;

import java.util.Calendar;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class WelcomeController {

	// go to welcome page
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		
		model.put("adCampaign", new AdCampaign()); 
		
		return "welcome";
	}
	
	// Create a new ad campaign
    @RequestMapping(value = "/createad", method = RequestMethod.POST)
    public String home(@ModelAttribute("adCampaign")AdCampaign adCampaign, ModelMap model) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	// look for active ad campaign
		String url = "http://localhost:8080/ad/" + adCampaign.getPartner_id();
		AdCampaign activeCampaign = restTemplate.getForObject(url, AdCampaign.class);
    	
		if (activeCampaign==null) {
	    	Calendar cal = Calendar.getInstance();
	    	cal.add(Calendar.SECOND, adCampaign.getDuration());
	    	adCampaign.setExpiredDateTime(cal);
	    	
			url = "http://localhost:8080/ad";
			String resultStr = restTemplate.postForObject(url, adCampaign, String.class);
			model.addAttribute("message", "This Partner ID (" + adCampaign.getPartner_id() + ") has been successfully added.");
			return "successpage";
		} else {
			model.addAttribute("message", "This Partner ID (" + adCampaign.getPartner_id() + ") cannot be added because it is an existing active ad campaign.");
			return "errorpage";
		}

    }
    
    // Get active ad campaign
    @RequestMapping(value = "/getad/{partner_id}", method = RequestMethod.GET)
    public String good(@PathVariable("partner_id") String partnerID, ModelMap model) {

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/ad/{partner_id}";
		AdCampaign adCampaign = restTemplate.getForObject(url, AdCampaign.class, partnerID);

		if (adCampaign == null) {
			model.addAttribute("message", "There is no active ad campaign for the specified partner id (" + partnerID + ").");
			return "errorpage";
		} else {
			model.addAttribute("partner_id", adCampaign.getPartner_id());
			model.addAttribute("duration", adCampaign.getDuration());
			model.addAttribute("ad_content", adCampaign.getAd_content());
			return "displayad";
		}
		
    }
    

}
