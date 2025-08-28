package co.com.crediya.api.errorhandling;

import co.com.crediya.utils.ExceptionResponse;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@Order(-2)
public class GlobalErrorHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorHandler(ErrorAttributes errorAttributes,
                              ApplicationContext applicationContext,
                              ServerCodecConfigurer configurer) {
        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        super.setMessageWriters(configurer.getWriters());
        super.setMessageReaders(configurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        ExceptionResponse response = ExceptionResponse.builder()
                .message((String) errorPropertiesMap.get("message"))
                .uri(request.path())
                .code((Integer) errorPropertiesMap.get("status"))
                .details((String) errorPropertiesMap.get("error"))
                .timestamp(LocalDateTime.now())
                .build();

        return ServerResponse.status((Integer) errorPropertiesMap.get("status"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response);
    }
}
