package entity;

public class Employee {

	private int id;
	private String name;
	private String gender;
	private Address address;
	private double salary;
	private int exp;
	private String role;
	private String lead;
	User user;
	
	public Employee(int id, String name, String gender, Address address, double salary, int exp,String role,String lead,User user) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.salary = salary;
		this.exp = exp;
		this.user=user;
		this.role=role;
		this.lead=lead;
	}


	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}


	public User getUser() {
		return user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}
	
	@Override
	public String toString() {
		return "Name: "+getName()+" Gender: "+getGender()+" Address: "+getAddress()+" Salary: "+getSalary()+" Experience: "+getExp()+" Role: "+getRole()+" Lead: "+getLead();
	}
}
