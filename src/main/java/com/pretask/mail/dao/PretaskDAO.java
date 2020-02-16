package com.pretask.mail.dao;


import com.pretask.mail.vo.MailVO;

public interface PretaskDAO {
	
	int saveJobSchedule(MailVO mailVO);
	int updateJobSchedule(MailVO mailVO);
}
