package com.app.batch.dto;

public class EmployeeDTO {
	
	private String id ;
	private String lastName;
	private String firstName;
	private String email;
	private String avatar;
	private String jobTitle;
	private String departement;
	private String managerId;
	private String phone;
	private String adresse1;
	private String adresse2;
	private String city;
	private String state;
	private String codePostal;
	private String country;
	
	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeDTO(String id, String lastName, String firstName, String email, String avatar, String jobTitle,
			String departement, String managerId, String phone, String adresse1, String adresse2, String city,
			String state, String codePostal, String country) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.avatar = avatar;
		this.jobTitle = jobTitle;
		this.departement = departement;
		this.managerId = managerId;
		this.phone = phone;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.city = city;
		this.state = state;
		this.codePostal = codePostal;
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
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

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", avatar=" + avatar + ", jobTitle=" + jobTitle + ", departement=" + departement + ", managerId="
				+ managerId + ", phone=" + phone + ", adresse1=" + adresse1 + ", adresse2=" + adresse2 + ", city="
				+ city + ", state=" + state + ", codePostal=" + codePostal + ", country=" + country + "]";
	}
	
}
