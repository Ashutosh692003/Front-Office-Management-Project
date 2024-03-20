package com.Ashutosh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ashutosh.binding.DashboardResponse;
import com.Ashutosh.binding.EnquiryForm;
import com.Ashutosh.binding.EnquirySearchCriteria;
import com.Ashutosh.entity.CourseEntity;
import com.Ashutosh.entity.EnqStatusEntity;
import com.Ashutosh.entity.StudentEnqEntity;
import com.Ashutosh.entity.UserDtlsEntity;
import com.Ashutosh.repository.CourseRepo;
import com.Ashutosh.repository.EnqStatusRepo;
import com.Ashutosh.repository.StudentEnqRepo;
import com.Ashutosh.repository.UserDtlsRepo;

import jakarta.servlet.http.HttpSession;
@Service
public class EnquiryServiceImpl implements EnquiryService {
	   @Autowired
	   UserDtlsRepo uRepo;
	   
	   @Autowired
	   CourseRepo cRepo;
	   
	   @Autowired
	   EnqStatusRepo eRepo;
	   
	   @Autowired
	   StudentEnqRepo sRepo;
	   
	   @Autowired
	    HttpSession session;            

	@Override
	public List<String> getCourseName() {
		       
	List<CourseEntity>	 course=     cRepo.findAll();
	     List<String> name = new ArrayList<>();
	      for(CourseEntity user:course) {
	    	   name.add(user.getName());
	      }
	      return name;
	}

	@Override
	public List<String> getEnqStatus() {
	List<EnqStatusEntity> enq =    eRepo.findAll();
	    List<String> status = new ArrayList<>();
	     for(EnqStatusEntity type :enq) {
	    	  status.add(type.getStatusName());
	     }
		return status;
	}

	@Override
	public DashboardResponse getDashboardData(Integer userID) {
		 
		DashboardResponse resp = new DashboardResponse();
		
		     Optional<UserDtlsEntity> user = uRepo.findById(userID) ;
		     
		     
		            if(user.isPresent()) {
		           UserDtlsEntity staff =          user.get();
		             
		    List<StudentEnqEntity>  enquiries =    staff.getEnquiries();
		             Integer total = enquiries.size();
		             
		 List<StudentEnqEntity> enrolled =     enquiries.stream()
		        .filter(e->e.getEnqStatus().equals("ENROLLED")).
		        collect(Collectors.toList());
		   
		  Integer enrolledS = enrolled.size();
		 List<StudentEnqEntity> lost =     enquiries.stream()
			        .filter(e->e.getEnqStatus().equals("LOST")).
			        collect(Collectors.toList());
		 Integer lostS = lost.size();
		 
		  resp.setTotal(total);
		  resp.setLost(lostS);
		  resp.setEnrolled(enrolledS);
		      return resp;
		 
	}
		          
		                      
		                                   
		return null;
	}

	@Override
	public String upsertEnquiry(EnquiryForm form) {
		  StudentEnqEntity student = new StudentEnqEntity()  ;
		   BeanUtils.copyProperties(form, student);
	Integer userId =  (Integer) session.getAttribute("userId");
		Optional<UserDtlsEntity>    use =                uRepo.findById(userId);
		           UserDtlsEntity user = use.get();
		          
		        student.setUser(user);
		        
		   sRepo.save(student);
		        if(form.getEnqId()!=null) {
		        	 return "Updated";
		        }
		        else if(form.getEnqId()==null) {
		        	return "Added";
		        	
		        }
		        else {
		        	 return "Problem";
		        }
		
		   
	}
	

	@Override
	public List<StudentEnqEntity> getEnquiries(Integer userId, EnquirySearchCriteria criteria) {
		                           Optional<UserDtlsEntity>  user2 =        uRepo.findById(userId);
		                                        UserDtlsEntity user = user2.get();
		                               List<StudentEnqEntity> enquiries = user.getEnquiries();
	if(null!=criteria.getCourse()& !"".equals(criteria.getCourse())) {
	  
		enquiries = enquiries.stream().filter(e ->e.getCourse().equals(criteria.getCourse()))
	       .collect(Collectors.toList());
	}
	if(null!=criteria.getStatus()& !"".equals(criteria.getStatus())) {
		enquiries =      enquiries.stream().filter(e ->e.getEnqStatus().equals(criteria.getStatus()))
	       .collect(Collectors.toList());
	}
	if(null!=criteria.getMode()& !"".equals(criteria.getMode())) {
		enquiries =   enquiries.stream().filter(e ->e.getClassMode().equals(criteria.getMode()))
	       .collect(Collectors.toList());
	}
		                               
		                               
		return enquiries;
	}

	

	@Override
	public List<StudentEnqEntity> viewEnq(Integer id) {
	   Optional<UserDtlsEntity> us = uRepo.findById(id);
	            UserDtlsEntity user = us.get();
	            List<StudentEnqEntity>  enquiries =  user.getEnquiries();       
		return enquiries;
	}

	@Override
	public EnquiryForm edit(Integer id) {
	     Optional<StudentEnqEntity> entity =  sRepo.findById(id);
	     StudentEnqEntity user = new StudentEnqEntity();
	      if(entity.isPresent()) {
	                   user = entity.get();}
	                    System.out.println(user);
	             EnquiryForm form =new  EnquiryForm();   
	             BeanUtils.copyProperties(user, form);
	             
		return form;
	}

}
