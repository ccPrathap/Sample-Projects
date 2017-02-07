package com.user.service;

import com.user.model.MailUser;

public interface MailUserService {

	MailUser getMailUserDetails(String mailId);
	
	MailUser createMailUser(MailUser mailUser);

}
