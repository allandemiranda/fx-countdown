package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.CreateChartObjectDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.model.ChartObjectModel;
import java.util.UUID;

public interface ChartObjectMapper<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> {

  D toDto(ChartDto chartDto, M model);

  M toModel(UUID id, ChartDto chartDto, C createDto);

}
