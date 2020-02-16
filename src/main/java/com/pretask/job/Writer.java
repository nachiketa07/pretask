package com.pretask.job;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.pretask.mail.dao.PretaskDAO;
import com.pretask.mail.vo.MailVO;

public class Writer implements ItemWriter<MailVO> {
	
	@Override
	public void write(List<? extends MailVO> messages) throws Exception {
		System.out.println("--in writer---");
		
	}

}