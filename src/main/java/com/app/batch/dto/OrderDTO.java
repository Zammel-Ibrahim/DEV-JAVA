package com.app.batch.dto;

import java.math.BigDecimal;
import java.util.Date;


public class OrderDTO {

	
	    private Integer id;
	    private Integer employeeId;
	    private Integer customerId;
	    private String    orderDate;
	    private String    shippedDate;
	    private String    paidDate;
	    private String  shipName;
	    private String  shipAddress1;
	    private String  shipAddress2;
	    private String  shipCity;
	    private String  shipState;
	    private String  shipPostalCode;
	    private String  shipCountry;
	    private BigDecimal shippingFee;
	    private String paymentType;
	    private String orderStatus;
		
	    public OrderDTO() {
			super();
		}
		
	    
	    public OrderDTO(Integer id, Integer employeeId, Integer customerId, String orderDate, String shippedDate,
	    		String paidDate, String shipName, String shipAddress1, String shipAddress2, String shipCity,
				String shipState, String shipPostalCode, String shipCountry, BigDecimal shippingFee, String paymentType,
				String orderStatus) {
			super();
			this.id = id;
			this.employeeId = employeeId;
			this.customerId = customerId;
			this.orderDate = orderDate;
			this.shippedDate = shippedDate;
			this.paidDate = paidDate;
			this.shipName = shipName;
			this.shipAddress1 = shipAddress1;
			this.shipAddress2 = shipAddress2;
			this.shipCity = shipCity;
			this.shipState = shipState;
			this.shipPostalCode = shipPostalCode;
			this.shipCountry = shipCountry;
			this.shippingFee = shippingFee;
			this.paymentType = paymentType;
			this.orderStatus = orderStatus;
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public Integer getEmployeeId() {
			return employeeId;
		}


		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
		}


		public Integer getCustomerId() {
			return customerId;
		}


		public void setCustomerId(Integer customerId) {
			this.customerId = customerId;
		}


		public String getOrderDate() {
			return orderDate;
		}


		public void setOrderDate(String orderDate) {
			this.orderDate = orderDate;
		}


		public String getShippedDate() {
			return shippedDate;
		}


		public void setShippedDate(String shippedDate) {
			this.shippedDate = shippedDate;
		}


		public String getPaidDate() {
			return paidDate;
		}


		public void setPaidDate(String paidDate) {
			this.paidDate = paidDate;
		}


		public String getShipName() {
			return shipName;
		}


		public void setShipName(String shipName) {
			this.shipName = shipName;
		}


		public String getShipAddress1() {
			return shipAddress1;
		}


		public void setShipAddress1(String shipAddress1) {
			this.shipAddress1 = shipAddress1;
		}


		public String getShipAddress2() {
			return shipAddress2;
		}


		public void setShipAddress2(String shipAddress2) {
			this.shipAddress2 = shipAddress2;
		}


		public String getShipCity() {
			return shipCity;
		}


		public void setShipCity(String shipCity) {
			this.shipCity = shipCity;
		}


		public String getShipState() {
			return shipState;
		}


		public void setShipState(String shipState) {
			this.shipState = shipState;
		}


		public String getShipPostalCode() {
			return shipPostalCode;
		}


		public void setShipPostalCode(String shipPostalCode) {
			this.shipPostalCode = shipPostalCode;
		}


		public String getShipCountry() {
			return shipCountry;
		}


		public void setShipCountry(String shipCountry) {
			this.shipCountry = shipCountry;
		}


		public BigDecimal getShippingFee() {
			return shippingFee;
		}


		public void setShippingFee(BigDecimal shippingFee) {
			this.shippingFee = shippingFee;
		}


		public String getPaymentType() {
			return paymentType;
		}


		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}


		public String getOrderStatus() {
			return orderStatus;
		}


		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}
	
	
	    
	   
	    
	    
	
	
}
