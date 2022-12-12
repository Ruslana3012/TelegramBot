package com.company;

import javax.persistence.*;

@Entity
@Table(name = "telegrambot")
public class TelegramBot {
    @Id
    @Column(name = "chat_id")
    private Long chat_id;
    @Column(name = "group_name")
    private String group_name;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public TelegramBot(String group_name) {
        this.group_name = group_name;
    }

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public TelegramBot(Long chat_id) {
        this.chat_id = chat_id;
    }

    public TelegramBot() {
    }

    public TelegramBot(Long chat_id, String group_name) {
        this.chat_id = chat_id;
        this.group_name = group_name;
    }
}
