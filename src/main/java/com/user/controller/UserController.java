package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.user.model.MailUser;
import com.user.service.MailUserService;
import com.user.verifier.IdTokenVerifierAndParser;

@Controller
public class UserController {
	
	@Autowired
	private MailUserService mailUserService;
	
	public static final int INITIAL_BAL = 500;
	
	@RequestMapping(value = "/cts/user/login", method = RequestMethod.GET)
	public ModelAndView login() {
		
		System.out.println("Start login");
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/cts/user/login", method = RequestMethod.POST)
	public ModelAndView getUserDetails(String idToken) {

		System.out.println("Start getUserDetails");

		String email = null, name = null;
		ModelAndView mv = null;
		MailUser mailUser = null;

		try {

			GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);

			// Get profile information from payload
			email = payLoad.getEmail();
			name = (String) payLoad.get("name");

			mailUser = mailUserService.getMailUserDetails(email);

			if (null != mailUser) {
				
				mv = new ModelAndView("home");
				mv.addObject("mailId", mailUser.getMailId());
				mv.addObject("name", mailUser.getName());
				mv.addObject("bal", mailUser.getBalance());

				return mv;

			} else {

				MailUser mailUserCreate = new MailUser();
				mailUserCreate.setMailId(email);
				mailUserCreate.setName(name);
				mailUserCreate.setBalance(INITIAL_BAL);

				MailUser mailUserNew = mailUserService.createMailUser(mailUserCreate);

				mv = new ModelAndView("home");
				mv.addObject("empId", mailUserNew.getMailId());
				mv.addObject("name", mailUserNew.getName());
				mv.addObject("bal", mailUserNew.getBalance());

				return mv;
			}
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("Fatal Error in Google Sign-in");
		return new ModelAndView("login");

	}

}
