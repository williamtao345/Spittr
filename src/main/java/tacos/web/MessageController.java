package tacos.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tacos.Message;
import tacos.User;
import tacos.data.MessageRepository;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private MessageRepository messageRepo;

    public MessageController(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/{username}")
    public List<Message> getMessages(
            @PathVariable("username") String username,
            @AuthenticationPrincipal User user
    ) {
        if (user.isCredentialsNonExpired()) {
            return messageRepo.findByUsername(username);
        } else {
            return null;
        }
    }

    @PostMapping
    public void sendMessages(@RequestBody Message message){
        messageRepo.save(message);
    }
}
