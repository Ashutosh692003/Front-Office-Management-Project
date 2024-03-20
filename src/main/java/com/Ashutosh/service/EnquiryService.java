package com.Ashutosh.service;

import java.util.List;

import com.Ashutosh.binding.DashboardResponse;
import com.Ashutosh.binding.EnquiryForm;
import com.Ashutosh.binding.EnquirySearchCriteria;
import com.Ashutosh.entity.StudentEnqEntity;

public interface EnquiryService {
    public List<String> getCourseName();
    
    public List<String> getEnqStatus();
    
     public DashboardResponse getDashboardData(Integer userID);
     
     public String upsertEnquiry(EnquiryForm form);
     
   public List<StudentEnqEntity> getEnquiries(Integer userId,EnquirySearchCriteria criteria);
   
   public List<StudentEnqEntity> viewEnq(Integer id);
    
   public EnquiryForm edit(Integer id);
   
}
