package com.company;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Responses {

    public static SendMessage changeGroup(Long chatId) {
        String text = "Введіть вашу групу. Наступного разу достатньо клікнути кнопку \"Розклад\", щоб отримати розклад занять. Якщо необхідно змінити групу, натисніть кнопку \"Змінити групу\"";
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(String.valueOf(chatId));

        return sendMessage;
    }

    public static SendMessage schedule(Long chatId) throws SQLException, IOException {
        return getSchedule(chatId, Database.getGroupFromDatabaseByKey(chatId));
    }

    public static SendMessage newGroup(Long chatId, String group) throws IOException, SQLException {
        if (!Database.getGroupFromDatabaseByKey(chatId).equals(group)) {
            Database.updateGroupInDatabase(chatId, group);
        } else {
            Database.addChatIdAndGroupToDatabase(chatId, group);
        }

        return getSchedule(chatId, group);
    }

    private static SendMessage getSchedule(Long chatId, String groupFromDatabaseByKey) throws IOException {
        String dateFrom = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
        String dateTo = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
        String schedule = new HttpService().getSchedule(groupFromDatabaseByKey, dateFrom, dateTo);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(schedule);
        sendMessage.setChatId(String.valueOf(chatId));

        return sendMessage;
    }
}
