// ChatController.java
package com.example.penstore.controller;

import com.example.penstore.entity.ChatMessage;
import com.example.penstore.entity.User;
import com.example.penstore.service.impl.ChatService;
import com.example.penstore.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping("/{otherUserId}")
    public String chatPage(@PathVariable String otherUserId,
                           @SessionAttribute("user") User currentUser,
                           Model model) {
        User otherUser = userService.getById(otherUserId);
        List<ChatMessage> messages = chatService.getAllChatMessages(
                currentUser.getId(),
                otherUserId
        );
        chatService.markMessagesAsRead(currentUser.getId(), otherUserId);
        model.addAttribute("otherUser", otherUser);
        model.addAttribute("messages", messages);
        model.addAttribute("currentUser", currentUser);
        return "chat";
    }

}