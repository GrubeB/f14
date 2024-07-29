package pl.app.comment.adapter.in;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.app.comment.application.domain.VotingEvent;
import pl.app.comment.application.port.in.AddUserVoteUseCase;
import pl.app.comment.application.port.in.CreateVotingUseCase;
import pl.app.comment.application.port.in.RemoveUserVoteUseCase;
import pl.app.comment.application.port.in.command.AddUserVoteCommand;
import pl.app.comment.application.port.in.command.CreateVotingCommand;
import pl.app.comment.application.port.in.command.RemoveUserVoteCommand;

@Component
@RequiredArgsConstructor
class VotingRequestedEventListener {
    private final Logger logger = LoggerFactory.getLogger(VotingRequestedEventListener.class);
    private final CreateVotingUseCase createVotingUseCase;
    private final AddUserVoteUseCase addUserVoteUseCase;
    private final RemoveUserVoteUseCase removeUserVoteUseCase;

    @KafkaListener(
            id = "create-voting-requested-event-listener",
            groupId = "${app.kafka.consumer.group-id}",
            topics = "${app.kafka.topic.create-voting-requested.name}"
    )
    public void createVoting(ConsumerRecord<ObjectId, Object> record) {
        logger.debug("received " + record.partition() + ":" + record.offset() + " - " + record.key() + " with value: " + record.value());
        if (record.value() instanceof VotingEvent.CreateVotingRequestedEvent event) {
            var command = new CreateVotingCommand(event.getVotingId(), event.getDomainObjectId(), event.getDomainObjectType());
            createVotingUseCase.createVoting(command);
        }
    }

    @KafkaListener(
            id = "add-vote-requested-event-listener",
            groupId = "${app.kafka.consumer.group-id}",
            topics = "${app.kafka.topic.add-vote-requested.name}"
    )
    public void addUserVote(ConsumerRecord<ObjectId, Object> record) {
        logger.debug("received " + record.partition() + ":" + record.offset() + " - " + record.key() + " with value: " + record.value());
        if (record.value() instanceof VotingEvent.AddVoteRequestedEvent event) {
            var command = new AddUserVoteCommand(
                    event.getVotingId(),
                    event.getDomainObjectId(),
                    event.getDomainObjectType(),
                    event.getUserId(),
                    event.getType()
            );
            addUserVoteUseCase.addUserVote(command);
        }
    }

    @KafkaListener(
            id = "remove-vote-requested-event-listener",
            groupId = "${app.kafka.consumer.group-id}",
            topics = "${app.kafka.topic.remove-vote-requested.name}"
    )
    public void removeUserVote(ConsumerRecord<ObjectId, Object> record) {
        logger.debug("received " + record.partition() + ":" + record.offset() + " - " + record.key() + " with value: " + record.value());
        if (record.value() instanceof VotingEvent.RemoveVoteRequestedEvent event) {
            var command = new RemoveUserVoteCommand(
                    event.getVotingId(),
                    event.getDomainObjectId(),
                    event.getDomainObjectType(),
                    event.getUserId()
            );
            removeUserVoteUseCase.removeUserVote(command);
        }
    }
}
