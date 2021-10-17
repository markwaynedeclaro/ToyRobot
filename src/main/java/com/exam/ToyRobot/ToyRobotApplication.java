package com.exam.ToyRobot;

import com.exam.ToyRobot.job.RobotJob;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * run config : mvn spring-boot:run
 * @author Mark Wayne de Claro
 * @version 1.0 10/17/2021
 */
@Log4j2
@SpringBootApplication
public class ToyRobotApplication implements CommandLineRunner {

	@Autowired
	RobotJob robotJob;

	public static void main(String[] args) {
		SpringApplication.run(ToyRobotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		robotJob.startRobotController();
	}

}
