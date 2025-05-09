package entity;

public class User {
	private int id;
	private String userName;
	private String userPassword;
	private String role;
	private String lead;
	
	public User(int id, String userName, String userPassword) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
}
