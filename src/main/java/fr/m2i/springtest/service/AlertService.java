package fr.m2i.springtest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    private final String channel;
    private final String priority;

    public AlertService(@Value("${alert.channel:EMAIL}") String channel,
                        @Value("${alert.priority:NORMAL}") String priority) {
        this.channel = channel;
        this.priority = priority;
    }

    public String sendAlert(String message) {
        String finalMessage = String.format("[%s] %s: %s",
                channel, priority, message);
        // ... (logique d'envoi réelle)
        return finalMessage;
    }
}