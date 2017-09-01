package org.uebergebuehr.graphcms.dms.services;

import javax.jcr.Session;

/**
 * Service for holding a JCR session.
 * 
 * @author aschaeffer
 *
 */
public interface JcrSessionService {

	/**
	 * Session login.
	 */
	void login();

	/**
	 * Session logout.
	 */
	void logout();

	/**
	 * Returns the JCR session.
	 * @return The JCR session.
	 */
	Session getSession();

}
