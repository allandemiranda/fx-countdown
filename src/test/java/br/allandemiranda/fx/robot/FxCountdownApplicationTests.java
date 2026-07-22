package br.allandemiranda.fx.robot;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class FxCountdownApplicationTests {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

  @Test
  void contextLoads() {
  }

  @Test
  void mainMethodRuns() {
    try (MockedStatic<SpringApplication> springApplication = Mockito.mockStatic(SpringApplication.class)) {
      FxCountdownApplication.main(new String[]{});
      springApplication.verify(() -> SpringApplication.run(FxCountdownApplication.class, new String[]{}));
    }
  }
}
