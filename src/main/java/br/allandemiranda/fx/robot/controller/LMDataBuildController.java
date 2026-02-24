package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.LMBuildRequirementsDTO;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.service.LMDataBuildService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@RestController
@RequestMapping("ml/build")
public class LMDataBuildController {

  private final LMDataBuildService lmDataBuildService;

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping(path = "/symbols/{name}/timeframes/{period}", produces = "application/json")
  public void runBuild(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period, @RequestBody @Valid LMBuildRequirementsDTO lmBuildRequirementsDTO) {

  }

}
