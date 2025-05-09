package entity;

public class Address {

	private String lineOne;
	private String lineTwo;
	private String city;
	private String state;

	public Address(String lineOne, String lineTwo, String city, String state) {
		super();
		this.lineOne = lineOne;
		this.lineTwo = lineTwo;
		this.city = city;
		this.state = state;
	}

	public String getLineOne() {
		return lineOne;
	}

	public void setLineOne(String lineOne) {
		this.lineOne = lineOne;
	}

	public String getLineTwo() {
		return lineTwo;
	}

	public void setLineTwo(String lineTwo) {
		this.lineTwo = lineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Address: "+getLineTwo()+getLineTwo()+" City: "+getCity()+" State:"+getState();
	}

}
