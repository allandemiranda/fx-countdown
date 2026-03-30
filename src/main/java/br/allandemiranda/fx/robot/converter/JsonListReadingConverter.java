package br.allandemiranda.fx.robot.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@Getter(AccessLevel.PRIVATE)
@ReadingConverter
@RequiredArgsConstructor
public class JsonListReadingConverter<T> implements Converter<Json, List<T>> {

  private final ObjectMapper mapper;
  private final Class<T> elementClass;

  @Override
  public List<T> convert(@NonNull Json source) {
    try {
      return this.getMapper().readValue(source.asString(), this.getMapper().getTypeFactory().constructCollectionType(List.class, this.getElementClass()));
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Failed to deserialize JSON to List", e);
    }
  }
}
