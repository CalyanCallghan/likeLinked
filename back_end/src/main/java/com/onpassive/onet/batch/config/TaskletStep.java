package com.onpassive.onet.batch.config;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskletStep implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		try{
    		File file = new File("uploads/Attendance.csv");
    		if(file.delete()){
    			System.out.println("### TaskletStep:" + file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
		}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
	}

}