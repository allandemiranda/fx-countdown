package br.allandemiranda.fx.robot.controller.util;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.exception.impl.DashboardNotFoundException;
import br.allandemiranda.fx.robot.service.DashboardService;
import java.util.UUID;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@UtilityClass
public class DashboardUtils {

  private static Logger log() {
    return LoggerFactory.getLogger(DashboardUtils.class);
  }

  public static Mono<DashboardDto> getDashboard(UUID id, DashboardService dashboardService) {
    DashboardUtils.log().trace("getDashboard(id={})", id);
    return dashboardService.get(id).switchIfEmpty(Mono.error(() -> new DashboardNotFoundException(id)));
  }
}
