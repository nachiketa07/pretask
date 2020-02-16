package com.pretask.mail.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pretask.mail.vo.MailVO;

@Repository
public class PreTaskDAOImpl implements PretaskDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int saveJobSchedule(MailVO mailVO) {
		System.out.println("--In DAO--");
				
		return jdbcTemplate.update(
                "insert into pretask_job (request_date, run_date,address_to,subject,body) values(curdate(),?,?,?,?)",
                 mailVO.getRunDate(),mailVO.getAddress_TO() ,mailVO.getSubject(),mailVO.getBody());
               
	}
    @Override
	public int updateJobSchedule(MailVO mailVO){
    	
    	return jdbcTemplate.update(
    			" update pretask_job set run_date = ? where request_date = ? ",
    			 mailVO.getRunDate() , mailVO.getRequestDate() );
    			
    			
    }
	
}
