package pl.app.item.application.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.app.item_template.application.domain.WeaponTemplate;

@Getter
@Document(collection = "items")
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
        return getQuality()
                * (getTemplate().getMinDmg() + generatedForLevel * getTemplate().getMinDmg() * getTemplate().getMinDmgPercentage() / 100_000)
                / 100_000;
    }

    public Long getMaxDmg() {
        return getQuality()
                * (getTemplate().getMaxDmg() + generatedForLevel * getTemplate().getMaxDmg() * getTemplate().getMaxDmgPercentage() / 100_000)
                / 100_000;
    }
}

