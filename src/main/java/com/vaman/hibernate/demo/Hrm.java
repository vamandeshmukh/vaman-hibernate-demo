package com.vaman.hibernate.demo;

import java.util.Scanner;

import com.vaman.hibernate.demo.model.Employee;
import com.vaman.hibernate.demo.service.EmployeeService;

public class Hrm {

	private static EmployeeService service = new EmployeeService();

	public static void selectOption() {

		try {

			System.out.println("\nPlease select an option to continue:");
			System.out.println(
					"\n1. View all employees\n2. View employee by id \n3. Add new employee\n4. Update employee \n5. Delete employee\n6. Exit");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("List of all employees:");
				service.viewAllEmployees().forEach(e -> System.out.println(e.toString()));
				Hrm.selectOption();
				break;
			case 2:
				System.out.println("Please enter employeeId to search:");
				int employeeid = sc.nextInt();
				System.out.println("Employee Details:");
				System.out.println(service.viewEmployeeById(employeeid).toString());
				Hrm.selectOption();
				break;
			case 3:
				System.out.println("Please enter employee details:");
				System.out.println("employeeId:");
				int employeeId = sc.nextInt();
				System.out.println("firstName:");
				String firstName = sc.next();
				System.out.println("salary:");
				double salary = sc.nextDouble();
				Employee employee = new Employee(employeeId, firstName, salary);
				service.addEmployee(employee);
				System.out.println("\nEmployee Details:\n" + employee.toString());
				Hrm.selectOption();
				break;
			case 4:
				System.out.println("Please enter employee details to update:");
				System.out.println("employeeId:");
				employeeId = sc.nextInt();
				System.out.println("firstName:");
				firstName = sc.next();
				System.out.println("salary:");
				salary = sc.nextDouble();
				employee = new Employee(employeeId, firstName, salary);
				service.updateEmployee(employee);
				System.out.println("\nEmployee Details:\n" + employee.toString());
				Hrm.selectOption();
				break;
			case 5:
				System.out.println("Please enter employeeId to delete:");
				employeeid = sc.nextInt();
				System.out.println("Employee Details:");
				System.out.println(service.deleteEmployeeById(employeeid).toString());
				Hrm.selectOption();
				break;
			case 6:
				Hrm.exitApp();
				;
				break;
			default:
				System.out.println("Wrong input!");
				Hrm.selectOption();
				break;
			}
			sc.close();
		} catch (Exception e) {

			System.out.println(e.getMessage());
			Hrm.selectOption();
		}
	}

	private static void exitApp() {
		System.out.println("Thanks for using this app!");
		System.exit(0);
	}
}
