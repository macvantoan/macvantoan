package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;


@Controller
public class pdfcontroller {
	 @Autowired private EmployeeService employeeService;
	 @Autowired private ServletContext context;
	 
	 @GetMapping(value="/")
 public String allEmployee(Model model) {
		  List<Employee> employees = employeeService.getAllemployees();
			  
		  model.addAttribute("employees", employees);
			  return "view/employess";
	
 }
	 @GetMapping(value="/createpdf")
	 public void createpdf(HttpServletRequest requset, HttpServletResponse reponse) {
		 List<Employee> employess = employeeService.getAllemployees();
		 boolean isFlag = employeeService.createPdf(employess, context, requset, reponse);

	    	if(isFlag) {
	    		String fullPath = requset.getServletContext().getRealPath("/resources/reports/"+"employess"+".pdf");
	    		filedowload(fullPath, reponse, "employess.pdf");
	    	}
	 }
	private void filedowload(String fullPath, HttpServletResponse reponse, String filename) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if(file.exists()) {
			try {
				FileInputStream inputstream = new FileInputStream(file);
				String mimetype = context.getMimeType(fullPath);
				reponse.setContentType(mimetype);
				reponse.setHeader("content", "namefile"+filename);
				OutputStream outputStream = reponse.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while((bytesRead = inputstream.read(buffer))!= -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputstream.close();
				
				outputStream.close();
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
}
	}
