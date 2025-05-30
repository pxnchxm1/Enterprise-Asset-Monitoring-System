package com.eams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EnterpriseAssetMonitoringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseAssetMonitoringSystemApplication.class, args);
	}

}
