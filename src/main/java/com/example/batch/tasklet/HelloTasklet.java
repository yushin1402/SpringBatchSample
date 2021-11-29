package com.example.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component("HelloTasklet") //Taskletの実装コンポーネントを複数定義するために、名前をつける。
@StepScope
@Slf4j

public class HelloTasklet implements Tasklet{
	
	@Override
	public RepeatStatus execute(StepContribution contribution, 
			ChunkContext chunkContext) throws Exception {
		log.info("Hello World");
		
		//↓↓HelloTasklet2に受け渡す変数をJobExecutionContextに設定する。
		ExecutionContext jobContext = contribution.getStepExecution()
				.getJobExecution()
				.getExecutionContext();
		
		jobContext.put("jobKey", "jobValue");
		//↑↑HelloTasklet2に受け渡す変数をJobExecutionContextに設定する。			

		//↓↓HelloTasklet内でしか参照できない変数をStepExecutionContextに設定する。		
		ExecutionContext stepContext = contribution.getStepExecution()
				.getExecutionContext();
		
		stepContext.put("stepKey", "stepValue");
		//↑↑HelloTasklet内でしか参照できない変数をStepExecutionContextに設定する。		
		return RepeatStatus.FINISHED;
	}

}
