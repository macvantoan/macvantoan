package com.example.demo.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Employee;

public interface EmployeeService {

	List<Employee> getAllemployees();

	boolean createPdf(List<Employee> employess, ServletContext context, HttpServletRequest requset,
			HttpServletResponse reponse);

}
