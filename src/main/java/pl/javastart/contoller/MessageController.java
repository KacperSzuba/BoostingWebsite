package pl.javastart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.javastart.message.MessageHandler;
import pl.javastart.model.entity.message.Message;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageHandler messageHandler;

    public MessageController(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @GetMapping
    public String messenger(Model model) {
        model.addAttribute("users",messageHandler.listOfRecipients());
        Message message = new Message();
        model.addAttribute("sendMessage",message);
        return "messageView/sendMessage";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute("sendMessage") Message message){
        messageHandler.sendMessage(message);
        return "redirect:/message/singleConversation/"+messageHandler.getTemp();
    }

    @RequestMapping("/retrieve")
    public ModelAndView listOfConversations(){
        return new ModelAndView("messageView/messagesReceived", "conversations",messageHandler.setOfSendRecipients());
    }

    @GetMapping("/singleConversation/{id}")
    public String singleConversation(@PathVariable("id") final Long id) {
        messageHandler.setIdOfConversation(id);
        return "messageView/singleConversation";
    }
}