package dm848.images;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The userservice Spring configuration.
 */
@Configuration
@ComponentScan
@EntityScan("dm848.images")
@EnableJpaRepositories("dm848.images")
@PropertySource("classpath:db-config.properties")
public class ImageConfiguration {

	protected Logger logger;

	public ImageConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates an in-memory "rewards" database populated with test data for fast
	 * testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo imageservice.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/images/schema.sql")
				.addScript("classpath:testdb/images/data.sql").build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> images = jdbcTemplate.queryForList("SELECT id FROM T_IMAGE");
		logger.info("System has " + images.size() + " images");

		return dataSource;
	}
}
