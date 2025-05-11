package controller;

import java.util.*;

import Service.AdminServiceImpl;
import Service.NonAdminServiceImpl;

public class EmployeeManagement {

	public static void main(String[] args) {

		AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
		NonAdminServiceImpl nonAdminServiceImpl = new NonAdminServiceImpl();
		Scanner s = new Scanner(System.in);

		int choice=0;
		do{
			System.out.println("LOGIN ADMIN/NON-ADMIN");
			String username=s.nextLine();
			String password=s.nextLine();

			if(adminServiceImpl.getAdminUsername().equals(username) && adminServiceImpl.getAdminPassword().equals(password)){
				System.out.println("--- ADMIN ACCESS ALLOWED ---");
				int pick;
				do{
					System.out.println("1. VIEW ALL RECORDS\n2. ADD EMPLOYEE\n3. SET ROLE AND LEAD\n4. LOGOUT");
					pick=s.nextInt();
					if(pick == 1) adminServiceImpl.viewAllRecords();
					if(pick == 2) adminServiceImpl.addEmployee();
					if(pick == 3) adminServiceImpl.setRole_lead();

				}while(pick!=4);
			}else{
				int id=Integer.parseInt(username);
				if(AdminServiceImpl.getEmployees().get(id).getUser().getId() == id && password.contains(AdminServiceImpl.getEmployees().get(id).getUser().getUserPassword())){
					System.out.println("--- EMPLOYEE ACCESS ALLOWED ---");
					int pick;
					do{
						System.out.println("1. VIEW ALL EMPLOYEES\n0. EXIT");
						pick=s.nextInt();
						if(pick == 1) nonAdminServiceImpl.viewProfile(id);
					}while(pick != 0);
				}
			}
			System.out.println("ENTER 0 TO  HOME OR ANY NUMBER TO CONTINUE");
			choice=s.nextInt();
			s.nextLine();
			if(choice==0) System.out.println("HOME");
		}while(choice !=0);
	}
}
