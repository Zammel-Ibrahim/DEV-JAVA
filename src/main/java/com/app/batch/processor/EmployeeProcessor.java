package com.app.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.app.batch.dto.CustommerDTO;
import com.app.batch.dto.EmployeeDTO;




public class EmployeeProcessor implements ItemProcessor<EmployeeDTO, EmployeeDTO> {
	
    private static final Logger log = LoggerFactory.getLogger(EmployeeProcessor.class);
    
    @Override
    public EmployeeDTO process(final EmployeeDTO employeeDTO) throws Exception {
        return employeeDTO;
    }

}