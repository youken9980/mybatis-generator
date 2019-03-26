package com.company.project.mybatisgenerator;

import com.company.project.mybatisgenerator.plugins.MyBatisGeneratorRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		generator();
	}

	private static void generator() {
		try {
			MyBatisGeneratorRun app = new MyBatisGeneratorRun();
			app.generator();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
