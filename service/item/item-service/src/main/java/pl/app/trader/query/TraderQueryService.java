package pl.app.trader.query;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import pl.app.god_equipment.dto.GodEquipmentDto;
import pl.app.trader.dto.TraderDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TraderQueryService {
    Mono<TraderDto> fetchByGodId(@NonNull ObjectId godId);

    Mono<Page<TraderDto>> fetchAllByPageable(Pageable pageable);
}
