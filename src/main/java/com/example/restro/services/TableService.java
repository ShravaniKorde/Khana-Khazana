package com.example.restro.services;

import com.example.restro.repository.TableRepository;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    private TableRepository tableRepository;

    TableService(TableRepository tableRepository){
        this.tableRepository = tableRepository;

    }
}
