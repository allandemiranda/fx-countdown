package br.allandemiranda.fx.robot.mapper.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;

public interface InputObjectMapper<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto> {

  D toDto(ChartDto chartDto, M model);

  M toModel(ChartDto chartDto, C createDto);

}
