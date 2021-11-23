package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing

public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private Tasklet helloTasklet;
	
	@Bean
	public Step taskletStep1() {	
		return stepBuilderFactory.get("HelloTaskletStep1")
				.tasklet(helloTasklet)
				.build();		
	}
	
	@Bean
	public Job taskletJob() throws Exception{
		return jobBuilderFactory.get("HelloWorldTaskletjob")
				.incrementer(new RunIdIncrementer())
				.start(taskletStep1())
				.build();
	}
}
