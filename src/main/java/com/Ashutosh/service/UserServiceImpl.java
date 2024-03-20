package com.Ashutosh.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ashutosh.Utiltiy.EmailUtils;
import com.Ashutosh.Utiltiy.PwdUtils;
import com.Ashutosh.binding.ForgotBinding;
import com.Ashutosh.binding.LoginForm;
import com.Ashutosh.binding.SignUpForm;
import com.Ashutosh.binding.UnlockForm;
import com.Ashutosh.entity.UserDtlsEntity;
import com.Ashutosh.repository.UserDtlsRepo;

import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	 UserDtlsRepo uRepo;
	  
	  @Autowired
	  HttpSession session;
	 
	 @Autowired
	 EmailUtils email;

	@Override
	public String login(LoginForm form) {
		 System.out.println("inside log service");
		UserDtlsEntity user =           uRepo.findByEmail(form.getEmail());
		System.out.println(user);
		    if(!form.getPassword().equals(user.getPassword())) {
		    	 return "Incorrect Password";
		    }
		    else if(!user.getAcc_status().contentEquals("UNLOCKED")) {
		    	 return "Locked Account !!!!! Unlock From Gmail";
		    }
		     System.out.println("inside log service");
		   session.setAttribute("userId", user.getId());
		    
		    return "SucessFully login";
		 
		
		
		
	
	}

	@Override
	public boolean signUp(SignUpForm form) {
		            UserDtlsEntity user1 = uRepo.findByEmail(form.getEmail());
		                     
		                  if(user1!=null) {
		                	   return false;
		                  }
		UserDtlsEntity user = new UserDtlsEntity();
		
		          
		  BeanUtils.copyProperties(form, user);
		           
		  PwdUtils p = new PwdUtils();
		   String pw = p.randomPGenerator();
		           user.setPassword(pw);
		           
		          user.setAcc_status("LOCKED");
		    // for mail
		                 uRepo.save(user) ;
		     String to = form.getEmail();
		     String subject = "Unlock Your Account  |Ash-IT";
		   
		    StringBuffer body =new StringBuffer("");
		body.append("<h1>Use below temporary password to unlock your account</h1>");
	body.append("Temporary pwd :"+pw);
body.append("<a href=\"http://localhost:8080/unlock?email="+to+"\">Click Here to Unlock Your Account");
	
	boolean b=  email.sendEmail(to,subject,body.toString());
		           
		        
		return b;
	}

	@Override
	public boolean unlockAccount(UnlockForm form) {
	     UserDtlsEntity user =            uRepo.findByEmail(form.getEmail());
	     
	      System.out.println("Inside service");
	         String temp = user.getPassword();
	             if(form.getTempPass().equals(temp)) {
	            	      user.setPassword(form.getPassword());
	            	      user.setAcc_status("UNLOCKED");
	            	      uRepo.save(user);
	            	      
	            	      return true;
	             }
		return false;
	}

	@Override
	public String forgotpwd( String Email) {
	UserDtlsEntity user = 	     uRepo.findByEmail(Email);
	 String pass = user.getPassword();
		
		 String to = Email;
		 String header = "<h1>Password Reset (AshIT)</h1>";
		 String body = "Your Password :"+pass;
		boolean b =          email.sendEmail(to,header, body);
		       if(b=true) {
		    	   return "Password Sended Sucessfully to Email" ;
		       }
		       else {
		return "Something went wrong";
		}
	}
             
}
