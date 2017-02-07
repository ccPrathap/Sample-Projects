package com.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.dao.DBTemplate;
import com.user.model.MailUser;
import com.user.service.MailUserService;

@Service
@Transactional
public class MailUserServiceImpl implements MailUserService {

	@Autowired
	private DBTemplate dBTemplate;
	
	public MailUser getMailUserDetails(String mailId) {
		
		MailUser mailUser = null;
		
		try {
			mailUser = dBTemplate.getEntity(MailUser.class, mailId);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return mailUser;
	}
	
	public MailUser createMailUser(MailUser mailUserCreate) {
		
		MailUser mailUser = null;
		
		try {
			dBTemplate.saveEntity(mailUserCreate);
			mailUser = dBTemplate.getEntity(MailUser.class, mailUserCreate.getMailId());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return mailUser;
	}

}
