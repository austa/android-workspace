package com.example.ramazanzikir;

import java.io.Serializable;

public class ZikirClass implements Serializable {
	
	private String zikirAd;
	private int zikirAdet;
	
	public ZikirClass(String zikirAd,int zikirAdet) {
		this.zikirAd = zikirAd;
		this.zikirAdet = zikirAdet;
	}

	public String getZikirAd() {
		return zikirAd;
	}

	public void setZikirAd(String zikirAd) {
		this.zikirAd = zikirAd;
	}

	public int getZikirAdet() {
		return zikirAdet;
	}

	public void setZikirAdet(int zikirAdet) {
		this.zikirAdet = zikirAdet;
	}
	
	
	
	

}
