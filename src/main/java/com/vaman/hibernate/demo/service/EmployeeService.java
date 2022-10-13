package com.vaman.hibernate.demo.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.vaman.hibernate.demo.dao.EmployeeDao;
import com.vaman.hibernate.demo.exception.EmployeeNotFoundException;
import com.vaman.hibernate.demo.model.Employee;

public class EmployeeService {

	private EmployeeDao dao = new EmployeeDao();
	private EntityManager manager = dao.getEntityManager();

	@SuppressWarnings("unchecked")
	public List<Employee> viewAllEmployees() {
		System.out.println("viewAllEmployees");
		dao.beginTransaction();
		List<Employee> empList;
		Query query = manager.createQuery("from Employee");
		try {
			empList = query.getResultList();
			dao.commitTransaction();
			return empList;
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
			dao.commitTransaction();
			return null;
		}
	}

	public Employee viewEmployeeById(int employeeId) {
		System.out.println("viewEmployeeById " + employeeId);
		dao.beginTransaction();
		Employee tempEmp = manager.find(Employee.class, employeeId);
		dao.commitTransaction();
		if (null != tempEmp) {
			dao.commitTransaction();
			return tempEmp;
		}
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
		Employee empToDelete = viewEmployeeById(employeeId);
		dao.beginTransaction();
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
