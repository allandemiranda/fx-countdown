package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IATRCreateDto(short period) implements Serializable {

}
