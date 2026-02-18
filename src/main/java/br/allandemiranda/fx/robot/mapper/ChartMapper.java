package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartCreateDto;
import br.allandemiranda.fx.robot.model.Chart;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface ChartMapper {

  @Mapping(source = "scriptInfoIStochasticPriceField", target = "scriptInfo.iStochastic.priceField")
  @Mapping(source = "scriptInfoIStochasticMethod", target = "scriptInfo.iStochastic.method")
  @Mapping(source = "scriptInfoIStochasticSlowing", target = "scriptInfo.iStochastic.slowing")
  @Mapping(source = "scriptInfoIStochasticDPeriod", target = "scriptInfo.iStochastic.dPeriod")
  @Mapping(source = "scriptInfoIStochasticKPeriod", target = "scriptInfo.iStochastic.kPeriod")
  @Mapping(source = "scriptInfoIRsiApplyTo", target = "scriptInfo.iRsi.applyTo")
  @Mapping(source = "scriptInfoIRsiPeriod", target = "scriptInfo.iRsi.period")
  @Mapping(source = "scriptInfoIMacdApplyTo", target = "scriptInfo.iMacd.applyTo")
  @Mapping(source = "scriptInfoIMacdMacdSma", target = "scriptInfo.iMacd.macdSma")
  @Mapping(source = "scriptInfoIMacdSlowEma", target = "scriptInfo.iMacd.slowEma")
  @Mapping(source = "scriptInfoIMacdFastEma", target = "scriptInfo.iMacd.fastEma")
  @Mapping(source = "scriptInfoIAtrPeriod", target = "scriptInfo.iAtr.period")
  @Mapping(source = "scriptInfoIMaSlowApplyTo", target = "scriptInfo.iMaSlow.applyTo")
  @Mapping(source = "scriptInfoIMaSlowMethod", target = "scriptInfo.iMaSlow.method")
  @Mapping(source = "scriptInfoIMaSlowShift", target = "scriptInfo.iMaSlow.shift")
  @Mapping(source = "scriptInfoIMaSlowPeriod", target = "scriptInfo.iMaSlow.period")
  @Mapping(source = "scriptInfoIMaFastApplyTo", target = "scriptInfo.iMaFast.applyTo")
  @Mapping(source = "scriptInfoIMaFastMethod", target = "scriptInfo.iMaFast.method")
  @Mapping(source = "scriptInfoIMaFastShift", target = "scriptInfo.iMaFast.shift")
  @Mapping(source = "scriptInfoIMaFastPeriod", target = "scriptInfo.iMaFast.period")
  @Mapping(source = "scriptInfoIBandsApplyTo", target = "scriptInfo.iBands.applyTo")
  @Mapping(source = "scriptInfoIBandsDeviations", target = "scriptInfo.iBands.deviations")
  @Mapping(source = "scriptInfoIBandsShift", target = "scriptInfo.iBands.shift")
  @Mapping(source = "scriptInfoIBandsPeriod", target = "scriptInfo.iBands.period")
  @Mapping(source = "scriptInfoIAdxPeriod", target = "scriptInfo.iAdx.period")
  @Mapping(source = "scriptInfoEndScope", target = "scriptInfo.endScope")
  @Mapping(source = "scriptInfoStartScope", target = "scriptInfo.startScope")
  @Mapping(source = "scriptInfoUpdateTime", target = "scriptInfo.updateTime")
  @Mapping(source = "symbolName", target = "symbol.name")
  Chart toEntity(ChartCreateDto chartCreateDto);

  @InheritInverseConfiguration(name = "toEntity")
  ChartCreateDto toDto(Chart chart);

  @InheritConfiguration(name = "toEntity")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Chart partialUpdate(ChartCreateDto chartCreateDto, @MappingTarget Chart chart);
}