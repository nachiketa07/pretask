package com.pretask.job;

import javax.mail.MessagingException;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.pretask.job.JobCompletionListener;
import com.pretask.job.Processor;

import com.pretask.job.Writer;
import com.pretask.mail.Mailer;
import com.pretask.mail.vo.MailVO;



@Configuration
@EnableScheduling
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
    @Autowired
    DataSource dataSource;
	
   // @Autowired
   // private SimpleJobLauncher jobLauncher;
    @Autowired
	JobLauncher jobLauncher;
    
    @Scheduled(cron = "0 0/5 14,15,16 * * ?")    
    public void sendMailJOb() throws Exception 
    {
    System.out.println(" Job Started at :");
    JobParameters param = new JobParametersBuilder().addString("JobID",
    String.valueOf(System.currentTimeMillis())).toJobParameters();
    jobLauncher.run(processJob(), param);
    System.out.println("Job finished with status :");
    }   
    
	@Bean
	ItemReader<MailVO>  dbReader(DataSource dataSource){
		JdbcCursorItemReader<MailVO> reader=new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource);
		reader.setSql("select * from pretask_job where run_date=curdate()");
		reader.setRowMapper(new BeanPropertyRowMapper<>(MailVO.class));
		return reader;
	}
		@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(orderStep1())
				.end()
				.build();
	}

	@Bean
	public Step orderStep1() {		
		return stepBuilderFactory.get("orderStep1")
				.<MailVO, MailVO> chunk(1)
				.reader(dbReader(dataSource))				
				.processor(new Processor())
				.writer(new Writer())
				.build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

}
