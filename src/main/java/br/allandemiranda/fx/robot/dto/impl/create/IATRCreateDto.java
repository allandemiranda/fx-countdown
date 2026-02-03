package br.allandemiranda.fx.robot.dto.impl.create;

import br.allandemiranda.fx.robot.dto.CreateInputObjectDto;
import java.io.Serializable;

public record IATRCreateDto(short period) implements Serializable, CreateInputObjectDto {

}
