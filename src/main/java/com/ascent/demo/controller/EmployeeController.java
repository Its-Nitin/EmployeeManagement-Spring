package com.ascent.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ascent.demo.dao.EmployeeDAO;
import com.ascent.demo.model.Employee;

@RestController
@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.GET ,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class EmployeeController {

	@Autowired
	EmployeeDAO employeeDAO;
	
	
	@GetMapping(value = "/allEmployees" )
	public ResponseEntity<List<Employee>> allEmployees(){
		
		List<Employee> allEmployees;
		allEmployees = employeeDAO.fetchAllEmployees();
		
		return new ResponseEntity<List<Employee>>(allEmployees,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/saveEmployee" ,method = RequestMethod.POST )
	public  ResponseEntity<Employee> saveEmployee(@RequestBody Employee newEmployee){
		
		employeeDAO.createEmployee(newEmployee);
		
		return new ResponseEntity<Employee>(newEmployee , HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/deleteEmployee/{id}" ,method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id){
		
		employeeDAO.deleteEmployee(id);
		return new ResponseEntity<Employee>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		
		employeeDAO.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getEmployeeById/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
		Employee employee = employeeDAO.getEmployeeById(id);
		if (employee !=null) {
			
			return new ResponseEntity<Employee>(employee , HttpStatus.OK);
		}else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	
	
}
