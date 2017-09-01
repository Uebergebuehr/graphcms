package org.uebergebuehr.graphcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.uebergebuehr.graphcms.dms.services.JcrSessionService;

/**
 * Login or logout from a session.
 * 
 * @author Andreas Schaeffer
 *
 */
@Controller
public class JcrSessionController {

	@Autowired
	private JcrSessionService jcrSessionService;

	@RequestMapping("/jcr/session/login")
	@ResponseBody
	public String login() {
		jcrSessionService.login();
		return "Login";
	}

	@RequestMapping("/jcr/session/logout")
	@ResponseBody
	public String logout() {
		jcrSessionService.logout();
		return "Logout";
	}

}
