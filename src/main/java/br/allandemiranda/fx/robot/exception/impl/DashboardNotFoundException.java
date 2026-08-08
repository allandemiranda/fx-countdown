package br.allandemiranda.fx.robot.exception.impl;

import br.allandemiranda.fx.robot.exception.NotFoundException;
import java.util.UUID;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class DashboardNotFoundException extends NotFoundException {

  public DashboardNotFoundException(UUID id) {
    super("Dashboard not found: [" + id + "]");
  }
}
