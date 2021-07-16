package com.ascent.demo.dao;

import java.util.List;

import com.ascent.demo.model.Employee;

public interface EmployeeDAO {

	public List<Employee> fetchAllEmployees();
	public boolean createEmployee(Employee newEmployee);
	public void deleteEmployee(int id);
	public void updateEmployee(Employee employee);
	public Employee getEmployeeById(int id);
}
