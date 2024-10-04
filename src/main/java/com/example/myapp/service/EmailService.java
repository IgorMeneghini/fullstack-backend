package com.example.myapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class EmailService {

    private static final String FILE_DIRECTORY = "Emails";

    public static void logUpdates(int cardId, @RequestBody Map<String, Object> updates, float oldPrice) {
        String newStatus = (String) updates.getOrDefault("status", "");
        float newPrice = (updates.containsKey("price")) ? ((Number) updates.get("price")).floatValue() : oldPrice;

        String emailContent = createEmailContent(cardId, newStatus, oldPrice, newPrice);
        writeEmailToFile(cardId, emailContent);
    }

    private static String createEmailContent(int cardId, String newStatus, float oldPrice, float newPrice) {
        String emailTemplate = "Subject: Lead Acceptance Notification\n" +
                "To: vendas@test.com\n\n" +
                "Dear Sales Team,\n\n" +
                "We would like to inform you that the lead has been accepted and updated to accepted status in the database.\n\n"
                +
                "Transaction Details:\n" +
                "- Price: $%s\n\n" +
                "If you have any questions or need further assistance, please do not hesitate to contact us.\n\n" +
                "Best regards,\n\n" +
                "[Igor Vidal]\n";

        if (!newStatus.contains("Declined")) {
            String discountNotice = (oldPrice >= 500.0f) ? "(10% discount applied)" : "";
            return String.format(emailTemplate, newPrice + " " + discountNotice);
        } else {
            return String.format("Subject: Lead Status Notification\n" +
                    "To: sales@test.com\n\n" +
                    "Dear Sales Team,\n\n" +
                    "We would like to inform you that the lead has been " + newStatus +
                    " and updated to " + newStatus + " status in the database.\n\n" +
                    "Best regards,\n\n" +
                    "Igor Vidal\n");
        }
    }

    private static void writeEmailToFile(int cardId, String emailContent) {
        String filePath = FILE_DIRECTORY + "\\updates_card_" + cardId + ".txt";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write(emailContent);
        } catch (IOException e) {
            System.err.println("Failed to write email to file: " + e.getMessage());
        }
    }
}
