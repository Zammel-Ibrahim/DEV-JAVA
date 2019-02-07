package com.app.batch.dto;

public class CustommerDTO {
	
	private String id ;
	private String lastName;
	private String firstName;
	private String email;
	
	private String company;
	private String phone;
	private String adresse1;
	private String adresse2;
	private String city;
	private String state;
	private String codePostal;
	private String country;
	
	
	
	
	public CustommerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CustommerDTO(String id, String lastName, String firstName, String email) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
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
	
	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
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
		return "CustommerDTO [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ "]";
	}

}
