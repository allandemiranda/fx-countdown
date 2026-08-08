package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDashboardCreateDto;
import java.io.Serializable;

public record IATRCreateDto(short period) implements Serializable, InputDashboardCreateDto {

}
