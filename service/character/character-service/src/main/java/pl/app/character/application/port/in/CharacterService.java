package pl.app.character.application.port.in;

import pl.app.character.application.domain.Character;
import reactor.core.publisher.Mono;

public interface CharacterService {
    Mono<Character> createCharacter(CharacterCommand.CreateCharacterCommand command);
    Mono<Character> addStatistic(CharacterCommand.AddStatisticCommand command);
}