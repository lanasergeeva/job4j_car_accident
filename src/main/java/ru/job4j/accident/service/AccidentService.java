package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.Store;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccidentService implements Services {
    @Autowired
    private Store store;

    @Override
    @Transactional
    public List<Accident> findAll() {
        return store.findAll();
    }

    @Override
    @Transactional
    public void save(Accident accident) {
        store.save(accident);
    }

    @Override
    @Transactional
    public Accident findById(int id) {
        return store.findById(id);
    }

    @Override
    public List<AccidentType> findAllTypes() {
        return store.findAllTypes();
    }
}
