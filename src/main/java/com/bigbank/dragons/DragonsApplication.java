package com.bigbank.dragons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DragonsApplication implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(DragonsApplication.class);
  
  public static void main(String[] args) {
    SpringApplication.run(DragonsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    LOGGER.info("testing logger");
  }

}
