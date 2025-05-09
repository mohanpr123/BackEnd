package Service;

public class NonAdminServiceImpl implements NonAdminServices {

	@Override
	public void viewProfile(int id) {
		AdminServiceImpl usersAdminServiceImpl=new AdminServiceImpl();
		System.out.println(usersAdminServiceImpl.getEmployees().get(id));
		
	}

}
