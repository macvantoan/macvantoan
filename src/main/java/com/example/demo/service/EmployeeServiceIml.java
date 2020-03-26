package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



@Service
@Transactional
public class EmployeeServiceIml implements EmployeeService {
 
	@Autowired private  EmployeeRepository employeeReposity;
	@Override
	public List<Employee> getAllemployees() {
		// TODO Auto-generated method stub
		return (List<Employee>) employeeReposity.findAll();
	}
	@Override
	public boolean createPdf(List<Employee> employess, ServletContext context, HttpServletRequest requset,HttpServletResponse reponse) {

		 Document document = new Document(PageSize.A4, 15, 15, 45, 30);
	        try {
	        	String filePath = context.getRealPath("/resources/reports");
	        	File file = new File(filePath);
	        	boolean exists = new File(filePath).exists();
	        	if(!exists) {
	        		new File(filePath).mkdirs();
	        	}
	        	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"employess"+".pdf"));
	        	document.open();
	        	
	        	Font maintFont = FontFactory.getFont("Arial",10, BaseColor.BLACK);
	        	 
	        	Paragraph paragraph  = new Paragraph("All Employee", maintFont);
	        	paragraph.setAlignment(Element.ALIGN_CENTER);
	        	paragraph.setIndentationLeft(50);
	        	paragraph.setIndentationRight(50);
	        	paragraph.setSpacingAfter(10);
	        	document.add(paragraph);
	        	
	        	
	        	PdfPTable table = new PdfPTable(4);
	        	table.setWidthPercentage(100);
	        	table.setSpacingBefore(10f);
	        	table.setSpacingAfter(10);
	        	
	        	
	        	Font tableHeader = FontFactory.getFont("Arial",10,BaseColor.BLACK);
	        	Font tableBody = FontFactory.getFont("Arial",9,BaseColor.BLACK	);
	        	
	        	float[] columnWidths = {2f, 2f, 2f, 2f};
	        	
	        	table.setWidths(columnWidths);
	        	
	        	PdfPCell firtname =new PdfPCell(new Paragraph("firt name", tableHeader));               	
	        	firtname.setBorderColor(BaseColor.BLACK);
	        	firtname.setPaddingLeft(10);
	        	firtname.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	firtname.setVerticalAlignment(Element.ALIGN_CENTER);
	        	firtname.setBackgroundColor(BaseColor.GRAY);
	        	firtname.setExtraParagraphSpace(5f);
	        	table.addCell(firtname);
	        	
	        	PdfPCell lastname =new PdfPCell(new Paragraph("last name", tableHeader));               	
	        	lastname.setBorderColor(BaseColor.BLACK);
	        	lastname.setPaddingLeft(10);
	        	lastname.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	lastname.setVerticalAlignment(Element.ALIGN_CENTER);
	        	lastname.setBackgroundColor(BaseColor.GRAY);
	        	lastname.setExtraParagraphSpace(5f);
	        	table.addCell(lastname);
	        	
	        	PdfPCell email =new PdfPCell(new Paragraph("email ", tableHeader));               	
	        	email.setBorderColor(BaseColor.BLACK);
	        	email.setPaddingLeft(10);
	        	email.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	email.setVerticalAlignment(Element.ALIGN_CENTER);
	        	email.setBackgroundColor(BaseColor.GRAY);
	        	email.setExtraParagraphSpace(5f);
	        	table.addCell(email);
	        	
	        	PdfPCell phonenumber =new PdfPCell(new Paragraph("phone number ", tableHeader));               	
	        	phonenumber.setBorderColor(BaseColor.BLACK);
	        	phonenumber.setPaddingLeft(10);
	        	phonenumber.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	phonenumber.setVerticalAlignment(Element.ALIGN_CENTER);
	        	phonenumber.setBackgroundColor(BaseColor.GRAY);
	        	phonenumber.setExtraParagraphSpace(5f);
	        	table.addCell(phonenumber);
	        	
	        	
	        	for( Employee employee : employess) {
	        		PdfPCell firtnamevalue =new PdfPCell(new Paragraph(employee.getFirtsname(), tableBody));               	
	        		firtnamevalue.setBorderColor(BaseColor.BLACK);
	        		firtnamevalue.setPaddingLeft(10);
	        		firtnamevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		firtnamevalue.setVerticalAlignment(Element.ALIGN_CENTER);
	        		firtnamevalue.setBackgroundColor(BaseColor.WHITE);
	        		firtnamevalue.setExtraParagraphSpace(5f);
		        	table.addCell(firtname);
		        	
		        	PdfPCell lastnamevalue =new PdfPCell(new Paragraph(employee.getLastname(), tableBody));               	
		        	lastnamevalue.setBorderColor(BaseColor.BLACK);
		        	lastnamevalue.setPaddingLeft(10);
		        	lastnamevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	lastnamevalue.setVerticalAlignment(Element.ALIGN_CENTER);
		        	lastnamevalue.setBackgroundColor(BaseColor.WHITE);
		        	lastnamevalue.setExtraParagraphSpace(5f);
		        	table.addCell(lastnamevalue);
		        	
		        	PdfPCell emailvalue =new PdfPCell(new Paragraph(employee.getEmail(), tableBody));               	
		        	emailvalue.setBorderColor(BaseColor.BLACK);
		        	emailvalue.setPaddingLeft(10);
		        	emailvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	emailvalue.setVerticalAlignment(Element.ALIGN_CENTER);
		        	emailvalue.setBackgroundColor(BaseColor.WHITE);
		        	emailvalue.setExtraParagraphSpace(5f);
		        	table.addCell(emailvalue);
		        	
		        	PdfPCell phonevalue =new PdfPCell(new Paragraph(employee.getPhonenumber(), tableBody));               	
		        	phonevalue.setBorderColor(BaseColor.BLACK);
		        	phonevalue.setPaddingLeft(10);
		        	phonevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	phonevalue.setVerticalAlignment(Element.ALIGN_CENTER);
		        	phonevalue.setBackgroundColor(BaseColor.WHITE);
		        	phonevalue.setExtraParagraphSpace(5f);
		        	table.addCell(phonevalue);
	            	
	            	
	            	
	        	}
	        	document.add(table);
	        	document.close();
	        	writer.close();
	        	return true;
	        	
	        	
	        	
				
			} catch (Exception e) {
				return false;
			}
	}

}
