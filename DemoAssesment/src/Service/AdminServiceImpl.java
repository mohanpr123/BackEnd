package Service;

import java.util.HashMap;
import java.util.*;
import java.util.Scanner;
import entity.Address;
import entity.Employee;
import entity.User;

public class AdminServiceImpl implements AdminService {

	Scanner s = new Scanner(System.in);

	private  String adminUsername = "admin";
	private String adminPassword = "admin";
	private String[] roles = { "Manager", "Supervisor", "Technician", "Intern" };
	private static  Map<Integer, Employee> employees = new HashMap<>();
	private  static List<Employee> list=new  ArrayList<>();
	
	public  static Map<Integer, Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Map<Integer, Employee> employees) {
		this.employees = employees;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public static List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	@Override
	public void viewAllRecords() {
		for(Employee emp:list) {
			System.out.println(emp);
		}
	}

	@Override
	public void addEmployee() {
		System.out.println("What role you would like to add");
		System.out.printf("1%10s2%10s3%10s5\n", "", "", "");
		System.out.println(Arrays.toString(roles));
		int roleNo=s.nextInt();
		String role=roles[roleNo-1];
		System.out.println("Create ID and PASSWORD for Employee");
			System.out.print("ID: ");
			int id = s.nextInt();
			s.nextLine();
			System.out.print("\nUSERNAME: ");
			String uName = s.nextLine();
			System.out.print("\nPASSWORD: ");
			String pass = s.nextLine();
			System.out.print("\nNAME: ");
			String name = s.nextLine();
			System.out.print("\nGENDER: ");
			String gender = s.nextLine();
			System.out.print("\nADDRESS LINE 1: ");
			String ad1 = s.nextLine();
			System.out.print("\nADDRESS LINE 1: ");
			String ad2 = s.nextLine();
			System.out.print("\nCITY: ");
			String city = s.nextLine();
			System.out.print("\nSTATE: ");
			String state = s.nextLine();
			System.out.print("\nSALARY: ");
			double salary = s.nextDouble();
			System.out.print("\nEXPERIENCE: ");
			int exp = s.nextInt();
			System.out.println("ASSIGN LEAD NAME: ");
			String lead="No lead available";
			if(!role.equals(roles[0])) {
				for(Employee emp:list){
					if(list.isEmpty())
						System.out.println("No employee avbl to assign lead");
					if(emp.getRole().equals("Manager"))
						System.out.println(emp);
				}
				lead=s.next();
			}
			else {
				System.out.println("No lead for manager");
			}
			if(!role.equals(roles[0]) && !role.equals(roles[1])) {
				for(Employee emp:list){
					if(list.isEmpty())
						System.out.println("No employee avbl to assign lead");
					if(emp.getRole().equals("Supervisor"))
						System.out.println(emp);
				}
				lead=s.next();
			}

			employees.put(id, new Employee(id, name, gender, new Address(ad1, ad2, city, state), salary, exp,role,lead,new User(id, uName, pass)));
			list.add(employees.get(id));
					
	}

	@Override
	public void setRole_lead() {

	}

}
