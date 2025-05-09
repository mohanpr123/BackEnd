package controller;

import java.util.*;

import Service.AdminServiceImpl;
import Service.NonAdminServiceImpl;
import entity.*;

public class EmployeeManagement {

	public static void main(String[] args) {

		AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
		NonAdminServiceImpl nonAdminServiceImpl = new NonAdminServiceImpl();
		Scanner s = new Scanner(System.in);

		int choice=0;
		do {
			System.out.println("LOGIN ADMIN/NON-ADMIN");
			String username = s.nextLine();
			String password = s.nextLine();
			
			if (username.equals(adminServiceImpl.getAdminUsername())
					&& password.equals(adminServiceImpl.getAdminPassword())) {
				while(choice!=3) {
				System.out.println("--- ADMIN ACCESS ALLOWED ---");
				System.out.println("1. VIEW ALL RECORDS\n2. ADD EMPLOYEE\n3. SET ROLE AND LEAD\n4.LOGOUT");
				choice = s.nextInt();
				if (choice == 1)
					adminServiceImpl.viewAllRecords();
				if (choice == 2)
					adminServiceImpl.addEmployee();
				if (choice == 4)
					break;
				}
			}
			
			int id = Integer.valueOf(username);
			if (id == adminServiceImpl.getEmployees().get(id).getUser().getId()
					&& password.contains(adminServiceImpl.getEmployees().get(id).getUser().getUserPassword())) {

				System.out.println("--- EMPLOYEE ACCESS ALLOWED ---");
				while (true) {
					choice = s.nextInt();
					System.out.println("1. VIEW ALL RECORDS UNDER YOU");

					if (choice == 1)
						nonAdminServiceImpl.viewProfile(id);
					System.out.println("Press '1' to Coninute or '0' to exit");
					choice=s.nextInt();
					if(choice==0)break;
				}

			} else {
				System.err.println("ACCESS DENIED");
			}
		} while (choice != 0);
	}
}
