package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.infrastructure.rest.dto.ChatGPTRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ChatGPTResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/bot")
public class BotController {
    private static final Logger logger = LogManager.getLogger(BotController.class);
    @Value("${openai.api.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiURL;
    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){
        logger.log(Level.WARN, "on est dans la méthode");
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);
        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }
}
