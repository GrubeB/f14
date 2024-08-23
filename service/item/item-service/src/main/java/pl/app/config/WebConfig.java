package pl.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;
import org.springframework.web.server.ServerWebExchange;
import pl.app.common.shared.config.JacksonConfig;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
@Import(JacksonConfig.class)
public class WebConfig implements WebFluxConfigurer {
    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        WebFluxConfigurer.super.configureArgumentResolvers(configurer);
        configurer.addCustomResolver(new PageableHandlerMethodArgumentResolver());
    }

    public class PageableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

        private static final String DEFAULT_PAGE = "0";
        private static final String DEFAULT_SIZE = "50";
        private static final Integer MAX_SIZE = 50;

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return Pageable.class.equals(parameter.getParameterType());
        }

        @Override
        public Mono<Object> resolveArgument(MethodParameter methodParameter, BindingContext bindingContext, ServerWebExchange serverWebExchange) {
            List<String> pageValues = serverWebExchange.getRequest().getQueryParams().getOrDefault("page", List.of(DEFAULT_PAGE));
            List<String> sizeValues = serverWebExchange.getRequest().getQueryParams().getOrDefault("size", List.of(DEFAULT_SIZE));

            String page = pageValues.get(0);

            String sortParam = serverWebExchange.getRequest().getQueryParams().getFirst("sort");
            Sort sort = Sort.unsorted();

            if (sortParam != null) {
                String[] parts = sortParam.split(",");
                if (parts.length == 2) {
                    String property = parts[0];
                    Sort.Direction direction = Sort.Direction.fromString(parts[1]);
                    sort = Sort.by(direction, property);
                }
            }

            return Mono.just(
                    PageRequest.of(
                            Integer.parseInt(page),
                            Math.min(Integer.parseInt(sizeValues.get(0)),
                                    MAX_SIZE), sort
                    )
            );
        }
    }
}

