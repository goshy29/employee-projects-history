package com.example.demo.util;

import com.example.demo.model.Employee;
import com.example.demo.model.History;
import com.example.demo.model.Project;
import org.springframework.cglib.core.Local;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CSVReader {
    public static List<?>[] read(String file) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        Map<Integer, Project> projectMap = new HashMap<>();

        List<Employee> employeeList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();
        List<History> historyList = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int employeeId = Integer.parseInt(data[0].trim());
                String employeeName = "Employee " + data[0].trim();
                Employee employee = new Employee(employeeId, employeeName);
                if (!employeeMap.containsKey(employeeId)) {
                    employeeMap.put(employeeId, employee);
                    employeeList.add(employee);
                }

                int projectId = Integer.parseInt(data[1].trim());
                String projectName = "Project " + data[1].trim();
                Project project = new Project(projectId, projectName);
                if (!projectMap.containsKey(projectId)) {
                    projectMap.put(projectId, project);
                    projectList.add(project);
                }

                employeeId = Integer.parseInt(data[0].trim());
                projectId = Integer.parseInt(data[1].trim());
                LocalDate startDate = DateParser.parse(data[2].trim());
                LocalDate endDate = (data[3].trim().equalsIgnoreCase("NULL") ? null: DateParser.parse(data[3].trim()));
                History history = new History(employeeId, projectId, startDate, endDate);
                historyList.add(history);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new List[]{employeeList, projectList, historyList};
    }
}
