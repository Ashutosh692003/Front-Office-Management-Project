package com.Ashutosh.service;

import com.Ashutosh.binding.ForgotBinding;
import com.Ashutosh.binding.LoginForm;
import com.Ashutosh.binding.SignUpForm;
import com.Ashutosh.binding.UnlockForm;

public interface UserService {
 public String login(LoginForm form);
 
 public boolean signUp(SignUpForm form);
 
 public boolean unlockAccount(UnlockForm form);
 
  public String forgotpwd( String email);
   
}
