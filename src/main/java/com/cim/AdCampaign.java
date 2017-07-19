package com.cim;

import java.util.Calendar;

public class AdCampaign {
	
	private String partner_id;
	private int duration;
	private String ad_content;
	private Calendar expiredDateTime;
	
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAd_content() {
		return ad_content;
	}
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

	public Calendar getExpiredDateTime() {
		return expiredDateTime;
	}
	public void setExpiredDateTime(Calendar expiredDateTime) {
		this.expiredDateTime = expiredDateTime;
	}
	@Override
	public String toString() {
		return "AdCampaign [partner_id=" + partner_id + ", duration=" + duration + ", ad_content=" + ad_content + ", expiredDateTime=" + expiredDateTime.getTimeInMillis() +"]";
	}
}
