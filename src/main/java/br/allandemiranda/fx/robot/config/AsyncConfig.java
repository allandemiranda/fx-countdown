package br.allandemiranda.fx.robot.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Asynchronous execution configuration using Java 21+ Virtual Threads. Provides a virtual-thread-per-task executor for high-throughput non-blocking background pipelines.
 */
@EnableAsync
@Configuration
public class AsyncConfig {

  /**
   * Creates an {@link Executor} backed by lightweight Java virtual threads.
   *
   * @return the virtual thread executor instance
   */
  @Bean
  public Executor executor() {
    return Executors.newVirtualThreadPerTaskExecutor();
  }
}
