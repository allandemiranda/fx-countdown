package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import java.io.Serializable;

public record IADXCreateDto(short period) implements Serializable, CreateInputObjectDto {

}
