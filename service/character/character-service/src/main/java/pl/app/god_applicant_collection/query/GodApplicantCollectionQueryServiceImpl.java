package pl.app.god_applicant_collection.query;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.support.ReactiveMongoRepositoryFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.app.common.mapper.BaseMapper;
import pl.app.god_applicant_collection.application.domain.GodApplicantCollection;
import pl.app.god_applicant_collection.query.dto.GodApplicantCollectionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class GodApplicantCollectionQueryServiceImpl implements GodApplicantCollectionQueryService {
    private final Mapper mapper;
    private final Repository repository;

    public GodApplicantCollectionQueryServiceImpl(ReactiveMongoTemplate mongoTemplate, Mapper mapper) {
        this.mapper = mapper;
        this.repository = new ReactiveMongoRepositoryFactory(mongoTemplate).getRepository(Repository.class);
    }

    @Override
    public Mono<GodApplicantCollectionDto> fetchByGodId(@NonNull ObjectId godId) {
        return repository.findByGodId(godId)
                .map(e -> mapper.map(e, GodApplicantCollectionDto.class));
    }

    @Override
    public Mono<Page<GodApplicantCollectionDto>> fetchAllByPageable(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(e -> mapper.map(e, GodApplicantCollectionDto.class))
                .collectList()
                .zipWith(repository.count())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }
    @Component
    @RequiredArgsConstructor
    static class Mapper extends BaseMapper {
        private final ModelMapper modelMapper;

        @PostConstruct
        void init() {
            addMapper(GodApplicantCollection.class, GodApplicantCollectionDto.class, e -> modelMapper.map(e, GodApplicantCollectionDto.class));
        }
    }

    interface Repository extends ReactiveMongoRepository<GodApplicantCollection, ObjectId> {
        @Query("{ 'godId': ?0 }")
        Mono<GodApplicantCollection> findByGodId(ObjectId id);

        Flux<GodApplicantCollection> findAllBy(Pageable pageable);
    }
}
