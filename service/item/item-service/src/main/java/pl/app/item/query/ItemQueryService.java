package pl.app.item.query;

import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import pl.app.item.application.domain.Item;
import pl.app.common.shared.model.ItemType;
import reactor.core.publisher.Mono;

public interface ItemQueryService {
    Mono<Item> fetchById(@NonNull ObjectId id);
}
