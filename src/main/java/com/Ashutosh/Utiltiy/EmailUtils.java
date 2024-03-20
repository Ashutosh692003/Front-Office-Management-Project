package com.Ashutosh.Utiltiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;


@Component
public class EmailUtils {
	            @Autowired
	        JavaMailSender mailSender;
         public  boolean  sendEmail(String to,String subject,String body) {
      try {
        	 MimeMessage mimeMsg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new 	 MimeMessageHelper(mimeMsg,true);
         helper.setSubject(subject);	 
         helper.setText(body, true);
         helper.setTo(to);
          mailSender.send(mimeMsg);
      }
      catch(Exception e){
    	   e.printStackTrace();
    	   
      }
        	 
        	     
        	 
        	  return true;
         }
}
