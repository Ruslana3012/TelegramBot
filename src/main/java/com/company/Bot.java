package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.SQLException;

public class Bot extends TelegramLongPollingBot {
    static StringBuilder htmlBuilder = new StringBuilder();

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();

        switch (update.getMessage().getText()) {
            case "/start":
                try {
                    execute(KeyboardMarkup.getStartMessage(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Змінити групу":
                try {
                    execute(Responses.changeGroup(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Розклад":
                try {
                    execute(Responses.Schedule(chatId));
                } catch (TelegramApiException | IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    execute(Responses.newGroup(chatId, update.getMessage().getText()));
                } catch (TelegramApiException | IOException | SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public String getBotUsername() {
        return "Schedule IFTUOG";
    }

    @Override
    public String getBotToken() {
        return "5753744462:AAEwHgTrRsow5h25LDob1UTmcIEUEk2acpA";
    }
}

/*    public void onUpdateReceived(Update update) {

        Long chatId = update.getMessage().getChatId();
        SendMessage send;
        String dateTo;
        String dateFrom;
        if (update.getMessage().getText().equals("/start")) {
            try {
                execute(Message.getStartMessage(chatId));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.getMessage().getText().equals("Змінити групу")) {
            String text = "Введіть вашу групу. Наступного разу достатньо клікнути кнопку \"Розклад\", щоб отримати розклад занять. Якщо необхідно змінити групу, натисніть кнопку \"Змінити групу\"";
            send = new SendMessage();
            try {
                send.setText(text);
                send.setChatId(String.valueOf(chatId));
                execute(send);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.getMessage().getText().equals("Розклад")) {
            dateFrom = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            dateTo = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            try {
                String schedule = new HttpService().getSchedule(Database.getGroupFromDatabaseByKey(chatId), dateFrom, dateTo);
                send = new SendMessage();
                send.setText(schedule);
                send.setChatId(String.valueOf(chatId));
                execute(send);
            } catch (TelegramApiException | IOException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                String group = update.getMessage().getText();
                dateFrom = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
                dateTo = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
                String schedule = new HttpService().getSchedule(group, dateFrom, dateTo);
                send = new SendMessage();
                send.setText(schedule);
                send.setChatId(String.valueOf(chatId));
                execute(send);

                if (!Database.getGroupFromDatabaseByKey(chatId).equals(group)) {
                    Database.updateGroupInDatabase(chatId, group);
                } else {
                    Database.addChatIdAndGroupToDatabase(chatId, group);
                }
            } catch (TelegramApiException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */