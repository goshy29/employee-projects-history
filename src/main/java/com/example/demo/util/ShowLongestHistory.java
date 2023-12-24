package com.example.demo.util;

import com.example.demo.model.Couple;
import com.example.demo.model.History;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowLongestHistory {
    public static List<Couple> show(Map<Integer, List<History>> historyMap) {
        Map<Integer, Integer> maxDays = new HashMap<>();
        Map<Integer, List<History[]>> couplesWithMaxDays = new HashMap<>();

        for (List<History> histories : historyMap.values()) {
            if (histories.size() > 1) {
                for (int i = 0; i < histories.size() - 1; i++) {
                    for (int j = i + 1; j < histories.size(); j++) {
                        History history1 = histories.get(i);
                        History history2 = histories.get(j);

                        if (CheckCommonPeriod.check(history1.getStartDate(), history1.getEndDate(),
                                history2.getStartDate(), history2.getEndDate())) {

                            int commonDays = CalculateDays.calculate(history1, history2);

                            if (!maxDays.containsKey(history1.getProjectId()) ||
                                    commonDays > maxDays.get(history1.getProjectId())) {
                                maxDays.put(history1.getProjectId(), commonDays);

                                List<History[]> couples = new ArrayList<>();
                                couples.add(new History[]{history1, history2});
                                couplesWithMaxDays.put(history1.getProjectId(), couples);
                            } else if (commonDays == maxDays.get(history1.getProjectId())) {
                                List<History[]> couples = couplesWithMaxDays.get(history1.getProjectId());
                                couples.add(new History[]{history1, history2});
                                couplesWithMaxDays.put(history1.getProjectId(), couples);
                            }
                        }
                    }
                }
            }
        }

        List<Couple> coupleList = new ArrayList<>();
        for (Map.Entry<Integer, List<History[]>> entry : couplesWithMaxDays.entrySet()) {
            int projectId = entry.getKey();
            List<History[]> couples = entry.getValue();
            int maxCommonDays = maxDays.get(projectId);

            for (History[] couple : couples) {
                History history1 = couple[0];
                History history2 = couple[1];
                Couple coupleObject = new Couple(
                        history1.getEmployeeId(),
                        history2.getEmployeeId(),
                        String.format("Worked together on project %d for %d days.", projectId, maxCommonDays));
                coupleList.add(coupleObject);
            }
        }
        return coupleList;
    }
}
