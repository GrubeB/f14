package pl.app.item.http;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import pl.app.common.shared.config.ResponsePage;
import pl.app.item_template.query.dto.OutfitTemplateDto;
import reactor.core.publisher.Mono;

@HttpExchange(
        url = "outfit-templates",
        accept = MediaType.APPLICATION_JSON_VALUE,
        contentType = MediaType.APPLICATION_JSON_VALUE
)
public interface OutfitTemplateQueryControllerHttpInterface {
    @GetExchange("/{id}")
    Mono<ResponseEntity<OutfitTemplateDto>> fetchById(@PathVariable ObjectId id);

    @GetExchange
    Mono<ResponseEntity<ResponsePage<OutfitTemplateDto>>> fetchAllByPageable();

}
