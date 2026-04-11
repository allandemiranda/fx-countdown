package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.AbstractInputObjectMapperTest;
import br.allandemiranda.fx.robot.model.MLInput;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MLInputMapperTest extends AbstractInputObjectMapperTest<MLInput, MLInputDto, MLInputCreateDto> {

  private final int chartObjectNum = 1;
  private final int maxDepth = 2;
  private final float eta = 3;
  private final float subsample = 4;
  private final float colSampleByTree = 5;
  private final int minChildWeight = 6;
  private final float lambda = 7;
  private final float alpha = 8;

  @InjectMocks
  @Getter
  private MLInputMapper mapper;

  @Mock
  @Getter
  private MLInput model;

  @Mock
  @Getter
  private MLInputCreateDto createDto;

  @Override
  protected void whenExtraParameters(MLInput model) {
    Mockito.when(model.chartObjectNum()).thenReturn(chartObjectNum);
    Mockito.when(model.maxDepth()).thenReturn(maxDepth);
    Mockito.when(model.eta()).thenReturn(eta);
    Mockito.when(model.subsample()).thenReturn(subsample);
    Mockito.when(model.colSampleByTree()).thenReturn(colSampleByTree);
    Mockito.when(model.minChildWeight()).thenReturn(minChildWeight);
    Mockito.when(model.lambda()).thenReturn(lambda);
    Mockito.when(model.alpha()).thenReturn(alpha);
  }

  @Override
  protected void whenExtraParameters(MLInputCreateDto createDto) {
    Mockito.when(createDto.chartObjectNum()).thenReturn(chartObjectNum);
    Mockito.when(createDto.maxDepth()).thenReturn(maxDepth);
    Mockito.when(createDto.eta()).thenReturn(eta);
    Mockito.when(createDto.subsample()).thenReturn(subsample);
    Mockito.when(createDto.colSampleByTree()).thenReturn(colSampleByTree);
    Mockito.when(createDto.minChildWeight()).thenReturn(minChildWeight);
    Mockito.when(createDto.lambda()).thenReturn(lambda);
    Mockito.when(createDto.alpha()).thenReturn(alpha);
  }

  @Override
  protected void thenExtraParameters(MLInput model, MLInputDto dto) {
    Assertions.assertEquals(model.chartObjectNum(), dto.chartObjectNum());
    Assertions.assertEquals(model.maxDepth(), dto.maxDepth());
    Assertions.assertEquals(model.eta(), dto.eta());
    Assertions.assertEquals(model.subsample(), dto.subsample());
    Assertions.assertEquals(model.colSampleByTree(), dto.colSampleByTree());
    Assertions.assertEquals(model.minChildWeight(), dto.minChildWeight());
    Assertions.assertEquals(model.lambda(), dto.lambda());
    Assertions.assertEquals(model.alpha(), dto.alpha());
  }

  @Override
  protected void thenExtraParameters(MLInputCreateDto createDto, MLInput model) {
    Assertions.assertEquals(createDto.chartObjectNum(), model.chartObjectNum());
    Assertions.assertEquals(createDto.maxDepth(), model.maxDepth());
    Assertions.assertEquals(createDto.eta(), model.eta());
    Assertions.assertEquals(createDto.subsample(), model.subsample());
    Assertions.assertEquals(createDto.colSampleByTree(), model.colSampleByTree());
    Assertions.assertEquals(createDto.minChildWeight(), model.minChildWeight());
    Assertions.assertEquals(createDto.lambda(), model.lambda());
    Assertions.assertEquals(createDto.alpha(), model.alpha());
  }
}