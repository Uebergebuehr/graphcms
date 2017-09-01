package org.uebergebuehr.graphcms;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.uebergebuehr.graphcms.util.ColumnLogger;

/**
 * @author Andreas Schaeffer
 *
 */
@SpringBootApplication
@EnableNeo4jRepositories(basePackages = { "org.uebergebuehr.graphcms.repository" })
public class GraphCmsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(GraphCmsApplication.class);

	/**
	 * The main entry.
	 * @param args The command line arguments.
	 */
    public static void main(final String[] args) {
        SpringApplication.run(GraphCmsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return args -> {

        	LOGGER.info("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            ColumnLogger columnLogger = ColumnLogger.create()
            	.addColum(140, "Bean")
            	.header();
            for (String beanName : beanNames) {
            	columnLogger.addRow(beanName);
            }
            LOGGER.info(columnLogger.footer().toString());

        };
    }

}
