package com.ascent.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ascent.demo.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Employee> fetchAllEmployees() {
		
		List<Employee> employeeData =null;
		System.out.println("Reaching here ");

		Session session = sessionFactory.openSession();
			employeeData =  session.createQuery("from Employee").list();
		
		return employeeData;
	}

	@Override
	public boolean createEmployee(Employee newEmployee) {
	
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(newEmployee);
			session.getTransaction().commit();
		
			return true;
			
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public void deleteEmployee(int id) {

		Session session = sessionFactory.openSession();

		try {
			
		Employee employee = session.get(Employee.class, id);
		session.beginTransaction();
		session.delete(employee);
		session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
	
		Session session  = sessionFactory.openSession();
		
		try {
			
			session.beginTransaction();
			session.update(employee);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee getEmployeeById(int id) {
		Session session = sessionFactory.openSession();
		Employee employee = session.get(Employee.class, id);
		
		return employee;
	}
	
	
	
}
