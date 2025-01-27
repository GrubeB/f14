package pl.app.god.query;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import pl.app.energy.query.dto.EnergyDto;
import pl.app.god.query.dto.GodDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GodQueryService {
    Mono<GodDto> fetchById(@NonNull ObjectId id);
    Mono<List<GodDto>> fetchAll();
    Mono<List<GodDto>> fetchAllByIds(List<ObjectId> godIds);
    Mono<Page<GodDto>> fetchAllByPageable(Pageable pageable);
}
