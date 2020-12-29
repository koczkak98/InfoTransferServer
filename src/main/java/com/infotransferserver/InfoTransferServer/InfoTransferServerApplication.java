package com.infotransferserver.InfoTransferServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class InfoTransferServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InfoTransferServerApplication.class, args);
	}

}
