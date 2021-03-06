package Models;

import java.util.ArrayList;

public class User {
	protected String username, password;
	protected String name;
	protected String phoneNumber;
	protected String DoB;
	protected String gender;
	protected String ID;
	protected String email;
	protected String citizenID;
	protected ArrayList<Salary> salary;

	public User() {
	}

	public User(String ID, String username, String password, String name, String phoneNumber, String DoB, String gender, String email, String CitizenID, ArrayList<Salary> salary) {
		this.ID = ID;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.DoB = DoB;
		this.gender = gender;
		this.email = email;
		this.citizenID = CitizenID;
		this.salary = salary;
	}

	public void InputInformation() {

	}

	//GETTER

	public String getUsername() {
		return username;
	}
	public String getPassword() {return password;}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getID() {
		return ID;
	}
	public String getEmail() {
		return email;
	}
	public String getCitizenID() {
		return citizenID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getDoB() {
		return DoB;
	}

	public Integer getTotal() {
		int sum = 0;
		for(Salary s : salary) {
			sum += s.getAmount();
		}
		return sum;
	}
	//SETTER

	public void setName(String name) {
		this.name = name;
	}
	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}
	public void setDoB(String doB) {
		this.DoB = doB;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setID(String id) {
		this.ID = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void setCitizenID(String citizenID) {
		this.citizenID = citizenID;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	//Function

	/**
	 * Kiểm tra sơ xem giá trị id có phải của Manager hay không
	 * @return true/false
	 */
	public boolean isManager() {
		String code = this.ID.split("-")[0];
		return code.equals("MAN");
	}
}
