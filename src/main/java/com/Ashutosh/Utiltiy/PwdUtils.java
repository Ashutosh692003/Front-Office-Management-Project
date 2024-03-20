package com.Ashutosh.Utiltiy;

import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtils { 
	    public String randomPGenerator() {
	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789z";
	String pwd = RandomStringUtils.random( 15, characters );
	System.out.println( pwd );
	 return pwd;
	    }
}
