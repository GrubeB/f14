package pl.app.character.application.domain;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "characters")
@Getter
public class Character {
    @Id
    private ObjectId id;
    private String name;
    private CharacterProfession profession;
    private CharacterStatistics statistics;
    private CharacterMoney money;

    @SuppressWarnings("unused")
    public Character() {
    }

    public Character(String name, CharacterProfession profession) {
        this.id = ObjectId.get();
        this.name = name;
        this.profession = profession;
        this.statistics = new CharacterStatistics();
        this.money = new CharacterMoney(10_000L);
    }
}