package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.StochasticDto;
import br.allandemiranda.fx.robot.model.Stochastic;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface StochasticMapper {

  Stochastic toEntity(StochasticDto stochasticDto);

  StochasticDto toDto(Stochastic stochastic);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Stochastic partialUpdate(StochasticDto stochasticDto, @MappingTarget Stochastic stochastic);
}