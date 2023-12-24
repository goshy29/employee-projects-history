package com.example.demo.service;

import com.example.demo.model.History;
import com.example.demo.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    public List<History> getAll() {
        return historyRepository.findAll();
    }

    public void insert(List<History> historyList) {
        for (History history : historyList) {
            historyRepository.save(history);
        }
    }
}
