package com.Ashutosh.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Ashutosh.Utiltiy.reportPdfGenerator;
import com.Ashutosh.binding.DashboardResponse;
import com.Ashutosh.binding.EnquiryForm;
import com.Ashutosh.binding.EnquirySearchCriteria;
import com.Ashutosh.entity.StudentEnqEntity;
import com.Ashutosh.service.EnquiryService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
              @Autowired
              HttpSession session;
              
              @Autowired
              EnquiryService service;
              
              @Autowired
              reportPdfGenerator  generator;
               
              
                           @GetMapping("/logOut")
                        public String logout() {
                        	  session.invalidate();
                        	return "index";
                        }
                        	 
                  
                           
                      @GetMapping("/download")
          public void  download(HttpServletResponse resp) throws DocumentException, IOException   {
                   resp.setContentType("application/pdf");
                   resp.setHeader("Content-Disposition","attachment;filename=report.pdf");
                   generator.pdfGenerator(resp);
        	  
          }
                           
                           
                           
                           
                           
                           
                           
                           
	 
	 @GetMapping("/dashboard")
	public String dashboard(Model m) {
	Integer id = 	       (Integer) session.getAttribute("userId");
DashboardResponse resp = 	     service.getDashboardData(id);
      m.addAttribute("resp", resp);                   

		 return "dashboard";
	}
	 
	 @GetMapping("/addEnq")
	 public String addEnq(Model m) {
		   List<String> courses =  service.getCourseName();
		   List<String> status = service.getEnqStatus();
		   EnquiryForm form = new EnquiryForm();
		 System.out.println(courses);
		 System.out.println(status);
		  m.addAttribute("courses",courses);
		  m.addAttribute("status",status);
		  m.addAttribute("form", form);
		  
		 // binding data 
		 return "add-enquiry";	  
	 }
	 
	  @PostMapping("/addEnq")
	 public String addEnqDb(@ModelAttribute("form")EnquiryForm form,Model m) {
	String msg =	    service.upsertEnquiry(form);
		         if(msg.contains("Updated")) {
		        	  m.addAttribute("succMsg","Enquiry Updated Sucessfully");
		         }
		         else if(msg.contains("Added")) {
		        	 m.addAttribute("succMsg","Enquiry Added Sucessfully");
		         }
	
	
		         else {
		        	  m.addAttribute("errMsg", "Problem Occured");
		         }
		   return "add-enquiry";
		 
	 }
	         
	  
	 
	       @GetMapping("/enquires")
	 public String viewEnq(Model m) {
	    Integer id = 	    (Integer) session.getAttribute("userId");
	   List<StudentEnqEntity> enq  =  service.viewEnq(id); 
	   List<String> courses =  service.getCourseName();
	   List<String> status = service.getEnqStatus();
	    	    m.addAttribute("enq", enq); 
	    	    m.addAttribute("courses",courses);
	  		  m.addAttribute("status",status);
	    	         
		  return "view-enquiries";
	 }
	           @GetMapping("/edit")
	       public String edit(@RequestParam("id") Integer id,Model m) {
	        	    EnquiryForm form =             service.edit(id);
	        	    List<String> courses =  service.getCourseName();
	     		   List<String> status = service.getEnqStatus();
	        	    m.addAttribute("form", form);
	        	    m.addAttribute("courses",courses);
	      		  m.addAttribute("status",status);
	        	   
	      		 return "add-enquiry";
	       }
	           
	            @GetMapping("filter-enquiries")
	           public String filterdEnquiries(@RequestParam("cname") String course,@RequestParam("mode")String mode,@RequestParam("status")
	                                    String status,Model m    ) {
	            	    
	            	    
	            	    
	        	   Integer userId = (Integer) session.getAttribute("userId");
	        	   EnquirySearchCriteria enquiry = new  EnquirySearchCriteria();
	        	   enquiry.setCourse(course);
	        	   enquiry.setMode(mode);
	        	   enquiry.setStatus(status);
	        	   
	     List<StudentEnqEntity>   filteredD =	  service.getEnquiries(userId, enquiry);
	        	              m.addAttribute("enq", filteredD);
	        	    
	        	  return "filter" ;
	        	   
	        	   
	        	   
	        	   
	           }
	       
	        
}
