package com.company;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardMarkup {
    public static SendMessage getStartMessage(Long chatId) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Розклад");
        row.add("Змінити групу");
        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text("Вас вітає ІФНТУНГ!")
                .replyMarkup(keyboardMarkup)
                .build();
    }
}
