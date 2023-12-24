package com.example.demo.util;

import com.example.demo.model.Couple;
import com.example.demo.model.History;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryReader {
    public static List<Couple> read(String file) {
        Map<Integer, List<History>> historyMap = new HashMap<>();
         String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int employeeId = Integer.parseInt(data[0].trim());
                int projectId = Integer.parseInt(data[1].trim());
                LocalDate startDate = DateParser.parse(data[2].trim());
                LocalDate endDate = (data[3].trim().equalsIgnoreCase("NULL") ? null: DateParser.parse(data[3].trim()));
                History history = new History(employeeId, projectId, startDate, endDate);

                if (!historyMap.containsKey(projectId)) {
                    historyMap.put(projectId, new ArrayList<>());
                }
                historyMap.get(projectId).add(history);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ShowLongestHistory.show(historyMap);
    }
}
