package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.advice.CodeResponseHandler;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.service.SymbolService;
import java.math.BigDecimal;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(controllers = SymbolController.class)
class SymbolControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockitoBean
  private SymbolService symbolService;

  @Nested
  class FindAll {

    @Test
    void returnFluxDto_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";
      BigDecimal point = BigDecimal.ONE;
      BigDecimal swapLong = BigDecimal.ZERO;
      BigDecimal swapShort = BigDecimal.ZERO;
      Flux<SymbolDto> data = Flux.just(symbolDto);

      //when
      Mockito.when(symbolDto.name()).thenReturn(symbolName);
      Mockito.when(symbolDto.point()).thenReturn(point);
      Mockito.when(symbolDto.swapLong()).thenReturn(swapLong);
      Mockito.when(symbolDto.swapShort()).thenReturn(swapShort);

      Mockito.when(symbolService.get()).thenReturn(data);

      webTestClient
          .get()
          .uri("/symbols")
          .exchange()

          //then
          .expectStatus().isOk()
          .expectBodyList(SymbolDto.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals(1, response.size());

            SymbolDto responseDto = response.getFirst();
            Assertions.assertNotNull(responseDto);
            Assertions.assertEquals(symbolName, responseDto.name());
            Assertions.assertEquals(point, responseDto.point());
            Assertions.assertEquals(swapLong, responseDto.swapLong());
            Assertions.assertEquals(swapShort, responseDto.swapShort());
          });
    }

    @Test
    void empty_returnEmptyFluxDto_test() {
      //given
      Flux<SymbolDto> data = Flux.empty();

      //when
      Mockito.when(symbolService.get()).thenReturn(data);

      webTestClient
          .get()
          .uri("/symbols")
          .exchange()

          //then
          .expectStatus().isOk()
          .expectBodyList(SymbolDto.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals(0, response.size());
          });
    }
  }

  @Nested
  class Find {

    @Test
    void byName_exist_returnDto_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";
      BigDecimal point = BigDecimal.ONE;
      BigDecimal swapLong = BigDecimal.ZERO;
      BigDecimal swapShort = BigDecimal.ZERO;
      Mono<SymbolDto> data = Mono.just(symbolDto);

      //when
      Mockito.when(symbolDto.name()).thenReturn(symbolName);
      Mockito.when(symbolDto.point()).thenReturn(point);
      Mockito.when(symbolDto.swapLong()).thenReturn(swapLong);
      Mockito.when(symbolDto.swapShort()).thenReturn(swapShort);

      Mockito.when(symbolService.get(symbolName)).thenReturn(data);

      webTestClient
          .get()
          .uri("/symbols/{symbolName}", symbolName)
          .exchange()

          //then
          .expectStatus().isOk()
          .expectBody(SymbolDto.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals(symbolName, response.name());
            Assertions.assertEquals(point, response.point());
            Assertions.assertEquals(swapLong, response.swapLong());
            Assertions.assertEquals(swapShort, response.swapShort());
          });
    }

    @Test
    void byName_notExist_returnError_test() {
      //given
      String symbolName = "EURUSD";

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.empty());

      webTestClient
          .get()
          .uri("/symbols/{symbolName}", symbolName)
          .exchange()

          //then
          .expectStatus().isNotFound()
          .expectBody(CodeResponseHandler.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals("SymbolNotFoundException", response.type());
            Assertions.assertEquals("Symbol not found: [" + symbolName + "]", response.message());
          });
    }
  }

  @Nested
  class Create {

    @Test
    void withValidDto_returnCreated_test() {
      //given
      String symbolName = "EURUSD";
      BigDecimal point = BigDecimal.ONE;
      BigDecimal swapLong = BigDecimal.ZERO;
      BigDecimal swapShort = BigDecimal.ZERO;

      SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      Mono<SymbolDto> data = Mono.just(symbolDto);

      //when
      Mockito.when(symbolService.create(symbolCreateDto)).thenReturn(data);
      Mockito.when(symbolDto.name()).thenReturn(symbolName);

      webTestClient
          .post()
          .uri("/symbols")
          .bodyValue(symbolCreateDto)
          .exchange()

          //then
          .expectStatus().isCreated()
          .expectBody(SymbolDto.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals(symbolName, response.name());
          });
    }

    @Test
    void withValidDto_internalError_returnCreated_test() {
      //given
      String symbolName = "EURUSD";
      BigDecimal point = BigDecimal.ONE;
      BigDecimal swapLong = BigDecimal.ZERO;
      BigDecimal swapShort = BigDecimal.ZERO;
      SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

      //when
      Mockito.when(symbolService.create(symbolCreateDto)).thenReturn(Mono.empty());

      webTestClient
          .post()
          .uri("/symbols")
          .bodyValue(symbolCreateDto)
          .exchange()

          //then
          .expectStatus().is5xxServerError();
    }
  }

  @Nested
  class Delete {

    @Test
    void byName_exist_returnVoid_test() {
      //given
      SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
      String symbolName = "EURUSD";
      Mono<SymbolDto> data = Mono.just(symbolDto);

      //when
      Mockito.when(symbolDto.name()).thenReturn(symbolName);
      Mockito.when(symbolService.get(symbolName)).thenReturn(data);
      Mockito.when(symbolService.delete(symbolDto.name())).thenReturn(Mono.empty());

      webTestClient
          .delete()
          .uri("/symbols/{symbolName}", symbolName)
          .exchange()

          //then
          .expectStatus().isNoContent();
    }

    @Test
    void byName_notExist_returnError_test() {
      //given
      String symbolName = "EURUSD";

      //when
      Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.empty());

      webTestClient
          .delete()
          .uri("/symbols/{symbolName}", symbolName)
          .exchange()

          //then
          .expectStatus().isNotFound()
          .expectBody(CodeResponseHandler.class)
          .value(response -> {
            Assertions.assertNotNull(response);
            Assertions.assertEquals("SymbolNotFoundException", response.type());
            Assertions.assertEquals("Symbol not found: [" + symbolName + "]", response.message());
          });
    }
  }

  @Nested
  class InputValidation {

    @Nested
    class SymbolName {

      @Nested
      class Find {

        @Test
        void byName_valid_test() {
          //given
          String symbolName = RandomStringUtils.insecure().nextAlphabetic(6).toUpperCase();
          SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

          //when
          Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));
          webTestClient
              .get()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isOk();
        }

        @Test
        void byName_sizeSmall_returnError_test() {
          //given
          String symbolName = RandomStringUtils.insecure().nextAlphabetic(1, 5).toUpperCase();

          //when
          webTestClient
              .get()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isBadRequest()
              .expectBody(ProblemDetail.class)
              .value(response -> {
                Assertions.assertNotNull(response);
                Assertions.assertNotNull(response.getProperties());
                Assertions.assertEquals("Constraint violation", response.getProperties().get("error"));
              });
        }

        @Test
        void byName_sizeBig_returnError_test() {
          //given
          String symbolName = RandomStringUtils.insecure().nextAlphabetic(7, 10).toUpperCase();

          //when
          webTestClient
              .get()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isBadRequest()
              .expectBody(ProblemDetail.class)
              .value(response -> {
                Assertions.assertNotNull(response);
                Assertions.assertNotNull(response.getProperties());
                Assertions.assertEquals("Constraint violation", response.getProperties().get("error"));
              });
        }

        @Test
        void byName_lowerCase_returnError_test() {
          //given
          String symbolName = RandomStringUtils.insecure().nextAlphabetic(6).toLowerCase();

          //when
          webTestClient
              .get()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isBadRequest()
              .expectBody(ProblemDetail.class)
              .value(response -> {
                Assertions.assertNotNull(response);
                Assertions.assertNotNull(response.getProperties());
                Assertions.assertEquals("Constraint violation", response.getProperties().get("error"));
              });
        }

        @Test
        void byName_withNumbers_returnError_test() {
          //given
          String symbolName = "AB3DE6";

          //when
          webTestClient
              .get()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isBadRequest()
              .expectBody(ProblemDetail.class)
              .value(response -> {
                Assertions.assertNotNull(response);
                Assertions.assertNotNull(response.getProperties());
                Assertions.assertEquals("Constraint violation", response.getProperties().get("error"));
              });
        }
      }

      @Nested
      class Delete {

        @Test
        void byName_valid_test() {
          //given
          String symbolName = RandomStringUtils.insecure().nextAlphabetic(6).toUpperCase();
          SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

          //when
          Mockito.when(symbolService.get(symbolName)).thenReturn(Mono.just(symbolDto));
          Mockito.when(symbolService.delete(symbolDto.name())).thenReturn(Mono.empty());
          webTestClient
              .delete()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isNoContent();
        }

        @Test
        void byName_sizeSmall_returnError_test() {
          //given
          String symbolName = RandomStringUtils.insecure().nextAlphabetic(1, 5).toUpperCase();

          //when
          webTestClient
              .delete()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isBadRequest()
              .expectBody(ProblemDetail.class)
              .value(response -> {
                Assertions.assertNotNull(response);
                Assertions.assertNotNull(response.getProperties());
                Assertions.assertEquals("Constraint violation", response.getProperties().get("error"));
              });
        }

        @Test
        void byName_sizeBig_returnError_test() {
          //given
          String symbolName = RandomStringUtils.insecure().nextAlphabetic(7, 10).toUpperCase();

          //when
          webTestClient
              .delete()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isBadRequest()
              .expectBody(ProblemDetail.class)
              .value(response -> {
                Assertions.assertNotNull(response);
                Assertions.assertNotNull(response.getProperties());
                Assertions.assertEquals("Constraint violation", response.getProperties().get("error"));
              });
        }

        @Test
        void byName_lowerCase_returnError_test() {
          //given
          String symbolName = RandomStringUtils.insecure().nextAlphabetic(6).toLowerCase();

          //when
          webTestClient
              .delete()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isBadRequest()
              .expectBody(ProblemDetail.class)
              .value(response -> {
                Assertions.assertNotNull(response);
                Assertions.assertNotNull(response.getProperties());
                Assertions.assertEquals("Constraint violation", response.getProperties().get("error"));
              });
        }

        @Test
        void byName_withNumbers_returnError_test() {
          //given
          String symbolName = "AB3DE6";

          //when
          webTestClient
              .delete()
              .uri("/symbols/{symbolName}", symbolName)
              .exchange()

              //then
              .expectStatus().isBadRequest()
              .expectBody(ProblemDetail.class)
              .value(response -> {
                Assertions.assertNotNull(response);
                Assertions.assertNotNull(response.getProperties());
                Assertions.assertEquals("Constraint violation", response.getProperties().get("error"));
              });
        }
      }
    }

    @Nested
    class SymbolCreate {

      @Test
      void nameAndPointAndSwapLongAndSwapShort_valid_test() {
        //given
        SymbolDto symbolDto = Mockito.mock(SymbolDto.class);

        String symbolName = "EURUSD";
        BigDecimal point = BigDecimal.ONE;
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(symbolCreateDto)).thenReturn(Mono.just(symbolDto));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isCreated();
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_nameSizeSmall_returnError_test() {
        //given
        String symbolName = RandomStringUtils.insecure().nextAlphabetic(1, 5).toUpperCase();
        BigDecimal point = BigDecimal.ONE;
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_nameSizeBig_returnError_test() {
        //given
        String symbolName = RandomStringUtils.insecure().nextAlphabetic(7, 10).toUpperCase();
        BigDecimal point = BigDecimal.ONE;
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_nameSowerCase_returnError_test() {
        //given
        String symbolName = RandomStringUtils.insecure().nextAlphabetic(6).toLowerCase();
        BigDecimal point = BigDecimal.ONE;
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_nameWithNumbers_returnError_test() {
        //given
        String symbolName = "AB3DE6";
        BigDecimal point = BigDecimal.ONE;
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_nameNull_returnError_test() {
        //given
        String symbolName = null;
        BigDecimal point = BigDecimal.ONE;
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_pointNegative_returnError_test() {
        //given
        String symbolName = "EURUSD";
        BigDecimal point = BigDecimal.valueOf(Math.random() * (-1));
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_pointZero_returnError_test() {
        //given
        String symbolName = "EURUSD";
        BigDecimal point = BigDecimal.ZERO;
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_pointNull_returnError_test() {
        //given
        String symbolName = "EURUSD";
        BigDecimal point = null;
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_swapLongNull_returnError_test() {
        //given
        String symbolName = "EURUSD";
        BigDecimal point = BigDecimal.ONE;
        BigDecimal swapLong = null;
        BigDecimal swapShort = BigDecimal.ZERO;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }

      @Test
      void nameAndPointAndSwapLongAndSwapShort_swapShortNull_returnError_test() {
        //given
        String symbolName = "EURUSD";
        BigDecimal point = BigDecimal.ONE;
        BigDecimal swapLong = BigDecimal.ZERO;
        BigDecimal swapShort = null;
        SymbolCreateDto symbolCreateDto = new SymbolCreateDto(symbolName, point, swapLong, swapShort);

        //when
        Mockito.when(symbolService.create(Mockito.any())).thenReturn(Mono.just(Mockito.mock(SymbolDto.class)));

        webTestClient
            .post()
            .uri("/symbols")
            .bodyValue(symbolCreateDto)
            .exchange()

            //then
            .expectStatus().isBadRequest()
            .expectBody(ProblemDetail.class)
            .value(response -> {
              Assertions.assertNotNull(response);
              Assertions.assertEquals("Invalid request content.", response.getDetail());
              Assertions.assertEquals(400, response.getStatus());
              Assertions.assertEquals("Bad Request", response.getTitle());
            });
      }
    }
  }
}