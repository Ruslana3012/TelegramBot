package com.company;

public interface TelegramBotInterface {
    void addChat_idAndGroup_name(Long chat_id, String group_name);

    void updateGroupInDatabase(Long chat_id, String group_name);

    String getGroupFromDatabaseByKey(Long chat_id);
}
