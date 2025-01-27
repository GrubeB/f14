package pl.app.trader.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import pl.app.common.shared.model.Money;

import java.io.Serializable;

public interface TraderEvent {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    class TraderCreatedEvent implements Serializable {
        private ObjectId id;
        private ObjectId godId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class TraderItemsRenewedEvent implements Serializable {
        private ObjectId id;
        private ObjectId godId;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class GodBoughtItemEvent implements Serializable {
        private ObjectId id;
        private ObjectId godId;
        private ObjectId itemId;
        private Money money;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class GodSoldItemEvent implements Serializable {
        private ObjectId id;
        private ObjectId godId;
        private ObjectId itemId;
        private Money money;
    }
}
