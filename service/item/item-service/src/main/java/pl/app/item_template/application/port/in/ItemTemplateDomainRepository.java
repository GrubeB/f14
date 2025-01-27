package pl.app.item_template.application.port.in;

import org.bson.types.ObjectId;
import pl.app.item_template.application.domain.ItemTemplate;
import pl.app.item_template.application.domain.OutfitTemplate;
import pl.app.item_template.application.domain.WeaponTemplate;
import reactor.core.publisher.Mono;

public interface ItemTemplateDomainRepository {
    Mono<OutfitTemplate> fetchOutfitTemplateById(ObjectId id);

    Mono<WeaponTemplate> fetchWeaponTemplateById(ObjectId id);

    Mono<ItemTemplate> fetchTemplateById(ObjectId id);
}
