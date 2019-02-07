package com.app.batch;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TaskletImportFile implements Tasklet{
	
	
	private static String fileSeparator = System.getProperty("file.separator");
	
	private static final String fileCustommer="custommers.csv";
	private static  final String fileOrder="orders.csv";
	private static final String fileEmployee="employees.csv";
	
	private static final String APP_GRH="GRC";
	private static final String APP_APRO="APRO";
	private static final String APP_GRC="GRC";
	
	 @Value("${ftp.folder.dest}")
	 private String destFolder;
	 
	 @Value("${ftp.folder.src}")
	 private String srcFolder;
	 
	 
	
	 @Value("${applications.batch.imports}")
	 private String apps;
	 
	 

	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try{
			
			for (String app : getAppNames()) {
				if (app.equalsIgnoreCase(APP_GRH)) {
					 copyFile(getNameFile(fileEmployee,true),destFolder);
				}else if (app.equalsIgnoreCase(APP_APRO)) {
					  copyFile(getNameFile(fileOrder,true), destFolder);	
				}else if (app.equalsIgnoreCase(APP_GRC)) {
					  copyFile(getNameFile(fileCustommer,true), destFolder);
				}
			}
		  System.out.println("################### end copie file !!!");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
	}
	
	private  void copyFile(String src,String dest) 
			  throws IOException {
			    File copied = new File(src);
			    File original = new File(dest);
			       FileUtils.copyFileToDirectory(copied, original);
	}
	
	
	private String getNameFile(String fileName,Boolean type) {
		
		if (fileName!=null) {
		  String name="";
			if (type) {
				name=srcFolder+fileSeparator+fileName;
				
			}else {
				name=destFolder+fileSeparator+fileName;
			}
			
			return name;
		}
		
		return null;
	}
	
	
	private String [] getAppNames() {
		
		if (this.apps!=null) {
			
			String[] parts = this.apps.split(",");
			return parts;
		}
		return null;
	}
	
}
