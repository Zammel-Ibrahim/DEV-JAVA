package com.app.model.customer;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import io.swagger.annotations.ApiModelProperty;

@Data
@Entity
@Table(name = "customers")
public class Customer  {

    //@GeneratedValue(strategy = GenerationType.AUTO)
	/*@SequenceGenerator(name = "seq_customers", sequenceName = "seq_customers",
		    initialValue = 2000, allocationSize = 4)
			     @GeneratedValue(generator = "seq_customers")*/	
    
	@Id
    private Integer id;
    private String lastName;
    private String firstName;
    private String email;
    private String company;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    public Customer(){}
}
