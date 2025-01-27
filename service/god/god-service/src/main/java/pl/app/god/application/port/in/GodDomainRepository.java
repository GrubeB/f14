package pl.app.god.application.port.in;

import org.bson.types.ObjectId;
import pl.app.god.application.domain.God;
import reactor.core.publisher.Mono;

public interface GodDomainRepository {
    Mono<God> fetchById(ObjectId id);
}
