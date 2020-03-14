package com.bigbank.dragons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConfigurationProperties("client.dragons")
public class DragonsConfig {
  
  @Bean
  public WebClient webClient(WebClient.Builder webClientBuilder) {
    return webClientBuilder.baseUrl("https://dragonsofmugloar.com/api/v2").build();
  }

}
