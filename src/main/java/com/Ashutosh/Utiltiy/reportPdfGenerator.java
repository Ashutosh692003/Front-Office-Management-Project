package com.Ashutosh.Utiltiy;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Ashutosh.entity.StudentEnqEntity;
import com.Ashutosh.entity.UserDtlsEntity;
import com.Ashutosh.repository.UserDtlsRepo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Component
public class reportPdfGenerator {
	                                     @Autowired
	                                     HttpSession session;
	                                      
	                                      @Autowired
	                                      UserDtlsRepo uRepo;
	                                     
public  boolean pdfGenerator(HttpServletResponse resp) throws DocumentException, IOException {
	
	            Document document = new Document(PageSize.A4);
	            PdfWriter.getInstance(document, resp.getOutputStream());
document.open();
	     	   Font fontTiltle =FontFactory.getFont(FontFactory.TIMES_ROMAN);
	     	  fontTiltle.setSize(20);
	     	  
	     Paragraph para1 = new Paragraph("LIST OF ENQUIRIES",fontTiltle);
	     para1.setAlignment(Paragraph.ALIGN_CENTER);
	     document.add(para1); 
	     
	     PdfPTable table = new PdfPTable(6);
	     table.setWidthPercentage(100f);
	     table.setWidths(new int[] {3,3,3,3,3,3});
	     table.setSpacingBefore(5);
	       PdfPCell cell = new PdfPCell();
	              cell.setBackgroundColor(CMYKColor.BLUE);
	              cell.setPadding(5);
	              Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	              font.setColor(CMYKColor.WHITE);
	              
	              cell.setPhrase(new Phrase("ID", font));
	              table.addCell(cell);
	              cell.setPhrase(new Phrase(" Name", font));
	              table.addCell(cell);
	              cell.setPhrase(new Phrase("Course", font));
	              table.addCell(cell);
	              cell.setPhrase(new Phrase("Phone-Number", font));
	              table.addCell(cell);
	              cell.setPhrase(new Phrase("Enquiry-Date", font));
	              table.addCell(cell);
	              cell.setPhrase(new Phrase("Enquiry-Status", font));
	              table.addCell(cell);
	                    // logic to fetch data
	     Integer userId = (Integer) session.getAttribute("userId");
	           Optional<UserDtlsEntity>  user2 =            uRepo.findById(userId)  ;
	                       UserDtlsEntity user = user2.get();
	                       
	                       List<StudentEnqEntity> enq = user.getEnquiries();
	                       
	                       for(StudentEnqEntity enquiries : enq) {
	                   	    
	                   	    table.addCell(String.valueOf(enquiries.getEnqId()));
	                   	    table.addCell(enquiries.getName());
	                   	    table.addCell(enquiries.getCourse());
	                   	    table.addCell(enquiries.getNumber()+"");
	                   	    table.addCell(enquiries.getDateCreated()+"");
	                   	    table.addCell(enquiries.getEnqStatus());   
	                      }
	                      document.add(table);
	                      document.close();
	                       
	         
	     
	     
	     
	           
	
	  return true;
}

}
