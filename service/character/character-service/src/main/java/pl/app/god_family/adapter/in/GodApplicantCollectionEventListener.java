package pl.app.god_family.adapter.in;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.app.god_applicant_collection.application.domain.GodApplicantCollectionEvent;
import pl.app.god_family.application.port.in.GodFamilyCommand;
import pl.app.god_family.application.port.in.GodFamilyService;

@Component
@RequiredArgsConstructor
class GodApplicantCollectionEventListener {
    private final Logger logger = LoggerFactory.getLogger(GodApplicantCollectionEventListener.class);
    private final GodFamilyService godFamilyService;

    @KafkaListener(
            id = "god-applicant-accepted-event-listener--god-family",
            groupId = "${app.kafka.consumer.group-id}--god-family",
            topics = "${app.kafka.topic.god-applicant-accepted.name}"
    )
    public void create(ConsumerRecord<ObjectId, GodApplicantCollectionEvent.GodApplicantAcceptedEvent> record) {
        logger.debug("received event {} {}-{} key: {},value: {}", record.value().getClass().getSimpleName(), record.partition(), record.offset(), record.key(), record.value());
        final var event = record.value();
        var command = new GodFamilyCommand.AddCharacterToGodFamilyCommand(
                event.getGodId(),
                event.getCharacterId()
        );
        godFamilyService.add(command).block();
    }
}