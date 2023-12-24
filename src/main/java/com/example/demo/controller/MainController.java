package com.example.demo.controller;

import com.example.demo.model.Couple;
import com.example.demo.model.Employee;
import com.example.demo.model.History;
import com.example.demo.model.Project;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.HistoryService;
import com.example.demo.service.ProjectService;
import com.example.demo.util.CSVReader;
import com.example.demo.util.HistoryReader;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private HistoryService historyService;

    @GetMapping("/")
    public String getMain() {
        return "main";
    }

    @GetMapping("/load")
    public String LoadCSV(Model model) {
        String csvFile = "your path to csv file..";
        if (historyService.getAll().size() > 0) {
            model.addAttribute("csvAlreadyLoaded", true);
            return "main";
        }
        List<?>[] objectList = CSVReader.read(csvFile);
        List<Employee> employeeList = (List<Employee>) objectList[0];
        List<Project> projectList = (List<Project>) objectList[1];
        List<History> historyList = (List<History>) objectList[2];
        employeeService.insert(employeeList);
        projectService.insert(projectList);
        historyService.insert(historyList);
        model.addAttribute("csvLoaded", true);
        return "main";
    }

    @GetMapping("/employee")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employee/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeEdit";
    }

    @PostMapping("/employee/add")
    @Validated
    public String saveAddedEmployee(@Valid @ModelAttribute Employee employee, BindingResult result, Model model) {
        if (employeeService.existEmployeeById(employee.getId())) {
            model.addAttribute("existEmployee", true);
            return "employeeEdit";
        }
        employeeService.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employeeEdit";
    }

    @PostMapping("/employee/edit/{id}")
    @Validated
    public String saveEditedEmployee(@PathVariable int id, @Valid @ModelAttribute Employee employee, BindingResult result, Model model) {
        employeeService.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployeeById(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/employee";
    }


    @GetMapping("/project")
    public String getAllProjects(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/project/add")
    public String addProject(Model model) {
        model.addAttribute("project", new Project());
        return "projectEdit";
    }

    @PostMapping("/project/add")
    @Validated
    public String saveAddedProject(@Valid @ModelAttribute Project project, BindingResult result, Model model) {
        if (projectService.existProjectById(project.getId())) {
            model.addAttribute("existProject", true);
            return "projectEdit";
        }
        projectService.save(project);
        return "redirect:/project";
    }

    @GetMapping("/project/edit/{id}")
    public String editProject(@PathVariable int id, Model model) {
        model.addAttribute("project", projectService.findById(id));
        return "projectEdit";
    }

    @PostMapping("/project/edit/{id}")
    @Validated
    public String saveEditedProject(@PathVariable int id, @Valid @ModelAttribute Project project, BindingResult result, Model model) {
        projectService.save(project);
        return "redirect:/project";
    }

    @GetMapping("/project/delete/{id}")
    public String deleteProjectById(@PathVariable int id) {
        projectService.deleteById(id);
        return "redirect:/project";
    }


    @GetMapping("/couples")
    public String pairOfEmployees(Model model) {
        String csvFile = "your path to csv file..";
        List<Couple> coupleList = HistoryReader.read(csvFile);
        model.addAttribute("couples", coupleList);
        return "coupleEmployees";
    }
}
