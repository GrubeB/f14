package pl.app.comment.adapter.in;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.app.comment.application.port.in.AddUserVoteRequestUseCase;
import pl.app.comment.application.port.in.CreateVotingRequestUseCase;
import pl.app.comment.application.port.in.RemoveUserVoteRequestUseCase;
import pl.app.comment.application.port.in.command.CreateVotingRequestCommand;

@RestController
@RequestMapping(VotingRestController.resourcePath)
@RequiredArgsConstructor
class VotingRestController {
    public static final String resourceName = "votings";
    public static final String resourcePath = "/api/v1/" + resourceName;

    private final CreateVotingRequestUseCase createVotingRequestUseCase;
    private final AddUserVoteRequestUseCase addUserVoteRequestUseCase;
    private final RemoveUserVoteRequestUseCase removeUserVoteRequestUseCase;

    @PostMapping
    public ResponseEntity<ObjectId> createVotingRequest(@RequestBody CreateVotingRequestCommand command) {
        ObjectId votingId = createVotingRequestUseCase.createVotingRequest(command);
        return ResponseEntity
                .accepted()
                .body(votingId);
    }
}
