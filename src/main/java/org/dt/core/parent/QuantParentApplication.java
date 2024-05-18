package org.dt.core.parent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude={DataSourceAutoConfiguration.class})
public class QuantParentApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuantParentApplication.class, args);
	}

}
