package pl.app.equipment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import pl.app.gear.dto.GearDto;
import pl.app.item.query.dto.OutfitDto;
import pl.app.item.query.dto.WeaponDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class EquipmentDto implements Serializable {
    private ObjectId godId;
    private Set<OutfitDto> outfits;
    private Set<WeaponDto> weapons;
    private Set<GearDto> characterGears;

    public EquipmentDto() {
        this.outfits = new HashSet<>();
        this.weapons = new HashSet<>();
        this.characterGears = new HashSet<>();
    }
}
