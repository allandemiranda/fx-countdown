package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.impl.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.MLInputMapper;
import br.allandemiranda.fx.robot.model.impl.MLInput;
import br.allandemiranda.fx.robot.repository.impl.MLInputRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class MLInputService implements InputObjectService<MLInput, MLInputDto, MLInputCreateDto> {

  private final MLInputRepository repository;

  private final MLInputMapper mapper;

}
