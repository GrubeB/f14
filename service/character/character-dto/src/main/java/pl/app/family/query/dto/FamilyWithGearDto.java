package pl.app.family.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import pl.app.character.query.dto.CharacterWithGearDto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyWithGearDto implements Serializable {
    private ObjectId godId;
    private List<CharacterWithGearDto> characters;
}
