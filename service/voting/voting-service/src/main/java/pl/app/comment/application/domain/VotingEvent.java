package pl.app.comment.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.io.Serializable;

public interface VotingEvent {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class CreateVotingRequestedEvent implements Serializable {
        private ObjectId votingId;
        private String domainObjectType;
        private String domainObjectId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class VotingCreatedEvent implements Serializable {
        private ObjectId votingId;
        private String domainObjectType;
        private String domainObjectId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class AddVoteRequestedEvent implements Serializable {
        private ObjectId votingId;
        private String domainObjectType;
        private String domainObjectId;
        private String userId;
        private String type;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class VoteAddedEvent implements Serializable {
        private ObjectId votingId;
        private String domainObjectType;
        private String domainObjectId;
        private String userId;
        private String type;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class RemoveVoteRequestedEvent implements Serializable {
        private ObjectId votingId;
        private String domainObjectType;
        private String domainObjectId;
        private String userId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    class VoteRemovedEvent implements Serializable {
        private ObjectId votingId;
        private String domainObjectType;
        private String domainObjectId;
        private String userId;
        private String type;
    }
}
