package com.app.batch;

import java.math.BigDecimal;
import java.util.Date;

import javax.sql.DataSource;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import com.app.batch.dto.AnimeDTO;
import com.app.batch.dto.CustommerDTO;
import com.app.batch.dto.EmployeeDTO;
import com.app.batch.dto.OrderDTO;
import com.app.batch.processor.AnimeProcessor;
import com.app.batch.processor.CustommerProcessor;
import com.app.batch.processor.EmployeeProcessor;
import com.app.batch.processor.OrderProcessor;
import com.app.batch.writer.CustomerWriter;
import com.app.batch.writer.EmployeeWriter;
import com.app.batch.writer.OrderWriter;

@EnableBatchProcessing
@Configuration
@ComponentScan(basePackages = "com.app.batch")
@EnableScheduling
public class CsvFileToDatabaseConfig {
	
	
	private static String fileSeparator = System.getProperty("file.separator");
	
	
	 @Value("${ftp.folder.dest}")
	 private String ftpFolder;
	
	 
	 @Autowired
	 private ApplicationContext appContext;
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Autowired
    public DataSource dataSource;
    
    
    @Autowired
    public DataBaseWriter dataBaseWriter;
    
    @Autowired
    public TaskletImportFile taskImportFile;
    
   
    
    // begin reader, writer, and processor

    
    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    
    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

 
    @Bean
    public ResourcelessTransactionManager resourcelessTransactionManager() {
        return new ResourcelessTransactionManager();
    }
    
    
    @Bean
    public MapJobRepositoryFactoryBean mapJobRepositoryFactory(ResourcelessTransactionManager transactionManager)
            throws Exception {
        MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(transactionManager);
        factory.afterPropertiesSet();
        return factory;
    }
    
    
    @Bean
    public JobRepository jobRepository(MapJobRepositoryFactoryBean repositoryFactory) throws Exception {
        return repositoryFactory.getObject();
    }

    
    
    @Bean
    public JobExplorer jobExplorer(MapJobRepositoryFactoryBean repositoryFactory) {
        return new SimpleJobExplorer(repositoryFactory.getJobInstanceDao(), repositoryFactory.getJobExecutionDao(),
                repositoryFactory.getStepExecutionDao(), repositoryFactory.getExecutionContextDao());
    }
    
    
    @Bean
    @StepScope
    public FlatFileItemReader<CustommerDTO> dataCustommerReader(){
        FlatFileItemReader<CustommerDTO> reader = new FlatFileItemReader<CustommerDTO>();
        
        Resource  r=appContext.getResource("file:"+ftpFolder+fileSeparator+"custommers.csv");
        reader.setResource(r);
        reader.setLineMapper(new DefaultLineMapper<CustommerDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id", "lastName", "firstName","email","company","phone","adresse1","adresse2","city","state","codePostal","country" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CustommerDTO>() {{
                setTargetType(CustommerDTO.class);
            }});
        }});
        return reader;
    }
    
    @Bean
    @StepScope
    public FlatFileItemReader<EmployeeDTO> dataEmployeeReader(){
        FlatFileItemReader<EmployeeDTO> reader = new FlatFileItemReader<EmployeeDTO>();
        Resource  r=appContext.getResource("file:"+ftpFolder+fileSeparator+"employees.csv");
        reader.setResource(r);
        reader.setLineMapper(new DefaultLineMapper<EmployeeDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id", "lastName", "firstName","email","avatar","jobTitle","departement","managerId","phone","adresse1","adresse2","city","state","codePostal","country" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<EmployeeDTO>() {{
                setTargetType(EmployeeDTO.class);
            }});
        }});
        return reader;
    }
    
    

    @Bean
    @StepScope
    public FlatFileItemReader<OrderDTO> dataOrderReader(){
        FlatFileItemReader<OrderDTO> reader = new FlatFileItemReader<OrderDTO>();
        Resource  r=appContext.getResource("file:"+ftpFolder+fileSeparator+"orders.csv");
        reader.setResource(r);
        reader.setLineMapper(new DefaultLineMapper<OrderDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "id", "employeeId", "customerId","orderDate","shippedDate","shipName","shipAddress1","shipAddress2",
                		"shipCity","shipState","shipPostalCode","shipCountry","shippingFee","paymentType","paidDate","orderStatus" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<OrderDTO>() {{
                setTargetType(OrderDTO.class);
            }});
        }});
        return reader;
    }
    

     

	@Bean
	@StepScope
	ItemProcessor<CustommerDTO, CustommerDTO> custommerProcessor() {
		return new CustommerProcessor();
	}
	
	
	
	@Bean
	@StepScope
	ItemProcessor<EmployeeDTO, EmployeeDTO> employeeProcessor() {
		return new EmployeeProcessor();
	}
	
	
	@Bean
	@StepScope
	ItemProcessor<OrderDTO, OrderDTO> orderProcessor() {
		return new OrderProcessor();
	}
	

    @Bean
    @StepScope
    public ItemWriter<CustommerDTO> custommerWriter() {
        return new CustomerWriter(dataBaseWriter);
    }
    
    @Bean
    @StepScope
    public ItemWriter<EmployeeDTO> employeeWriter() {
        return new EmployeeWriter(dataBaseWriter);
    }
    
    @Bean
    @StepScope
    public ItemWriter<OrderDTO> orderWriter() {
        return new OrderWriter(dataBaseWriter);
    }
    
    

	 // end reader, writer, and processor

    // begin job info
	@Bean
	public Step custommerToDatabaseStep() {
		return stepBuilderFactory.get("custommerToDatabaseStep")
				.<CustommerDTO, CustommerDTO>chunk(1)
				.reader(dataCustommerReader())
				.processor(custommerProcessor())
				.writer(custommerWriter())
				.build();
	}
	
	
	@Bean
	public Step employeeToDatabaseStep() {
		return stepBuilderFactory.get("employeeToDatabaseStep")
				.<EmployeeDTO, EmployeeDTO>chunk(1)
				.reader(dataEmployeeReader())
				.processor(employeeProcessor())
				.writer(employeeWriter())
				.build();
	}
	
	
     
	@Bean
	public Step orderToDatabaseStep() {
		return stepBuilderFactory.get("orderToDatabaseStep")
				.<OrderDTO, OrderDTO>chunk(1)
				.reader(dataOrderReader())
				.processor(orderProcessor())
				.writer(orderWriter())
				.build();
	}
	
	 @Bean
	public Step stepImportFile() {
	        return stepBuilderFactory.get("stepImportFile")
	        		.tasklet(taskImportFile)
	                .build();
	}

	
	
    @Scheduled(cron = "${spring.batch.delay}")
    public void perform() throws Exception {
       
        JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(importDataJob(), param);
    }
	
    @Bean
    public Job importDataJob() {
        return jobBuilderFactory.get("csvFileToDatabaseJob").incrementer(new RunIdIncrementer()).start(stepImportFile()).next(custommerToDatabaseStep())
        		.next(employeeToDatabaseStep()).next(orderToDatabaseStep()).build();
    }

	
	
	 // end job info
}
