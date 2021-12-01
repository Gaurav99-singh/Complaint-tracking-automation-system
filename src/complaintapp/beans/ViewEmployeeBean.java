package complaintapp.beans;

public class ViewEmployeeBean 
{
	String employeeId,name,address,phoneNo,email,gender,experience;

	public ViewEmployeeBean(String employeeId, String name, String address, String phoneNo, String email, String gender,
			String experience) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.gender = gender;
		this.experience = experience;
	}

	public ViewEmployeeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

}
