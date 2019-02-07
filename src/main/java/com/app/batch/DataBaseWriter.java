package com.app.batch;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomMapEditor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.batch.dto.CustommerDTO;
import com.app.batch.dto.EmployeeDTO;
import com.app.batch.dto.OrderDTO;
import com.app.model.customer.Customer;
import com.app.model.employee.Employee;
import com.app.model.order.Order;
import com.app.repo.CustomerRepo;
import com.app.repo.EmployeeRepo;
import com.app.repo.OrderRepo;

import io.swagger.annotations.ApiModelProperty;


@Component
public class DataBaseWriter {
	
	  @Autowired private CustomerRepo customerRepo;
	  @Autowired private EmployeeRepo employeeRepo;
	  @Autowired private OrderRepo orderRepo;
	  
	  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  
	
	public void addCustommer(CustommerDTO custommerDTO ) throws SQLException {
		if (custommerDTO!=null) {
			
		    Customer c=new Customer();
		    c.setId(Integer.valueOf(custommerDTO.getId()));
		    c.setLastName(custommerDTO.getLastName());
		    c.setFirstName(custommerDTO.getFirstName());
		    c.setCompany(custommerDTO.getCompany());
		    
		    c.setPhone((custommerDTO.getPhone()));
		    c.setAddress1(custommerDTO.getAdresse1());
		    c.setAddress2(custommerDTO.getAdresse2());
		    c.setCity(custommerDTO.getCity());
		    c.setState(custommerDTO.getState());
		    c.setPostalCode(custommerDTO.getCodePostal());
		    c.setCountry(custommerDTO.getCountry());
		    
		    
		    customerRepo.save(c);
			System.out.println("after save new objects  "+custommerDTO.getFirstName());
			
		}
		
	}
	
	
	public void addPrUpdateEmployee(EmployeeDTO employeeDTO ) throws SQLException {
		if (employeeDTO!=null) {
			
		    Employee e =new Employee();
		    e.setId(Integer.valueOf(employeeDTO.getId()));
		    e.setLastName(employeeDTO.getLastName());
		    e.setFirstName(employeeDTO.getFirstName());
		    e.setEmail(employeeDTO.getEmail());
		    e.setAvatar(employeeDTO.getAvatar());
		    e.setJobTitle(employeeDTO.getJobTitle());
		    e.setDepartment(employeeDTO.getDepartement());
		    if (employeeDTO.getManagerId()!=null && employeeDTO.getManagerId().length()>0) {
		    e.setManagerId((Integer.valueOf(employeeDTO.getManagerId())));
		    }
		    e.setPhone(employeeDTO.getPhone());
		    e.setAddress1(employeeDTO.getAdresse1());
		    e.setAddress2(employeeDTO.getAdresse2());
		    e.setCity(employeeDTO.getCity());
		    e.setState(employeeDTO.getState());
		    e.setPostalCode(employeeDTO.getCodePostal());
		    e.setCountry(employeeDTO.getCountry());
		    
		    
		    employeeRepo.save(e);
		}
		
	}
	
	public void addOrUpdateOrder(OrderDTO orderDTO ) throws SQLException {
		if (orderDTO!=null) {
			
		    Order e =new Order();
		    e.setId(orderDTO.getId()); 
		    e.setEmployeeId(orderDTO.getEmployeeId());
		    e.setCustomerId(orderDTO.getCustomerId());
		    e.setOrderDate(getDateFromString(orderDTO.getOrderDate()));
		    e.setShippedDate(getDateFromString(orderDTO.getShippedDate()));
		    e.setPaidDate(getDateFromString(orderDTO.getPaidDate()));
		    e.setShipName(orderDTO.getShipName());
		    e.setShipAddress1(orderDTO.getShipAddress1());
		    e.setShipAddress2(orderDTO.getShipAddress2());
		    e.setShipCity(orderDTO.getShipCity());
		    e.setShipState(orderDTO.getShipState());
		    e.setShipPostalCode(orderDTO.getShipPostalCode());
		    e.setShipCountry(orderDTO.getShipCountry());
		    e.setShippingFee(orderDTO.getShippingFee());
		    e.setPaymentType(orderDTO.getPaymentType());
		    e.setOrderStatus(orderDTO.getOrderStatus());
		    

		    		    
		    orderRepo.save(e);
		}
		
	}
	
	private Date getDateFromString (String  dateChaine)  {
		if (dateChaine!=null) {
			
			 Date date=null;
			try {
				date = sdf.parse(dateChaine);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 return date;
		}
		
		return null;
	}
	
	
	

}
