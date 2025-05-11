package Service;

import entity.Employee;

public class NonAdminServiceImpl implements NonAdminServices {

	@Override
	public void viewProfile(int id) {


		if(AdminServiceImpl.getEmployees().get(id).getRole().equals("Manager"))
			for(Employee emp:AdminServiceImpl.getList()){
				if(!emp.getRole().equals("Manager"))
					System.out.println(emp);

			}

		if(AdminServiceImpl.getEmployees().get(id).getRole().equals("Supervisor"))
			for(Employee emp:AdminServiceImpl.getList()){
				if(!emp.getRole().equals("Manager") && !emp.getRole().equals("Supervisor") )
					System.out.println(emp);

			}

	}

}
