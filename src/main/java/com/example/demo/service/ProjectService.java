package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project findById(int id) {
        return projectRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
    }

    public Project save(Project users) {
        return projectRepository.save(users);
    }

    public void insert(List<Project> projectList) {
        for (Project project : projectList) {
            projectRepository.save(project);
        }
    }

    public void deleteById(int id) {
        boolean exists = projectRepository.existsById(id);
        if (exists) {
            projectRepository.deleteById(id);
        } else {
            throw  new IllegalArgumentException("Invalid id!");
        }
    }

    public boolean existProjectById(int id) {
        return projectRepository.existsById(id);
    }
}
