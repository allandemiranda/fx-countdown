package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.InputObjectDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.model.InputObjectModel;

public interface InputObjectMapper<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto> {

  D toDto(ChartDto chartDto, M model);

  M toModel(ChartDto chartDto, C createDto);

}
