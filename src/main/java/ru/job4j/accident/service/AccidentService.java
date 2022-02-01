package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.Store;

import java.util.List;

@Service
public class AccidentService implements Services {
    @Autowired
    private Store store;

    @Override
    public List<Accident> findAll() {
        return store.findAll();
    }
}
