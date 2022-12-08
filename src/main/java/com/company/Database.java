package com.company;

import java.sql.*;

public class Database {
    private static Statement statement;

    private static void connectionToDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/telegram_bot_schedule", "postgres", "vetabe");
        statement = connection.createStatement();
    }

    public static void addChatIdAndGroupToDatabase(Long chatId, String group) throws SQLException {
        connectionToDatabase();
        statement.executeQuery("INSERT INTO telegrambot (chat_id, group_name)" + "VALUES (" + chatId + ", '" + group + "');");
    }

    public static String getGroupFromDatabaseByKey(Long chatId) throws SQLException {
        connectionToDatabase();
        ResultSet resultSet = statement.executeQuery("SELECT group_name FROM telegrambot WHERE chat_id = " + chatId);
        resultSet.next();
        return resultSet.getString("group_name");
    }

    public static void updateGroupInDatabase(Long chat_id, String group) throws SQLException {
        connectionToDatabase();
        statement.executeUpdate("UPDATE telegrambot SET group_name = '" + group + "' WHERE chat_id =" + chat_id);
    }
}
