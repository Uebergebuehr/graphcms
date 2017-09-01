package org.uebergebuehr.graphcms.dms.services;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jcr.Repository;

import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.RepositoryConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ModeShapeRepository {

	/**
	 * The logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(ModeShapeRepository.class);

	private static final ModeShapeEngine ENGINE = new ModeShapeEngine();
	
	private Repository repository;

	@PostConstruct
	public void start() throws Exception {
		try {
			if (repository == null) {
				ENGINE.start();
				RepositoryConfiguration repositoryConfiguration = RepositoryConfiguration.read("repository.json");
				repository = ENGINE.deploy(repositoryConfiguration);
				ENGINE.startRepository(repositoryConfiguration.getName());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@PreDestroy
	public void stop() throws Exception {
		try {
			ENGINE.shutdown().get(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public final Repository getRepository() {
		if (repository == null) {
			try {
				start();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return repository;
	}

	public final void setRepository(final Repository repository) {
		this.repository = repository;
	}

}
