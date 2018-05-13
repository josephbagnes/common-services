package com.bagnesapps.email.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bagnesapps.common.model.ClientApp;
import com.bagnesapps.common.model.CustomException;
import com.bagnesapps.common.service.SecurityService;
import com.bagnesapps.email.model.Email;
import com.bagnesapps.email.service.MailService;

@RestController
@RequestMapping("/api/mail")
public class EmailRestController {

	private Logger logger = LoggerFactory.getLogger(EmailRestController.class);

	@Autowired
	private MailService mailService;
	@Autowired
	private SecurityService securityService;

	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody Email email) {
		try {
			ClientApp clientApp = this.securityService.isAllowed(email.getAppName(), email.getAppKey());
			this.mailService.sendEmail(email.getTo(), email.getCc(), email.getSubject(), email.getHtmlContent(),
					email.getReplyTo(), clientApp);
		} catch (CustomException ce) {
			logger.error(ce.getMessage());
			if (ce.isUnauthorized()) {
				return new ResponseEntity<>(ce.getMessage(), HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<>("FAILURE", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>("FAILURE", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("MESSAGE_SENT", HttpStatus.OK);
	}
}
