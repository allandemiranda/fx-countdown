package br.allandemiranda.fx.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the FX Countdown Algorithmic Trading and Expert Advisor Builder application. Bootstraps the Spring Boot WebFlux and R2DBC reactive runtime environment.
 */
@SpringBootApplication
public class FxCountdownApplication {

  /**
   * Application launch method.
   *
   * @param args command-line arguments passed to the application
   */
  static void main(String[] args) {
    SpringApplication.run(FxCountdownApplication.class, args);
  }

}
