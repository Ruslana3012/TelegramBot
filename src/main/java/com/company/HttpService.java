package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpService {
    public String getSchedule(String group, String dateFrom, String dateTo) throws IOException {
        URL url = new URL("https://dekanat.nung.edu.ua/cgi-bin/timetable.cgi?n=700");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=windows-1251");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(getParameters(group, dateFrom, dateTo)));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "windows-1251"));

        while (bufferedReader.readLine() != null) {
            Bot.htmlBuilder.append(bufferedReader.readLine());
        }

        bufferedReader.close();
        connection.disconnect();

        return DisplaySchedule.displayTheSchedule(group);
    }

    private static Map<String, String> getParameters(String group, String dateFrom, String dateTo) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("group", group);
        parameters.put("sdate", dateFrom);
        parameters.put("edate", dateTo);
        parameters.put("faculty", "0");
        parameters.put("course", "0");
        parameters.put("n", "700");

        return parameters;
    }
}
