package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.regex.Pattern;

public class DisplaySchedule {
    public static String displayTheSchedule(String group) {
        Document doc = Jsoup.parse(Bot.htmlBuilder.toString());
        Elements postTitleElements = doc.getElementsByAttributeValue("class", "col-md-6");

        if (postTitleElements.text().length() == 0) {
            return "За вашим запитом записів не знайдено. Можливо ви ввели не вірно групу";
        } else {
            return getScheduleFromHTML(postTitleElements, group);
        }
    }

    private static String getScheduleFromHTML(Elements postTitleElements, String group) {
        StringBuilder builder = new StringBuilder();
        String[] str = postTitleElements.text().split(" ");
        builder.append(group).append(" ").append(str[0]);    // виведення групи і дати

        for (int i = 2; i < str.length; i++) {
            if (Pattern.matches("[0-9]", str[i])) {
                builder.append("\n").append(str[i++]).append(". ").append(str[i++]).append("-").append(str[i++]).append("\n"); // виведення години початку та кінця пари

            } else if (str[i].matches("^(http|https|ftp)://.*$")) {
                builder.append("\n").append(str[i]).append("\n");  // виведення посилання
            } else {
                builder.append(str[i]).append(" ");
            }
        }
        return builder.toString();
    }
}
