package org.uebergebuehr.graphcms.dms.services;

import javax.annotation.PostConstruct;
import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for a JCR session.
 * 
 * @author aschaeffer
 *
 */
@Service
public class ModeShapeSession implements JcrSessionService {

	/**
	 * The JCR session.
	 */
	private Session session;

	/**
	 * The ModeShape repository.
	 */
	@Autowired
	private ModeShapeRepository modeShapeRepository;

	/**
	 * The logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(ModeShapeSession.class);

	/**
	 * Session login.
	 */
	@PostConstruct
	public final void login() {
		try {
			if (session == null) {
				session = modeShapeRepository.getRepository().login();
				logger.info("Successfully logged into the ModeShape repository");
			}
		} catch (LoginException e) {
			logger.error(e.getMessage(), e);
		} catch (RepositoryException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Session logout.
	 */
	public final void logout() {
		session.logout();
		session = null;
		logger.info("Successfully logged out from the ModeShape repository");
	}

	@Override
	public final Session getSession() {
		return session;
	}

}
