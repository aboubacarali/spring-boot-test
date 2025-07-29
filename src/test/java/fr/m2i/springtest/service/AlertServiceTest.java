package fr.m2i.springtest.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AlertService.class)
@TestPropertySource(properties = {"alert.channel=SMS", "alert.priority=URGENT"})
class AlertServiceTest {

    @Autowired
    private AlertService alertService;

    @Test
    void shouldExpectUrgentSmsAlert() {
        // Arrange
        String message = "Panne système imminente";
        String expectedFormat = "[SMS] URGENT: Panne système imminente";

        // Act
        String result = alertService.sendAlert(message);

        // Assert
        assertThat(result).isEqualTo(expectedFormat);
    }
}