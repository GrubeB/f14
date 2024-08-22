package pl.app.god_equipment.query;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.support.ReactiveMongoRepositoryFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.app.god_equipment.application.domain.GodEquipment;
import pl.app.god_equipment.application.domain.CharacterGear;
import pl.app.god_equipment.dto.GodEquipmentDto;
import pl.app.common.mapper.BaseMapper;
import pl.app.item.application.domain.Outfit;
import pl.app.item.application.domain.Weapon;
import pl.app.item.query.dto.OutfitDto;
import pl.app.item.query.dto.WeaponDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
class GodEquipmentQueryServiceImpl implements GodEquipmentQueryService {
    private final ReactiveMongoTemplate mongoTemplate;
    private final Mapper mapper;
    private final Repository repository;

    public GodEquipmentQueryServiceImpl(ReactiveMongoTemplate mongoTemplate, Mapper mapper) {
        this.mongoTemplate = mongoTemplate;
        this.mapper = mapper;
        this.repository = new ReactiveMongoRepositoryFactory(mongoTemplate).getRepository(Repository.class);
    }

    @Override
    public Mono<Page<GodEquipmentDto>> fetchByPageable(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(e -> mapper.map(e, GodEquipmentDto.class))
                .collectList()
                .zipWith(repository.count())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

    @Override
    public Mono<GodEquipmentDto> fetchById(ObjectId id) {
        return repository.findById(id)
                .map(e -> mapper.map(e, GodEquipmentDto.class));
    }

    @Component
    @RequiredArgsConstructor
    static class Mapper extends BaseMapper {
        private final ModelMapper modelMapper;


        @PostConstruct
        void init() {
            addMapper(GodEquipment.class, GodEquipmentDto.class, this::mapToGodEquipmentDto);
            addMapper(CharacterGear.class, GodEquipmentDto.CharacterGearDto.class, this::mapToCharacterGearDto);
        }

        GodEquipmentDto mapToGodEquipmentDto(GodEquipment domain) {
            List<OutfitDto> outfits = domain.getItems().stream()
                    .filter(d -> d instanceof Outfit)
                    .map(d -> modelMapper.map(d, OutfitDto.class))
                    .toList();
            List<WeaponDto> weapons = domain.getItems().stream()
                    .filter(d -> d instanceof Weapon)
                    .map(d -> modelMapper.map(d, WeaponDto.class))
                    .toList();
            return new GodEquipmentDto(
                    domain.getId(),
                    domain.getGodId(),
                    outfits,
                    weapons,
                    domain.getCharacterGears().stream().map(d -> map(d, GodEquipmentDto.CharacterGearDto.class)).collect(Collectors.toList())
            );
        }

        GodEquipmentDto.CharacterGearDto mapToCharacterGearDto(CharacterGear domain) {
            return new GodEquipmentDto.CharacterGearDto(
                    domain.getCharacterId(),
                    domain.getHelmet() != null ? modelMapper.map(domain.getHelmet(), OutfitDto.class) : null,
                    domain.getArmor() != null ? modelMapper.map(domain.getArmor(), OutfitDto.class) : null,
                    domain.getGloves() != null ? modelMapper.map(domain.getGloves(), OutfitDto.class) : null,
                    domain.getBoots() != null ? modelMapper.map(domain.getBoots(), OutfitDto.class) : null,
                    domain.getBelt() != null ? modelMapper.map(domain.getBelt(), OutfitDto.class) : null,
                    domain.getRing() != null ? modelMapper.map(domain.getRing(), OutfitDto.class) : null,
                    domain.getAmulet() != null ? modelMapper.map(domain.getAmulet(), OutfitDto.class) : null,
                    domain.getTalisman() != null ? modelMapper.map(domain.getTalisman(), OutfitDto.class) : null,

                    domain.getLeftHand() != null ? modelMapper.map(domain.getLeftHand(), WeaponDto.class) : null,
                    domain.getRightHand() != null ? modelMapper.map(domain.getRightHand(), WeaponDto.class) : null
            );
        }

    }

    interface Repository extends ReactiveMongoRepository<GodEquipment, ObjectId> {
        Flux<GodEquipment> findAllBy(Pageable pageable);
    }
}