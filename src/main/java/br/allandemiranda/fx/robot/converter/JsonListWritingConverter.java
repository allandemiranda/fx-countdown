package br.allandemiranda.fx.robot.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@Getter(AccessLevel.PRIVATE)
@WritingConverter
@RequiredArgsConstructor
public class JsonListWritingConverter implements Converter<List<?>, Json> {

  private final ObjectMapper mapper;

  @Override
  public Json convert(@NonNull List<?> source) {
    try {
      return Json.of(this.getMapper().writeValueAsString(source));
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to serialize List to JSON", e);
    }
  }
}
