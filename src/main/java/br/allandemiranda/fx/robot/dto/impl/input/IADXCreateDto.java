package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputCreateDto;
import java.io.Serializable;

public record IADXCreateDto(short period) implements Serializable, InputCreateDto {

}
