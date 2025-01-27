package pl.app.god.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import pl.app.common.shared.model.Money;
import pl.app.energy.query.dto.EnergyDto;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GodDto implements Serializable {
    private ObjectId id;
    private String name;
    private String description;
    private String imageId;
    private Money money;
}
