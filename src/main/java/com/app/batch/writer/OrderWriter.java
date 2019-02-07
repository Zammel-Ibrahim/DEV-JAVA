package com.app.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.app.batch.DataBaseWriter;
import com.app.batch.dto.EmployeeDTO;
import com.app.batch.dto.OrderDTO;

public class OrderWriter  implements ItemWriter<OrderDTO> {

	private DataBaseWriter  dataBaseWriter;

	public OrderWriter(DataBaseWriter dataBaseWriter) {
		super();
		this.dataBaseWriter = dataBaseWriter;
	}

	@Override
	public void write(List<? extends OrderDTO> orders) throws Exception {
		// TODO Auto-generated method stub
		
		
		for (OrderDTO order : orders) {
			try {		
				dataBaseWriter.addOrUpdateOrder(order);
				
			}catch (Exception e){
					e.printStackTrace();
				
			}
		}
		
	}
	
	
	
}



