package com.Ashutosh.Runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.Ashutosh.entity.CourseEntity;
import com.Ashutosh.entity.EnqStatusEntity;
import com.Ashutosh.repository.CourseRepo;
import com.Ashutosh.repository.EnqStatusRepo;
@Component
public class runner implements ApplicationRunner {
	 
	    @Autowired
	    CourseRepo cRepo;
	    
	    @Autowired
	    EnqStatusRepo eRepo;
	    
	    

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		 cRepo.deleteAll();
		 eRepo.deleteAll();
		 
		 CourseEntity c1 = new CourseEntity();
		   c1.setName("Java");
		   
			 CourseEntity c2 = new CourseEntity();
			   c2.setName("AWS");
			   
				 CourseEntity c3 = new CourseEntity();
				   c3.setName("DevOps");
				   
				   
				   EnqStatusEntity e = new EnqStatusEntity();
				    e.setStatusName("ENROLLED");
				    
					   EnqStatusEntity e2 = new EnqStatusEntity();
					    e2.setStatusName("LOST");
					    
						   EnqStatusEntity e3 = new EnqStatusEntity();
						    e3.setStatusName("NEW");
		   cRepo.saveAll(Arrays.asList(c1,c2,c3));
		   eRepo.saveAll(Arrays.asList(e,e2,e3));
		
	}

}
