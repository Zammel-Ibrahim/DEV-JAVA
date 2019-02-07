package com.app.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.app.batch.dto.CustommerDTO;
import com.app.batch.dto.OrderDTO;

public class OrderProcessor  implements ItemProcessor<OrderDTO, OrderDTO> {

    private static final Logger log = LoggerFactory.getLogger(OrderProcessor.class);
    
    @Override
    public OrderDTO process(final OrderDTO orderDTO) throws Exception {
        return orderDTO;
    }
	
	
}




