package com.app.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.app.batch.dto.AnimeDTO;
import com.app.batch.dto.CustommerDTO;




public class CustommerProcessor implements ItemProcessor<CustommerDTO, CustommerDTO> {
	
    private static final Logger log = LoggerFactory.getLogger(CustommerProcessor.class);
    
    @Override
    public CustommerDTO process(final CustommerDTO custommerDTO) throws Exception {
        return custommerDTO;
    }

}