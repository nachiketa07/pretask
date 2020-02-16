package com.pretask.job;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.pretask.mail.Mailer;
import com.pretask.mail.dao.PretaskDAO;
import com.pretask.mail.vo.MailVO;

public class Processor implements ItemProcessor<MailVO, MailVO> {

	
	@Override
	public MailVO process(MailVO data) throws Exception {
			System.out.println("--in processor --");
			System.out.println("address-"+data.getAddress_TO());
			System.out.println("body--"+data.getBody());
			System.out.println("sub--"+data.getSubject());
			Mailer mailer1=new Mailer();
			mailer1.sendMail(data);
	
			return new MailVO();
	}

}
