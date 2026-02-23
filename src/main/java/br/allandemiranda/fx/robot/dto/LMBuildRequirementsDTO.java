package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import lombok.Value;

@Value
public class LMBuildRequirementsDTO implements Serializable {

  @Positive
  int garchSize;

  @Positive
  int garchPrevisibility;

  @Positive
  int mlDataSize;
}
