package pl.app.item.application.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.app.item_template.application.domain.WeaponTemplate;

@Document(collection = "weapons")
@Getter
public class Weapon extends Outfit {
    @SuppressWarnings("unused")
    public Weapon() {
        super();
    }

    public Weapon(WeaponTemplate template, Integer generatedForLevel) {
        super(template, generatedForLevel);
    }

    /* GETTERS */
    public WeaponTemplate getTemplate() {
        return (WeaponTemplate) super.getTemplate();
    }

    public Long getMinDmg() {
        return getTemplate().getMinDmg() + generatedForLevel * getTemplate().getMinDmg() * getTemplate().getMinDmgPercentage()/100_000;
    }

    public Long getMaxDmg() {
        return getTemplate().getMaxDmg() + generatedForLevel * getTemplate().getMaxDmg() * getTemplate().getMaxDmgPercentage()/100_000;
    }
}
