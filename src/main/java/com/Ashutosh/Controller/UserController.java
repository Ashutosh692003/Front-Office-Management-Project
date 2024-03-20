package com.Ashutosh.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Ashutosh.binding.ForgotBinding;
import com.Ashutosh.binding.LoginForm;
import com.Ashutosh.binding.SignUpForm;
import com.Ashutosh.binding.UnlockForm;
import com.Ashutosh.entity.UserDtlsEntity;
import com.Ashutosh.repository.UserDtlsRepo;
import com.Ashutosh.service.UserService;

@Controller
public class UserController {            
	                                        @Autowired
	                                 UserService service;
	                                         
	                                        @Autowired
	                                        UserDtlsRepo uRepo;
	                                        
	 @GetMapping("/login")
	 public String login(Model m) {
		  LoginForm log = new LoginForm();
		         m.addAttribute("log", log) ;
		  return "login";
	 }
	 
	 
	 @PostMapping("/login")
	 public String loginIn(@ModelAttribute("log")LoginForm form,Model m) {
		     UserDtlsEntity u  = uRepo.findByEmail(form.getEmail());
		     System.out.println("inside post");
		      if(u!=null) {
		   String status=  	  service.login(form);
		               if(status.contains("SucessFully")) {
		            	    return "redirect:/dashboard";
		               }
		               else {
		            	    
		            	   m.addAttribute("errMsg",status);
		               }
		    	  
		      }
		      
		      else {
		    	   m.addAttribute("errMsg","Account Not Exist");
		      }
		     
		 
		           
		 
		  return "login";
	 }
	  
	  @PostMapping("/signup")
	  public String signUp(@ModelAttribute("user")SignUpForm sign,Model m) {
		                         boolean status = service.signUp(sign) ;
		                 if(status) {
		                	 m.addAttribute("succMsg", "Check Your Email");
		                 }
		                 else {
		                	 m.addAttribute("errMsg", "Problem occured");
		                 }
		 return"signup"; 
	  }
	  
	  @GetMapping("/signup")
	 public String signup(Model m) {
		  
		    SignUpForm sign = new SignUpForm();
		    m.addAttribute("user",sign);
		  return "signup";
	 }
	  
	  @GetMapping("/forgot")
	  public String forgot(Model m) {
		   ForgotBinding fo= new ForgotBinding();
		     m.addAttribute("detail",fo);   
		   return "forgotPwd";
	  }
	  
	  @PostMapping("/forgot")
	  public String forgotCheck(@ModelAttribute("detail") ForgotBinding form,Model m) {
		   UserDtlsEntity u  = uRepo.findByEmail(form.getEmail());
		      if(u!=null) {
		    	        String email = u.getEmail();
		String succMsg =   service.forgotpwd(email);
	    m.addAttribute("succMsg", succMsg);
		    	   
		      }
		      else {
		    	   m.addAttribute("errMsg", "Invalid Email");
		      }
		      
		   return "forgotPwd";
	  }
	  
	  @GetMapping("unlock")
	   public String unlock(@RequestParam("email") String email,Model m) {
		           UnlockForm un = new UnlockForm();
		           System.out.println(email);
		             un.setEmail(email);
		           m.addAttribute("user",un);
		    
		   return "unlock";
	  }
	  
	  @PostMapping("unlock")
	   public String unlockAcc(@ModelAttribute("user")UnlockForm form,Model m) {
		       if(form.getPassword().equals(form.getConfPassword())) {
		    	   System.out.println("Inside posT rEQUEST");
		    	   
		    	boolean b =      service.unlockAccount(form);
		    	   if(b) {
		    		   m.addAttribute("succMsg", "Account Unlocked Sucessfully"); 
		    	   }
		    	   else {
		    		   m.addAttribute("errMsg", "Temporary Password Wrong !! Check Your Email ");
		    	   }
		       }
		       else {
		    	   m.addAttribute("errMsg", "Password and Confirm Password fields Should be same");
		       }
		          
		    
		   return "unlock";
	  }

}
