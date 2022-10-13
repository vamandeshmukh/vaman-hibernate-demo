package com.vaman.hibernate.demo.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import com.vaman.hibernate.demo.dao.EmployeeDao;
import com.vaman.hibernate.demo.exception.EmployeeNotFoundException;
import com.vaman.hibernate.demo.model.Employee;

public class EmployeeService {

	private EmployeeDao dao = new EmployeeDao();
	private EntityManager manager = dao.getEntityManager();

	public List<Employee> viewAllEmployees() {
		System.out.println("viewAllEmployees");
		return null;
	}

	public Employee viewEmployeeById(int employeeId) {
		System.out.println("viewEmployeeById " + employeeId);
		dao.beginTransaction();
		Employee tempEmp = manager.find(Employee.class, employeeId);
		dao.commitTransaction();
		if (null != tempEmp)
			return tempEmp;
		throw new EmployeeNotFoundException("Employee with eid " + employeeId + " does not exist.");
	}

	public Employee addEmployee(Employee employee) {
		System.out.println("addEmployee\n" + employee.toString());
		dao.beginTransaction();
		try {
			manager.persist(employee);
			dao.commitTransaction();
			return employee;
		} catch (EntityExistsException e) {
			System.out.println(e.getMessage());
			dao.commitTransaction();
			return null;
		}

	}

	public Employee updateEmployee(Employee employee) {
		System.out.println("updateEmployee\n" + employee.toString());
		dao.beginTransaction();
		try {
			manager.merge(employee);
			dao.commitTransaction();
			return employee;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			dao.commitTransaction();
			return null;
		}
	}

	public Employee deleteEmployeeById(int employeeId) {
		System.out.println("deleteEmployeeById " + employeeId);

		dao.beginTransaction();
		Employee empToDelete = viewEmployeeById(employeeId);
		try {
			manager.remove(empToDelete);
			dao.commitTransaction();
			return empToDelete;
		} catch (EntityExistsException e) {
			System.out.println(e.getMessage());
			dao.commitTransaction();
			return null;
		}
	}
}
