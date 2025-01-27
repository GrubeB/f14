package pl.app.monster.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import pl.app.common.shared.model.CharacterProfession;
import pl.app.common.shared.model.CharacterRace;
import pl.app.common.shared.model.Statistics;
import pl.app.gear.dto.GearDto;
import pl.app.loot.dto.LootDto;
import pl.app.monster_template.dto.ProgressDto;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonsterWithGearDto implements Serializable {
    private ObjectId id;
    private String name;
    private String description;
    private CharacterRace race;
    private CharacterProfession profession;
    private String imageId;

    private Integer level;

    private Statistics base;
    private Statistics gear;
    private Statistics statistics;

    private Long hp;
    private Long def;
    private Long attackPower;

    private GearDto monsterGear;
    private LootDto monsterLoot;
    private ProgressDto progress;
}
