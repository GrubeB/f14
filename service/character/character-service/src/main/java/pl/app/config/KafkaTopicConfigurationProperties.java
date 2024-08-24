package pl.app.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.kafka.topic")
@Setter
@Getter
@NoArgsConstructor
public class KafkaTopicConfigurationProperties {
    private Topic characterCreated;
    private Topic characterRemoved;
    private Topic statisticAdded;
    private Topic expAdded;
    private Topic characterLevelIncreased;

    private Topic godFamilyCreated;
    private Topic characterAddedToGodFamily;
    private Topic characterRemovedFromGodFamily;

    private Topic godApplicantCollectionCreated;
    private Topic godApplicantCreated;
    private Topic godApplicantRemoved;
    private Topic godApplicantAccepted;
    private Topic godApplicantRejected;
    @Setter
    @Getter
    public static class Topic {
        private String name;
        private Integer partitions;
        private Boolean dtlTopic;

        public Topic() {
            this.name = "NAME_NOT_CONFIGURED";
            this.partitions = 1;
            this.dtlTopic = true;
        }
    }
}
