package pl.app.character.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import pl.app.character_status.application.domain.CharacterStatusType;
import pl.app.character_status.query.dto.CharacterStatusDto;
import pl.app.common.shared.model.CharacterProfession;
import pl.app.common.shared.model.CharacterRace;
import pl.app.common.shared.model.Statistics;
import pl.app.gear.dto.GearDto;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterWithGearDto implements Serializable {
    private ObjectId id;
    private String name;
    private CharacterProfession profession;
    private CharacterRace race;
    private String imageId;

    private LevelDto level;

    private Statistics base;
    private Statistics gear;
    private Statistics statistics;

    private Long hp;
    private Long def;
    private Long attackPower;

    private GearDto characterGear;
    private CharacterStatusType type;
}
