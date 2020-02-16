package com.pretask.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pretask.mail.dao.PretaskDAO;
import com.pretask.mail.vo.MailVO;

@RestController
public class PreTaskController {

	@Autowired
	PretaskDAO pretaskDAO;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJob;

	@PostMapping("/placeRequest")
	public ResponseEntity<String> email(@RequestBody MailVO payload) {
		pretaskDAO.saveJobSchedule(payload);
		return new ResponseEntity<>("Mail request placed", HttpStatus.OK);

	}
	@PostMapping("/UpdateRequest")
	public ResponseEntity<String> Updateemail(@RequestBody MailVO payload){
		pretaskDAO.updateJobSchedule(payload);
		return new ResponseEntity<String>("Mail Update Request placed" , HttpStatus.OK);
	}

	@RequestMapping("/calljob")
	public String callJOB() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("jobidd", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(processJob, jobParameters);
		return " Mail job completed";
	}

}
