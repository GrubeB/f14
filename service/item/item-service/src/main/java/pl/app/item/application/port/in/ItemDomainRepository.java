package pl.app.item.application.port.in;

import org.bson.types.ObjectId;
import pl.app.item.application.domain.Item;
import reactor.core.publisher.Mono;

public interface ItemDomainRepository {
    Mono<Item> fetchById(ObjectId id);
}
