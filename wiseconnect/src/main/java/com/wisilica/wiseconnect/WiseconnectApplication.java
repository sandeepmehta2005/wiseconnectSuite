package com.wisilica.wiseconnect;
/**
 *  WiseConnect Security Configuration
 *
 *  @Modified By Bhaskaran
 *  @Date 25-Mar-2019
 *
 * Description : Security implementation
 *
 * */
import org.apache.ignite.Ignition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(enableDefaultTransactions = false)
@EnableTransactionManagement
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
public class WiseconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiseconnectApplication.class, args);
		Ignition.start(System.getProperty("user.dir")+"/wiseconnect/config/ignite-default-config.xml");
		//and@1234
	}
}

