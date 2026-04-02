package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import java.io.Serializable;

public record IATRCreateDto(short period) implements Serializable, CreateInputObjectDto {

}
