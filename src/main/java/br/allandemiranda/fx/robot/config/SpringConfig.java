package br.allandemiranda.fx.robot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * General Spring application configuration for shared beans such as the JSON object mapper.
 */
@Configuration
public class SpringConfig {

  /**
   * Provides a singleton Jackson {@link ObjectMapper} for JSON serialization and deserialization.
   *
   * @return a new {@link ObjectMapper} instance
   */
  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
