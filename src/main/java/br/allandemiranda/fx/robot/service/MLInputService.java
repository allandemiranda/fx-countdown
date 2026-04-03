package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.mapper.MLInputMapper;
import br.allandemiranda.fx.robot.model.MLInput;
import br.allandemiranda.fx.robot.repository.MLInputRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
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
