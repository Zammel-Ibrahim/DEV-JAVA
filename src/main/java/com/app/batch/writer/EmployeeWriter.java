package com.app.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.app.batch.DataBaseWriter;
import com.app.batch.dto.EmployeeDTO;


public class EmployeeWriter implements ItemWriter<EmployeeDTO> {
	
	
	private DataBaseWriter  dataBaseWriter;

	public EmployeeWriter(DataBaseWriter dataBaseWriter) {
		super();
		this.dataBaseWriter = dataBaseWriter;
	}

	@Override
	public void write(List<? extends EmployeeDTO> employers) throws Exception {
		// TODO Auto-generated method stub
		
		
		for (EmployeeDTO employeeDTO : employers) {
			try {		
				dataBaseWriter.addPrUpdateEmployee(employeeDTO);
				
			}catch (Exception e){
					e.printStackTrace();
				
			}
		}
		
	}
	
}
