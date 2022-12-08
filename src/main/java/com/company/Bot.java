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
                    execute(Responses.schedule(chatId));
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
