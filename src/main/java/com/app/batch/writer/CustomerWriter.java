package com.app.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.app.batch.DataBaseWriter;
import com.app.batch.dto.CustommerDTO;
import com.app.model.customer.Customer;

public class CustomerWriter implements ItemWriter<CustommerDTO> {
	
	
	private DataBaseWriter  dataBaseWriter;

	public CustomerWriter(DataBaseWriter dataBaseWriter) {
		super();
		this.dataBaseWriter = dataBaseWriter;
	}

	@Override
	public void write(List<? extends CustommerDTO> custommers) throws Exception {
		// TODO Auto-generated method stub
		
		
		for (CustommerDTO custommerDTO : custommers) {
			try {		
				dataBaseWriter.addCustommer(custommerDTO);
				System.out.println(" log process "+custommerDTO.getFirstName());
			}catch (Exception e){
					e.printStackTrace();
				
			}
		}
		
	}
	
}
